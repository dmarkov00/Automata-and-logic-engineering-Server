package ale.server.ale.calculations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
