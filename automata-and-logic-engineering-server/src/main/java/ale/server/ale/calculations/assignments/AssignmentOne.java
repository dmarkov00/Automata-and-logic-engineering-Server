package ale.server.ale.calculations.assignments;

import ale.server.ale.calculations.FormulaTree;
import ale.server.models.AssignmentOneResult;

import java.io.UnsupportedEncodingException;

public final class AssignmentOne {

    private static FormulaTree formulaTree = new FormulaTree();


    public static void readFormula(StringBuilder formula) {

        formulaTree.addNode(formula.charAt(0));

        formula.deleteCharAt(0);

        if (formula.length() == 0) return;


        readFormula(formula);

    }

    public static void generateGraph() {
        formulaTree.generateImageFile();
    }

    public static AssignmentOneResult getAssignmentOneResult(String formula) {

        StringBuilder formulaString = new StringBuilder(formula);
        AssignmentOne.readFormula(formulaString);
        AssignmentOne.generateGraph();

        AssignmentOneResult result = new AssignmentOneResult();
        return result;

    }

}
























