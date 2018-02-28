package ale.server.ale.calculations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    private boolean nodeHasLeftChild(int focusNodeIndex) {
        int leftChildIndex = getLeftChildIndex(focusNodeIndex);


        if (arrayTree[leftChildIndex] == null) {
            return false;
        }

        return true;
    }

    private boolean nodeHasRightChild(int focusNodeIndex) {
        int rightChildIndex = getRightChildIndex(focusNodeIndex);


        if (arrayTree[rightChildIndex] == null) {
            return false;
        }

        return true;
    }


    private int getLeftChildIndex(int index) {
        int leftChildIndex = 2 * index + 1;

        return leftChildIndex;

    }

    private int getRightChildIndex(int index) {
        int rightChildIndex = 2 * index + 2;

        return rightChildIndex;

    }

    private int getParentIndex(int childIndex) {

        int parentIndex = (int) Math.ceil(childIndex / 2.0) - 1;

        return parentIndex;
    }

    public void generateImageFile() {
        Node[] arrayTree = this.arrayTree;

        List<String> lines = new ArrayList<>();
        lines.add("graph logic {");
        lines.add("node [ fontname = \"Arial\" ]");


        for (int i = 0; i < arrayTree.length; i++) {
            if (arrayTree[i] != null) {

                lines.add("node" + i + "[ label = \"" + arrayTree[i].getValue() + "\" ]");

                if (nodeHasLeftChild(i)) {
                    lines.add("node" + i + " -- " + "node" + getLeftChildIndex(i));
                }
                if (nodeHasRightChild(i)) {
                    lines.add("node" + i + " -- " + "node" + getRightChildIndex(i));
                }
            }

        }
        lines.add("}");
        Path out = Paths.get("./src/main/resources/dot-files/graph.dot");

        try {
            Files.write(out, lines, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process p = Runtime.getRuntime().exec("dot -Tpng -o./src/main/resources/images/graph.png ./src/main/resources/dot-files/graph.dot");
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


