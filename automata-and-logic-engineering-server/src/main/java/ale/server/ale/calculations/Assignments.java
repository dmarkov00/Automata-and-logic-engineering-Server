package ale.server.ale.calculations;

import ale.server.models.AssignmentOneResult;
import ale.server.models.AssignmentResult;

public final class Assignments {

    public static AssignmentResult generateAssignmentOneResult(String formula) {

        StringBuilder formulaString = new StringBuilder(formula);

        Utils utils = new Utils();

        utils.readFormula(formulaString);

        utils.formulaTree.generateGraph();


        return new AssignmentOneResult();
    }
}
