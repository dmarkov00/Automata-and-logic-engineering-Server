package ale.server.ale.calculations;

import ale.server.models.AssignmentFourResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisjunctiveNormalForm {

    private List<Map<Character, Character>> truthTable;
    private List<Map<Character, Character>> simplifiedTruthTable;

    DisjunctiveNormalForm(List<Map<Character, Integer>> truthTable, List<Map<Character, Character>> simplifiedTruthTable) {
        this.truthTable = Utils.convertTruthTableType(truthTable);
        this.simplifiedTruthTable = simplifiedTruthTable;

    }

    public String generateDisjunctiveNormalForm(List<Map<Character, Character>> truthTable) {
        StringBuilder disjunctiveNormalFormTruthTable;
//        for (int i = 0; i < truthTable.size(); i++) {
//
//            List<String> rowVariable = retrieveRowVariables(truthTable.get(i));
//
//            if (rowVariable.size() == 1) {
//                disjunctiveNormalFormTruthTable.append("&(").append(rowVariable.get(0)).append(")");
//
//            }
//            else if ()
//
//        }

        return null;
    }

    public AssignmentFourResult generateDisjunctiveNormalForms() {

        // Get the only the true rows from the truth table
        List<Map<Character, Character>> truthTableWithTrueValues = Utils.separateFalseFromTrueResultsFromTruthTable(truthTable).get(0);
        List<Map<Character, Character>> simplifiedTruthTableWithTrueValues = Utils.separateFalseFromTrueResultsFromTruthTable(simplifiedTruthTable).get(0);


        String normalFormTruthTable = generateDisjunctiveNormalForm(truthTableWithTrueValues);
        String normalFormSimplifiedTruthTable = generateDisjunctiveNormalForm(simplifiedTruthTableWithTrueValues);

        return new AssignmentFourResult(normalFormTruthTable, normalFormSimplifiedTruthTable);
    }

    private List<String> retrieveRowVariables(Map<Character, Character> tableRow) {
        List<String> rowVariables = new ArrayList<>();

        for (Map.Entry<Character, Character> variable : tableRow.entrySet()) {
            if (variable.getValue() == '0') {
                rowVariables.add("~(" + variable.getKey() + ")");
            } else {
                rowVariables.add(variable.getKey() + "");
            }
        }
        return rowVariables;
    }
}
