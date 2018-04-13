package ale.server.ale.calculations;

import java.math.BigInteger;
import java.util.*;

class TruthTableBuilder {
    TruthTableBuilder(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;
    }

    private FormulaTree formulaTree;


    public List<Map<Character, Integer>> generateTruthTable() {

        List<Map<Character, Integer>> truthTable;
        // Add the different binary values for each variable
        truthTable = fillTruthTableWithVariableData();

        for (Map<Character, Integer> tableRow : truthTable) {
            Node[] arrayTree = setBinaryValuesInArrayTree(tableRow);
            int formulaEvaluationResult = evaluateTree(0, arrayTree);

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

    /**
     * Binary tree evaluation
     */
    private int evaluateTree(int rootIndex, Node[] arrayTree) {

        if (!formulaTree.nodeHasLeftChild(rootIndex) & !formulaTree.nodeHasRightChild(rootIndex)) {

            return arrayTree[rootIndex].getBinaryValue();
        }
        int leftBinaryValue;
        int rightBinaryValue;

        if (!formulaTree.nodeHasLeftChild(rootIndex)) {
            leftBinaryValue = -1;
        } else {
            leftBinaryValue = evaluateTree(formulaTree.getLeftChildIndex(rootIndex), arrayTree);
        }
        if (!formulaTree.nodeHasRightChild(rootIndex)) {
            rightBinaryValue = -1;
        } else {
            rightBinaryValue = evaluateTree(formulaTree.getRightChildIndex(rootIndex), arrayTree);
        }

        if (Utils.isOperator(arrayTree[rootIndex])) {
            int evaluatedValue = getBinaryResult(arrayTree[rootIndex], leftBinaryValue, rightBinaryValue);
            arrayTree[rootIndex].setBinaryValue(evaluatedValue);

            return evaluatedValue;
        }
        return arrayTree[rootIndex].getBinaryValue();

    }

    private int getBinaryResult(Node root, int left, int right) {
        char rootValue = root.getValue();
        switch (rootValue) {
            case '|':
                return left | right;
            case '&':
                return left & right;
            case '~':
                if (right == -1) {
                    if (left == 0) {
                        return 1;
                    } else if (left == 1) {
                        return 0;
                    }
                } else if (left == -1) {
                    if (right == 0) {
                        return 1;
                    } else if (right == 1) {
                        return 0;
                    }
                }

            case '=':
                if (left == right) {
                    return 1;
                } else {
                    return 0;
                }
            case '%':
                if ((left & right) == 1) {
                    return 0;
                } else {
                    return 1;
                }
            default:
                // Here comes the '>' operator

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
        for (int i = truthTable.size() - 1; i >= 0; i--) {
            binaryString += truthTable.get(i).get('=');
        }
        BigInteger toNr = new BigInteger(binaryString, 2);

        return toNr.toString(16);
    }
}
