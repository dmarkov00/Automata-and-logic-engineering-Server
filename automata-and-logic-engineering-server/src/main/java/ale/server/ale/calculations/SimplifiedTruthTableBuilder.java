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
        for (int i = 0; i < notSimplifiedTruthTable.size() - 1; i++) {
            for (int j = i + 1; j < notSimplifiedTruthTable.size(); j++) {
                simplifiedTruthTable.get(i)
            }

        }

        for (Map<Character, Integer> tableRow : notSimplifiedTruthTable) {

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

    private boolean areRowsSimplifiable(Map<Character, Integer> rowOne, Map<Character, Integer> rowTwo) {
        return false;
    }


}
