package ale.server.ale.calculations;

class NandifyFormula {
    private FormulaTree formulaTree;

    NandifyFormula(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    public String generateNandifiedFormula() {
        Node[] arrayTree = formulaTree.getArrayTree();

        return nandifyTree(0, arrayTree);

    }


    private String nandifyTree(int rootIndex, Node[] arrayTree) {
        if (!formulaTree.nodeHasLeftChild(rootIndex) & !formulaTree.nodeHasRightChild(rootIndex)) {

            return arrayTree[rootIndex].getNandifiedValue();
        }
        String leftNandifiedValue;
        String rightNandifiedValue;

        if (!formulaTree.nodeHasLeftChild(rootIndex)) {
            leftNandifiedValue = null;
        } else {
            leftNandifiedValue = nandifyTree(formulaTree.getLeftChildIndex(rootIndex), arrayTree);
        }
        if (!formulaTree.nodeHasRightChild(rootIndex)) {
            rightNandifiedValue = null;
        } else {
            rightNandifiedValue = nandifyTree(formulaTree.getRightChildIndex(rootIndex), arrayTree);
        }

        if (Utils.isOperator(arrayTree[rootIndex])) {
            String nandifiedValue = getNandifiedResult(arrayTree[rootIndex], leftNandifiedValue, rightNandifiedValue);
            arrayTree[rootIndex].setNandifiedValue(nandifiedValue);

            return nandifiedValue;
        }
        return arrayTree[rootIndex].getNandifiedValue();

    }

    public String getNandifiedResult(Node root, String left, String right) {
        char rootValue = root.getValue();

        switch (rootValue) {
            case '|':


            case '&':

            case '~':


            case '=':


            default:

                // here is >

        }
    }

}
