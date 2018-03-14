package ale.server.ale.calculations;

import java.util.*;

class TruthTableBuilder {
    TruthTableBuilder(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    private FormulaTree formulaTree;

    public List<Map<Character, Byte>> generateTruthTable() {
        List<Map<Character, Byte>> truthTable;

        truthTable = fillTruthTableWithVariableData();
        for (Map<Character, Byte> tableRow : truthTable) {
            Node[] arrayTree = setBinaryValuesInArrayTree(tableRow);
            byte formulaEvaluationResult = evaluateTree(0, arrayTree);

            // The result is put under the '=' sing in the table row map
            tableRow.put('=', formulaEvaluationResult);
        }
        return truthTable;

    }

    /**
     * Generates a list of values that describe the truth table
     */
    public List<String> generateTableData() {
        List<String> tableData = new ArrayList<>();

        List<Character> uniqueTreeVariables = Utils.getUniqueTreeVariables(formulaTree);
        for (Character variable : uniqueTreeVariables) {
            String stringVariable = variable.toString();
            tableData.add(stringVariable);
        }
        tableData.add(FormulaReader.parsedFormulaString);
        return tableData;
    }


    private byte evaluateTree(int root, Node[] arrayTree) {


        if (!formulaTree.nodeHasLeftChild(root) && !formulaTree.nodeHasLeftChild(root)) {

            return arrayTree[root].getBinaryValue();
        }

        byte leftBinaryValue = evaluateTree(formulaTree.getLeftChildIndex(root), arrayTree);
        byte rightBinaryValue = evaluateTree(formulaTree.getRightChildIndex(root), arrayTree);

        if (Utils.isNotVariable(arrayTree[root])) {
            return getBinaryResult(arrayTree[root], arrayTree[leftBinaryValue].getBinaryValue(), arrayTree[rightBinaryValue].getBinaryValue());

        }
        return arrayTree[root].getBinaryValue();
    }

    private byte getBinaryResult(Node root, byte left, byte right) {
        char rootValue = root.getValue();
        switch (rootValue) {
            case '|':
                return (byte) (left | right);
            case '&':
                return (byte) (left & right);
            case '~':
                if (left == 0) {
                    return 1;
                } else if (left == 1) {
                    return 0;
                }
            case '=':
                if (left == right) {
                    return 0;
                } else {
                    return 1;
                }
            case '>':
                if (left == 1 && right == 0) {
                    return 0;
                } else return 1;

            default:
                return 0;
            // Fix later
        }

    }


    private Node[] setBinaryValuesInArrayTree(Map<Character, Byte> tableRow) {
        Node[] arrayTree = formulaTree.getArrayTree();
        for (Node node : arrayTree) {
            if (tableRow.containsKey(node.getValue())) {
                node.setBinaryValue(tableRow.get(node.getValue()));
            }
        }
        return arrayTree;

    }

    private List<Map<Character, Byte>> fillTruthTableWithVariableData() {

        List<Map<Character, Byte>> truthTable = new ArrayList<>();
        Map<Character, Byte> tableRow = new HashMap<>();

        List<Character> variablesList = Utils.getUniqueTreeVariables(formulaTree);
        int nrOfVariables = variablesList.size();
        int nrOfRows = (int) Math.pow(2, nrOfVariables);

        for (int i = 0; i < nrOfRows; i++) {
            for (int j = nrOfVariables - 1; j >= 0; j--) {
                byte binaryValue = (byte) ((i / (int) Math.pow(2, j)) % 2);

                tableRow.put(variablesList.get(j), binaryValue);
            }

            truthTable.add(tableRow);
            tableRow = new HashMap<>();
        }
        return truthTable;
    }


}
