package ale.server.ale.calculations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static boolean isNotVariable(Node node) {
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~');
        return bannedChars.contains(node.getValue());
    }

    public static List<Character> getUniqueTreeVariables(FormulaTree formulaTree) {
        Node[] arrayTree = formulaTree.getArrayTree();

        List<Character> variablesList = new ArrayList<>();
        List<Character> bannedChars = new ArrayList<>(Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~'));
        for (Node node : arrayTree) {
            if (node != null) {
                if (!bannedChars.contains(node.getValue())) {
                    variablesList.add(node.getValue());
                    bannedChars.add(node.getValue());
                }

            }
        }
        // Sort the variables in the list
        variablesList = Utils.sortListAlphabetically(variablesList);
        return variablesList;
    }

    public static List<Character> sortListAlphabetically(List<Character> unsortedVariables) {
        java.util.Collections.sort(unsortedVariables);

        List<Character> sortedVariables = unsortedVariables;

        return sortedVariables;

    }
}
