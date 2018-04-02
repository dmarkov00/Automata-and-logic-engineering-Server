package ale.server.ale.calculations;

import java.util.*;

class TruthTableBuilder {
    TruthTableBuilder(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    private FormulaTree formulaTree;

    /**
     * @return
     */
    public List<Map<Character, Integer>> generateTruthTable() {

        List<Map<Character, Integer>> truthTable;
        // Add the different binary values for each variable
        truthTable = fillTruthTableWithVariableData();

        for (Map<Character, Integer> tableRow : truthTable) {
            Node[] arrayTree = setBinaryValuesInArrayTree(tableRow);
            int formulaEvaluationResult = evaluateTree(0, 0, arrayTree);

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


    private int evaluateTree(int rootIndex, int evaluatedValue, Node[] arrayTree) {

        if (!formulaTree.nodeHasLeftChild(rootIndex) & !formulaTree.nodeHasRightChild(rootIndex)) {
            evaluatedValue = arrayTree[rootIndex].getBinaryValue();

            return evaluatedValue;
        }

        int leftBinaryValue = evaluateTree(formulaTree.getLeftChildIndex(rootIndex), evaluatedValue, arrayTree);
        int rightBinaryValue = evaluateTree(formulaTree.getRightChildIndex(rootIndex), evaluatedValue, arrayTree);

        if (Utils.isNotVariable(arrayTree[rootIndex])) {
            evaluatedValue = getBinaryResult(arrayTree[rootIndex], leftBinaryValue, rightBinaryValue);
            arrayTree[rootIndex].setBinaryValue(evaluatedValue);

            return evaluatedValue;
        }
        evaluatedValue = arrayTree[rootIndex].getBinaryValue();
        return evaluatedValue;

    }

    private int getBinaryResult(Node root, int left, int right) {
        char rootValue = root.getValue();
        switch (rootValue) {
            case '|':
                return left | right;
            case '&':
                return left & right;
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


    private Node[] setBinaryValuesInArrayTree(Map<Character, Integer> tableRow) {
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
     *
     * @return A list of maps. Each list values is a row in the truth table and the map represents the values in this row
     */
    private List<Map<Character, Integer>> fillTruthTableWithVariableData() {
        // Declarations
        List<Map<Character, Integer>> truthTable = new ArrayList<>();
        Map<Character, Integer> tableRow = new HashMap<>();

        // Extracting the uniques variables from the passed formula
        List<Character> variablesList = Utils.getUniqueTreeVariables(formulaTree);

        // Calculations need to generate the table
        int nrOfVariables = variablesList.size();
        int nrOfRows = (int) Math.pow(2, nrOfVariables);

        // Actual binary values generations
        for (int i = 0; i < nrOfRows; i++) {
            for (int j = nrOfVariables - 1, positionCounter = 0; j >= 0; j--, positionCounter++) {
                int binaryValue = ((i / (int) Math.pow(2, j)) % 2);

                tableRow.put(variablesList.get(positionCounter), binaryValue);
            }

            truthTable.add(tableRow);
            tableRow = new HashMap<>();
        }
        return truthTable;
    }


    public String generateHash(List<Map<Character, Integer>> truthTable) {
        String binaryString = "";
        for (int i = truthTable.size() - 1; i > 0; i--) {
            binaryString += truthTable.get(i).get('=');
        }
        int decimal = Integer.parseInt(binaryString, 2);
        String hexString = Integer.toString(decimal, 16);
        return hexString;
    }
}
