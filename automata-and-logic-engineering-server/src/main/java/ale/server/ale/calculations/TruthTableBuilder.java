package ale.server.ale.calculations;

import java.util.*;

class TruthTableBuilder {
    TruthTableBuilder(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    private FormulaTree formulaTree;

    /**
     *
     * @return
     */
    public List<Map<Character, Byte>> generateTruthTable() {

        List<Map<Character, Byte>> truthTable;

        truthTable = fillTruthTableWithVariableData();
        for (Map<Character, Byte> tableRow : truthTable) {
            Node[] arrayTree = setBinaryValuesInArrayTree(tableRow);
            byte formulaEvaluationResult = evaluateTree(0, (byte) 1, arrayTree);

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


    private byte evaluateTree(int root, byte evaluatedValue, Node[] arrayTree) {

        if (!formulaTree.nodeHasLeftChild(root) & !formulaTree.nodeHasRightChild(root)) {
            return evaluatedValue;
        }

        byte leftBinaryValue = evaluateTree(formulaTree.getLeftChildIndex(root), evaluatedValue, arrayTree);
        byte rightBinaryValue = evaluateTree(formulaTree.getRightChildIndex(root), evaluatedValue, arrayTree);

        if (Utils.isNotVariable(arrayTree[root])) {
            evaluatedValue = getBinaryResult(arrayTree[root], arrayTree[leftBinaryValue].getBinaryValue(), arrayTree[rightBinaryValue].getBinaryValue());
            return evaluatedValue;
        }
        evaluatedValue = arrayTree[root].getBinaryValue();
        return evaluatedValue;

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

            default:
                if (left == 1 && right == 0) {
                    return 0;
                } else return 1;
        }

    }


    private Node[] setBinaryValuesInArrayTree(Map<Character, Byte> tableRow) {
        Node[] arrayTree = formulaTree.getArrayTree();
        for (Node node : arrayTree) {
            if (node != null) {
                if (tableRow.containsKey(node.getValue())) {
                    node.setBinaryValue(tableRow.get(node.getValue()));
                }
            }

        }
        return arrayTree;

    }

    /**
     * Fills in the truth table with all the different combinations, later used to compute formula result
     * @return A list of maps. Each list values is a row in the truth table and the map represents the values in this row
     */
    private List<Map<Character, Byte>> fillTruthTableWithVariableData() {
        // Declarations
        List<Map<Character, Byte>> truthTable = new ArrayList<>();
        Map<Character, Byte> tableRow = new HashMap<>();

        // Extracting the uniques variables from the passed formula
        List<Character> variablesList = Utils.getUniqueTreeVariables(formulaTree);

        // Calculations need to generate the table
        int nrOfVariables = variablesList.size();
        int nrOfRows = (int) Math.pow(2, nrOfVariables);

        // Actual binary values generations
        for (int i = 0; i < nrOfRows; i++) {
            for (int j = nrOfVariables - 1, positionCounter = 0; j >= 0; j--, positionCounter++) {
                byte binaryValue = (byte) ((i / (int) Math.pow(2, j)) % 2);

                tableRow.put(variablesList.get(positionCounter), binaryValue);
            }

            truthTable.add(tableRow);
            tableRow = new HashMap<>();
        }
        return truthTable;
    }


    public String generateHash(List<Map<Character, Byte>> truthTable) {
        String binaryString = "";
        for (int i = truthTable.size() - 1; i > 0; i--) {
            binaryString += truthTable.get(i).get('=');
        }
        int decimal = Integer.parseInt(binaryString, 2);
        String hexString = Integer.toString(decimal, 16);
        return hexString;
    }
}
