package ale.server.ale.calculations;

import java.util.*;

public class Utils {

    public static boolean isNotVariable(Node node) {
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~');
        return bannedChars.contains(node.getValue());
    }

    /**
     * Extracts the unique variables required to generate the truth table
     *
     * @param formulaTree the generated tree from the formula
     * @return List of unique formula variables
     */
    public static List<Character> getUniqueTreeVariables(FormulaTree formulaTree) {
        // Get the elements of the tree
        Node[] arrayTree = formulaTree.getArrayTree();

        List<Character> variablesList = new ArrayList<>();

        // Do not consider these characters for variables
        List<Character> bannedChars = new ArrayList<>(Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~'));

        for (Node node : arrayTree) {
            if (node != null) {
                if (!bannedChars.contains(node.getValue())) {
                    variablesList.add(node.getValue());
                    bannedChars.add(node.getValue());
                }
            }
        }
        // Sort the variables in the list alphabetically
        variablesList = Utils.sortListAlphabetically(variablesList);
        return variablesList;
    }

    public static List<Character> sortListAlphabetically(List<Character> unsortedVariables) {
        java.util.Collections.sort(unsortedVariables);

        List<Character> sortedVariables = unsortedVariables;

        return sortedVariables;

    }

    /**
     * Converts truth table from List<Map<Character, Integer>> to List<Map<Character, Character>>
     */
    public static List<Map<Character, Character>> convertTruthTableType(List<Map<Character, Integer>> truthTable) {
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

    /**
     * Retrieves the rows that evaluate to true
     */
    public static List<List<Map<Character, Character>>> separateFalseFromTrueResultsFromTruthTable(List<Map<Character, Character>> truthTable) {


        List<Map<Character, Character>> truthTableWithTrueResults = new ArrayList<>();
        List<Map<Character, Character>> truthTableWithFalseResults = new ArrayList<>();


        for (Map<Character, Character> tableRow : truthTable) {
            if (tableRow.get('=') == '1') {

                truthTableWithTrueResults.add(tableRow);
            } else {
                truthTableWithFalseResults.add(tableRow);
            }
        }
        List<List<Map<Character, Character>>> separatedResults = new ArrayList<>();

        separatedResults.add(truthTableWithTrueResults);
        separatedResults.add(truthTableWithFalseResults);

        return separatedResults;

    }
}
