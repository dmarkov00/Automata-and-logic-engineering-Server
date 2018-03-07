package ale.server.ale.calculations;

import ale.server.models.AssignmentOneResult;
import ale.server.models.AssignmentResult;
import ale.server.models.Formula;

public class Assignments {
    private Utils utils;

    public AssignmentResult generateAssignmentResult(Formula formula, int assignmentNr) {


        utils = new Utils();

        utils.readFormula(formula);

        switch (assignmentNr) {
            case 1:
                return this.generateAssignmentOneResult();

            case 2:
                return this.generateAssignmentTwoResult();
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            default:

        }
        return null;
    }

    private AssignmentResult generateAssignmentTwoResult() {
        return null;
    }

    private AssignmentResult generateAssignmentOneResult() {

        utils.generateGraph();

        return new AssignmentOneResult();
    }
}
