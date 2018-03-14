package ale.server.ale.calculations;

import java.util.Arrays;
import java.util.List;

class Utils {

    private boolean isNotVariable(Node node) {
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~');
        return bannedChars.contains(node.getValue());
    }
}
