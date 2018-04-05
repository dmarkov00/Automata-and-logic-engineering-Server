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

        // Set the initial value of the simplified truth table
//

        while (true) {
            for (int i = 0; i < notSimplifiedTruthTableWithTrueResults.size() - 1; i++) {
                for (int j = i + 1; j < notSimplifiedTruthTableWithTrueResults.size(); j++) {


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
