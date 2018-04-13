package ale.server.ale.calculations;

import ale.server.ale.calculations.utils.Utils;
import ale.server.models.AssignmentFourResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisjunctiveNormalForm {

    private List<Map<Character, Character>> truthTable;
    private List<Map<Character, Character>> simplifiedTruthTable;

    DisjunctiveNormalForm(List<Map<Character, Integer>> truthTable, List<Map<Character, Character>> simplifiedTruthTable) {
        this.truthTable = Utils.convertTruthTableType(truthTable);
        this.simplifiedTruthTable = simplifiedTruthTable;

    }

    private String generateDisjunctiveNormalForm(List<Map<Character, Character>> truthTable) {
        List<String> rowVariables;
        List<String> listOfNormalizedRows = new ArrayList<>();
        String normalizedRow = "";
        for (Map<Character, Character> tableRow : truthTable) {

            // Convert variables from the map based on their values to List of stings
            rowVariables = retrieveRowVariables(tableRow);

            // Separates each row variable with '&' operator
            normalizedRow = normalizeRow(rowVariables, "&").toString();
            listOfNormalizedRows.add(normalizedRow);
        }

        // Separates each normalized table row with '|' operator
        return normalizeRow(listOfNormalizedRows, "|").toString();
    }

    private StringBuilder normalizeRow(List<String> rowVariables, String operator) {
        StringBuilder normalizedRow = new StringBuilder();

        if (rowVariables.size() == 1) {
            normalizedRow.append(rowVariables.get(0));
            return normalizedRow;
        }
        if (rowVariables.size() == 0) {
            return normalizedRow;
        }
        for (int i = 0; i < rowVariables.size(); i++) {

            // If it is not that last variable
            if (i < rowVariables.size() - 1) {
                normalizedRow.append(operator).append("(").append(rowVariables.get(i)).append(",");

                // If it is the last variable
            } else {
                normalizedRow.append(rowVariables.get(i));
                // Add as many closing brackets are needed
                for (int c = 0; c < rowVariables.size() - 1; c++) {
                    normalizedRow.append(")");
                }
            }
        }
        return normalizedRow;
    }

    public AssignmentFourResult generateDisjunctiveNormalForms() {

        // Get the only the true rows from the truth table
        List<Map<Character, Character>> truthTableWithTrueValues = Utils.separateFalseFromTrueResultsFromTruthTable(truthTable).get(0);
        List<Map<Character, Character>> simplifiedTruthTableWithTrueValues = Utils.separateFalseFromTrueResultsFromTruthTable(simplifiedTruthTable).get(0);


        String normalFormTruthTable = generateDisjunctiveNormalForm(truthTableWithTrueValues);
        String normalFormSimplifiedTruthTable = generateDisjunctiveNormalForm(simplifiedTruthTableWithTrueValues);

        return new AssignmentFourResult(normalFormTruthTable, normalFormSimplifiedTruthTable);
    }

    private List<String> retrieveRowVariables(Map<Character, Character> tableRow) {
        List<String> rowVariables = new ArrayList<>();

        for (Map.Entry<Character, Character> variable : tableRow.entrySet()) {
            if (variable.getValue() == '0') {
                rowVariables.add("~(" + variable.getKey() + ")");
            } else if (variable.getValue() == '1' & variable.getKey() != '=') {
                rowVariables.add(variable.getKey() + "");
            }
        }
        return rowVariables;
    }
}
