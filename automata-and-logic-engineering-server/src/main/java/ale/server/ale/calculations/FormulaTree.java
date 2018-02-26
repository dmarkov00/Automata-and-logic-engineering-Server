package ale.server.ale.calculations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormulaTree {

    private Node[] arrayTree = new Node[100];

    // Initial value 0 - we focus first on the root
    private int focusNodeIndex = 0;

    // Used when
    private boolean closingBracketDetected = false;

    public void addNode(char value) {
        System.out.println(Arrays.deepToString(this.arrayTree));

        // Always adding the first symbol on zero position of the array
        if (arrayTree.length == 0) {
            Node newNode = new Node(value);
            arrayTree[0] = newNode;
            return;
        }

        // Do not perform any actions when we receive the '(' symbol
        if (value == '(') {
            closingBracketDetected = false;
            return;
        }
        if (value == ')') {
            closingBracketDetected = true;
            return;
        }

        // When the symbols ')' and ',' are received successively we go 2 levels up in the tree
        if (value == ',' & closingBracketDetected) {

            focusNodeIndex = getParentIndex(focusNodeIndex);
            focusNodeIndex = getParentIndex(focusNodeIndex);
            closingBracketDetected = false;
            return;
        }
        // When we receive this symbol the focus goes to the parent node 1 level up in the tree
        if (value == ',') {
            focusNodeIndex = getParentIndex(focusNodeIndex);
            closingBracketDetected = false;
            return;
        }


        // Create new node only with the relevant operands and variables
        Node newNode = new Node(value);


        if (!nodeHasLeftChild(focusNodeIndex)) {
            int leftChildIndex = getLeftChildIndex(focusNodeIndex);
            arrayTree[leftChildIndex] = newNode;

            focusNodeIndex = leftChildIndex;

            closingBracketDetected = false;
            return;
        }

        if (!nodeHasRightChild(focusNodeIndex)) {
            int rightChildIndex = getRightChildIndex(focusNodeIndex);
            arrayTree[rightChildIndex] = newNode;

            focusNodeIndex = rightChildIndex;

            closingBracketDetected = false;
        }

    }

    private boolean nodeHasLeftChild(int focusNodeIndex) {
        int leftChildIndex = 2 * focusNodeIndex + 1;


        if (arrayTree[leftChildIndex] == null) {
            return false;
        }

        return true;
    }

    private boolean nodeHasRightChild(int focusNodeIndex) {
        int rightChildIndex = 2 * focusNodeIndex + 2;


        if (arrayTree[rightChildIndex] == null) {
            return false;
        }

        return true;
    }


    private int getLeftChildIndex(int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;

        return leftChildIndex;

    }

    private int getRightChildIndex(int parentIndex) {
        int rightChildIndex = 2 * parentIndex + 2;

        return rightChildIndex;

    }

    private int getParentIndex(int childIndex) {

        int parentIndex = (int) Math.ceil(childIndex / 2.0) - 1;

        return parentIndex;
    }
}


