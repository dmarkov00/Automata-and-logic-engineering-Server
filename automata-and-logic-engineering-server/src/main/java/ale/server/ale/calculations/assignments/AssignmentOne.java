package ale.server.ale.calculations.assignments;

import ale.server.ale.calculations.FormulaTree;
import ale.server.ale.calculations.Utils;
import ale.server.models.AssignmentOneResult;

public final class AssignmentOne {


    public static AssignmentOneResult getAssignmentOneResult(String formula) {

        StringBuilder formulaString = new StringBuilder(formula);

        Utils utils = new Utils();

        utils.readFormula(formulaString);

        utils.formulaTree.generateGraph();


        return new AssignmentOneResult();
    }

}
























