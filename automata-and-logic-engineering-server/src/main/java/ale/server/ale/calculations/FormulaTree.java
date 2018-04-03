package ale.server.ale.calculations;

public class FormulaTree {


    private Node[] arrayTree = new Node[1000];

    // Initial value 0 - we focus first on the root
    private int focusNodeIndex = 0;


    private int nrOfClosingBracketsDetected = 0;

    public void addNode(char value) {

        // Always adding the first symbol on zero position of the array
        if (arrayTree[0] == null) {
            Node newNode = new Node(value);
            arrayTree[0] = newNode;
            return;
        }
        // Do not perform any actions when we receive the '(' symbol
        if (value == '(') {
            nrOfClosingBracketsDetected = 0;

            return;
        }
        if (value == ')') {
            nrOfClosingBracketsDetected++;
            return;
        }

        // When the symbols ')' and ',' are received successively we go n levels up based on the counted nr of ')' symbols
        if (value == ',' & nrOfClosingBracketsDetected > 0) {
            int nrOfLevelsUp = nrOfClosingBracketsDetected + 1;
            for (int i = 0; i < nrOfLevelsUp; i++) {
                focusNodeIndex = getParentIndex(focusNodeIndex);

            }

            nrOfClosingBracketsDetected = 0;

            return;
        }
        // When we receive this symbol the focus goes to the parent node 1 level up in the tree
        if (value == ',') {
            focusNodeIndex = getParentIndex(focusNodeIndex);

            nrOfClosingBracketsDetected = 0;

            return;
        }


        // Create new node only with the relevant operands and variables
        Node newNode = new Node(value);


        if (!nodeHasLeftChild(focusNodeIndex)) {
            int leftChildIndex = getLeftChildIndex(focusNodeIndex);
            arrayTree[leftChildIndex] = newNode;

            focusNodeIndex = leftChildIndex;
            nrOfClosingBracketsDetected = 0;

            return;
        }

        if (!nodeHasRightChild(focusNodeIndex)) {
            int rightChildIndex = getRightChildIndex(focusNodeIndex);
            arrayTree[rightChildIndex] = newNode;

            focusNodeIndex = rightChildIndex;
            nrOfClosingBracketsDetected = 0;

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

}


