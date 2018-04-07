package ale.server.ale.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplifiedTruthTableBuilder {

    private List<Map<Character, Character>> notSimplifiedTruthTable;

    // Used to be merged with the final simplified table
    private List<Map<Character, Character>> truthTableWithFalseResult = new ArrayList<>();

    // Used when a true row couldn't be simplified, so we indicate and store this row for later display
    private boolean rowWasSimplified = false;

    private List<Map<Character, Character>> notFullySimplifiedRows = new ArrayList<>();

    SimplifiedTruthTableBuilder(List<Map<Character, Integer>> truthTable) {

        notSimplifiedTruthTable = this.convertTruthTableType(truthTable);
    }

    public List<Map<Character, Character>> simplifyTruthTable() {


        List<Map<Character, Character>> simplifiedTruthTable = new ArrayList<>();

        // Filter out the results that evaluated to false
        List<Map<Character, Character>> notSimplifiedTruthTableWithTrueResults = getTrueResultsFromTruthTable();

        while (true) {
            for (int i = 0; i < notSimplifiedTruthTableWithTrueResults.size() - 1; i++) {

                for (int j = i + 1; j < notSimplifiedTruthTableWithTrueResults.size(); j++) {

                    // Retrieve simplified row from two other rows
                    Map<Character, Character> simplifiedRow = simplifyRows(notSimplifiedTruthTableWithTrueResults.get(i), notSimplifiedTruthTableWithTrueResults.get(j));

                    // If the row couldn't be simplified a null is returned
                    if (simplifiedRow != null) {

                        if (!isDuplicate(simplifiedTruthTable, simplifiedRow)) {
                            simplifiedTruthTable.add(simplifiedRow);

                            rowWasSimplified = true;

                        }
                    }
                }
                if (!rowWasSimplified) {
                    notFullySimplifiedRows.add(notSimplifiedTruthTableWithTrueResults.get(i));
                }
                rowWasSimplified = false;
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
//        finalResult.addAll(notFullySimplifiedRows);
        finalResult.addAll(simplifiedTruthTable);

        return finalResult;
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


    private Map<Character, Character> simplifyRows(Map<Character, Character> rowOne, Map<Character, Character> rowTwo) {

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
     * Retrieves the rows that evaluate to true
     */
    private List<Map<Character, Character>> getTrueResultsFromTruthTable() {
        List<Map<Character, Character>> notSimplifiedTruthTableWithTrueResults = new ArrayList<>();

        for (Map<Character, Character> tableRow : notSimplifiedTruthTable) {
            if (tableRow.get('=') == '1') {

                notSimplifiedTruthTableWithTrueResults.add(tableRow);
            } else {
                truthTableWithFalseResult.add(tableRow);
            }
        }
        return notSimplifiedTruthTableWithTrueResults;
    }

    /**
     * Converts truth table from List<Map<Character, Integer>> to List<Map<Character, Character>>
     */
    private List<Map<Character, Character>> convertTruthTableType(List<Map<Character, Integer>> truthTable) {
        List<Map<Character, Character>> convertedTruthTable = new ArrayList<>();

        for (Map<Character, Integer> tableRow : truthTable) {

            Map<Character, Character> convertedMap = new HashMap<>();
            for (Map.Entry<Character, Integer> entry : tableRow.entrySet()) {
                convertedMap.put(entry.getKey(), (char) (entry.getValue() + '0'));
            }
            convertedTruthTable.add(convertedMap);
        }
        return convertedTruthTable;
    }


}
