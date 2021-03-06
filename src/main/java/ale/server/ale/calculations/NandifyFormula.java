package ale.server.ale.calculations;

import ale.server.ale.calculations.structures.FormulaTree;
import ale.server.ale.calculations.structures.Node;
import ale.server.ale.calculations.utils.Utils;

public class NandifyFormula {
    private FormulaTree formulaTree;

    public NandifyFormula(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    public String generateNandifiedFormula() {
        Node[] arrayTree = formulaTree.getArrayTree();

        return nandifyTree(0, arrayTree);

    }

    /**
     * The algorithm is based on binary tree evaluation
     * Generates nandified formula
     */
    private String nandifyTree(int rootIndex, Node[] arrayTree) {
        if (!formulaTree.nodeHasLeftChild(rootIndex) & !formulaTree.nodeHasRightChild(rootIndex)) {

            return arrayTree[rootIndex].getValue() + "";
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

        if (Utils.isOperator(arrayTree[rootIndex]) & arrayTree[rootIndex].getValue() != '%') {
            String nandifiedValue = getNandifiedResult(arrayTree[rootIndex], leftNandifiedValue, rightNandifiedValue);
            arrayTree[rootIndex].setNandifiedValue(nandifiedValue);

            return nandifiedValue;
        }
        return arrayTree[rootIndex].getNandifiedValue();

    }

    /**
     * Nandifies expression base on the operator and the passed left or right variables
     */
    private String getNandifiedResult(Node root, String left, String right) {
        StringBuilder nandifiedResult = new StringBuilder();
        char rootValue = root.getValue();

        switch (rootValue) {
            case '|':
                // |(A,B) -> %(%(A,A),%(B,B))

                nandifiedResult.append("%(%(").append(left).append(",").append(left).append("),%(").append(right).append(",").append(right).append("))");
                return nandifiedResult.toString();
            case '&':
                // &(A,B) -> %(%(A,B),%(A,B))

                nandifiedResult.append("%(%(").append(left).append(",").append(right).append("),%(").append(left).append(",").append(right).append("))");
                return nandifiedResult.toString();

            case '~':
                // ~(A) -> %(A,A)

                if (right == null) {

                    nandifiedResult.append("%(").append(left).append(",").append(left).append(")");

                } else if (left == null) {
                    nandifiedResult.append("%(").append(right).append(",").append(right).append(")");
                }

                return nandifiedResult.toString();

            case '=':
                // =(A,B) -> %(%(%(A,A),%(B,B)),%(A,B))

                nandifiedResult.append("%(%(%(").append(left).append(",").append(left).append("),%(").append(right)
                        .append(",").append(right).append(")),%(").append(left).append(",").append(right).append("))");
                return nandifiedResult.toString();
            default:
                // Here comes the '>' operator
                // >(A,B) -> %(A,%(B,B))

                nandifiedResult.append("%(").append(left).append(",").append("%(").append(right).append(",").append(right).append("))");
                return nandifiedResult.toString();

        }
    }

}
