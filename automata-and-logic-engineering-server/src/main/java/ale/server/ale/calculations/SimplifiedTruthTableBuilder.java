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

        while (true) {
            for (int i = 0; i < notSimplifiedTruthTableWithTrueResults.size() - 1; i++) {
                for (int j = i + 1; j < notSimplifiedTruthTableWithTrueResults.size(); j++) {

                    Map<Character, Character> simplifiedRow = simplifyRows(notSimplifiedTruthTableWithTrueResults.get(i), notSimplifiedTruthTableWithTrueResults.get(j));

                    // If the row couldn't be simplified a null is returned
                    if (simplifiedRow != null) {

                        // Verify every new row with the truth table
                        if (verifySimplifiedRowWithTruthTable(simplifiedRow)) {
                            simplifiedTruthTable.add(simplifiedRow);
                        }
                    }
                }
            }

            if (simplifiedTruthTable.size() == 0) {
                break;
            } else {
                notSimplifiedTruthTableWithTrueResults = simplifiedTruthTable;
                simplifiedTruthTable = new ArrayList<>();
            }
        }

        return simplifiedTruthTable;
    }

    private Map<Character, Character> simplifyRows(Map<Character, Character> rowOne, Map<Character, Character> rowTwo) {
        if (rowContainsAsterisk) {
            return null;

        } else {
            int differentValuesCount = 0;
            char differentValueKey = ' ';
            for (char key : rowOne.keySet()) {
                if (rowOne.get(key) != rowTwo.get(key)) {
                    differentValuesCount++;
                    differentValueKey = key;
                }

            }
            if (differentValuesCount == 1) {
                rowOne.replace(differentValueKey, '*');

                Map<Character, Character> simplifiedRow = rowOne;
                return simplifiedRow;
            }
            return null;
        }
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
