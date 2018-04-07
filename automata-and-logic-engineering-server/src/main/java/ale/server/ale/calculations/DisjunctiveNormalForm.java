package ale.server.ale.calculations;

import java.util.List;
import java.util.Map;

public class DisjunctiveNormalForm {

    private List<Map<Character, Character>> truthTable;
    private List<Map<Character, Character>> simplifiedTruthTable;

    DisjunctiveNormalForm(List<Map<Character, Integer>> truthTable, List<Map<Character, Character>> simplifiedTruthTable) {
        this.truthTable = Utils.convertTruthTableType(truthTable);
        this.simplifiedTruthTable = simplifiedTruthTable;

    }

    public List<String> generateDisjunctiveNormalForms() {
        return null;
    }
}
