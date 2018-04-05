package ale.server.ale.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplifiedTruthTableBuilder {

    private List<Map<Character, Character>> notSimplifiedTruthTable;

    SimplifiedTruthTableBuilder(List<Map<Character, Integer>> truthTable) {

        notSimplifiedTruthTable = this.convertTruthTableType(truthTable);
    }

    public List<Map<Character, Character>> simplifyTruthTable() {


        List<Map<Character, Character>> simplifiedTruthTable = new ArrayList<>();

        // Filter out the results that evaluated to false
        List<Map<Character, Character>> notSimplifiedTruthTableWithTrueResults = getTrueResultsFromTruthTable();

        boolean initialSimplificationsDone = false;

        while (true) {
            for (int i = 0; i < notSimplifiedTruthTableWithTrueResults.size() - 1; i++) {
                for (int j = i + 1; j < notSimplifiedTruthTableWithTrueResults.size(); j++) {


                    if (initialSimplificationsDone) {
                        Map<Character, Character> simplifiedRow = simplifyRowsWithAsterisks(notSimplifiedTruthTableWithTrueResults.get(i), notSimplifiedTruthTableWithTrueResults.get(j));


                    } else {
                        Map<Character, Character> simplifiedRow = simplifyRowsWithoutAsterisks(notSimplifiedTruthTableWithTrueResults.get(i), notSimplifiedTruthTableWithTrueResults.get(j));

                        // If the row couldn't be simplified a null is returned
                        if (simplifiedRow != null) {
                            if (!isDuplicate(simplifiedTruthTable, simplifiedRow)) {
                                simplifiedTruthTable.add(simplifiedRow);
                            }
                        }
                    }

                }
            }
//            initialSimplificationsDone = true;
            if (simplifiedTruthTable.size() == 0) {
                simplifiedTruthTable = notSimplifiedTruthTableWithTrueResults;
                break;
            } else {
                notSimplifiedTruthTableWithTrueResults = simplifiedTruthTable;
                simplifiedTruthTable = new ArrayList<>();
            }
        }
        return simplifiedTruthTable;
    }

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


    private boolean verifySimplifiedRowWithTruthTable(Map<Character, Character> simplifiedRow) {
        return false;
    }

    private Map<Character, Character> simplifyRowsWithAsterisks(Map<Character, Character> rowOne, Map<Character, Character> rowTwo) {
        return null;
    }

    private Map<Character, Character> simplifyRowsWithoutAsterisks(Map<Character, Character> rowOne, Map<Character, Character> rowTwo) {

        int differentValuesCount = 0;
        char differentValueKey = ' ';
        for (char key : rowOne.keySet()) {
            if (rowOne.get(key) != rowTwo.get(key)) {
                differentValuesCount++;
                differentValueKey = key;
            }

        }
        if (differentValuesCount == 1) {

            Map<Character, Character> simplifiedRow = new HashMap<>(rowOne);
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
            if (tableRow.get('=') == 1) {

                notSimplifiedTruthTableWithTrueResults.add(tableRow);
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
                convertedMap.put(entry.getKey(), (char) ((int) entry.getValue()));
            }
            convertedTruthTable.add(convertedMap);
        }
        return convertedTruthTable;
    }


}
