package ale.server.ale.calculations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FormulaTree {


    private Node[] arrayTree = new Node[100];

    // Initial value 0 - we focus first on the root
    private int focusNodeIndex = 0;

    // Used when
    private boolean closingBracketDetected = false;

    public void addNode(char value) {

        // Always adding the first symbol on zero position of the array
        if (arrayTree[0] == null) {
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

    public boolean nodeHasLeftChild(int focusNodeIndex) {
        int leftChildIndex = getLeftChildIndex(focusNodeIndex);


        if (arrayTree[leftChildIndex] == null) {
            return false;
        }

        return true;
    }

    public boolean nodeHasRightChild(int focusNodeIndex) {
        int rightChildIndex = getRightChildIndex(focusNodeIndex);


        if (arrayTree[rightChildIndex] == null) {
            return false;
        }

        return true;
    }

    public int getLeftChildIndex(int index) {
        int leftChildIndex = 2 * index + 1;

        return leftChildIndex;

    }

    public int getRightChildIndex(int index) {
        int rightChildIndex = 2 * index + 2;

        return rightChildIndex;

    }

    public int getParentIndex(int childIndex) {

        int parentIndex = (int) Math.ceil(childIndex / 2.0) - 1;

        return parentIndex;
    }

    public Node[] getArrayTree() {
        return arrayTree;
    }


    public List<Character> getUniqueVariables() {
        List<Character> variablesList = new ArrayList<>();
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', ',', '|', '~');
        for (Node node : arrayTree) {
            if (node != null) {
                if (!bannedChars.contains(node.getValue())) {
                    variablesList.add(node.getValue());
                    bannedChars.add(node.getValue());
                }

            }
        }
        return variablesList;
    }
}


