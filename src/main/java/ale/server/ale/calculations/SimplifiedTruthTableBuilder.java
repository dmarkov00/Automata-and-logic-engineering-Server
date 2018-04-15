package ale.server.ale.calculations;

import ale.server.ale.calculations.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplifiedTruthTableBuilder {

    private List<Map<Character, Character>> notSimplifiedTruthTable;

    public SimplifiedTruthTableBuilder(List<Map<Character, Integer>> truthTable) {

        notSimplifiedTruthTable = Utils.convertTruthTableType(truthTable);
    }

    public List<Map<Character, Character>> simplifyTruthTable() {


        List<Map<Character, Character>> simplifiedTruthTable = new ArrayList<>();

        // Separates rows that evaluate to true with rows that evaluate to false, returns as list with 2 elements
        List<List<Map<Character, Character>>> separatedResults = Utils.separateFalseFromTrueResultsFromTruthTable(notSimplifiedTruthTable);

        // Set the results that evaluate to true
        List<Map<Character, Character>> notSimplifiedTruthTableWithTrueResults = separatedResults.get(0);

        // Set the results that evaluate to false, used to be merged with the final simplified table
        List<Map<Character, Character>> truthTableWithFalseResult = separatedResults.get(1);

        while (true) {
            for (int i = 0; i < notSimplifiedTruthTableWithTrueResults.size() - 1; i++) {

                for (int j = i + 1; j < notSimplifiedTruthTableWithTrueResults.size(); j++) {

                    // Retrieve simplified row from two other rows
                    Map<Character, Character> simplifiedRow = simplifyRows(notSimplifiedTruthTableWithTrueResults.get(i), notSimplifiedTruthTableWithTrueResults.get(j));

                    // If the row couldn't be simplified a null is returned
                    if (simplifiedRow != null) {

                        if (!isDuplicate(simplifiedTruthTable, simplifiedRow)) {
                            simplifiedTruthTable.add(simplifiedRow);
                        }
                    }
                }
            }

            // If nothing was simplified
            if (simplifiedTruthTable.size() == 0) {
                simplifiedTruthTable = notSimplifiedTruthTableWithTrueResults;
                break;
            } else {
                notSimplifiedTruthTableWithTrueResults = simplifiedTruthTable;
                simplifiedTruthTable = new ArrayList<>();
            }
        }

        // The final result consists of the combination between the false results and the simplified true results
        List<Map<Character, Character>> finalResult = new ArrayList<>();
        finalResult.addAll(truthTableWithFalseResult);
        finalResult.addAll(simplifiedTruthTable);

        return finalResult;
    }

    /**
     * Checks if the passed rows have 1 value that is different, if true it returns a simplified row
     */
    private Map<Character, Character> simplifyRows(Map<Character, Character> rowOne, Map<Character, Character> rowTwo) {
        rowOne = new HashMap<>(rowOne);
        int differentValuesCount = 0;
        char differentValueKey = ' ';
        for (char key : rowOne.keySet()) {
            if (rowOne.get(key) != rowTwo.get(key)) {
                differentValuesCount++;
                differentValueKey = key;
            }

        }
        if (differentValuesCount == 1) {

            Map<Character, Character> simplifiedRow = rowOne;
            simplifiedRow.replace(differentValueKey, '*');
            return simplifiedRow;
        }
        return null;
    }


    /**
     * Checks for duplicated simplifications
     */
    private boolean isDuplicate(List<Map<Character, Character>> simplifiedTruthTable, Map<Character, Character> row) {
        int differentValuesCount;
        for (Map<Character, Character> tableRow : simplifiedTruthTable) {
            differentValuesCount = 0;
            for (Character key : tableRow.keySet()) {
                if (tableRow.get(key) != row.get(key)) {
                    differentValuesCount++;
                }
            }
            if (differentValuesCount == 0) {
                return true;
            }
        }
        return false;
    }


}
