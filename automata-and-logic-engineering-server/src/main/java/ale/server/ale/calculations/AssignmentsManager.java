package ale.server.ale.calculations;

import ale.server.models.AssignmentOneResult;
import ale.server.models.AssignmentResult;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;

public class AssignmentsManager {

    private AssignmentsCalculations assignmentsCalculations;

    public AssignmentResult generateAssignmentResult(Formula formula, int assignmentNr) {

        assignmentsCalculations = new AssignmentsCalculations(formula);


        switch (assignmentNr) {
            case 1:
                return this.getAssignmentOneResult();

            case 2:
                return this.getAssignmentTwoResult();
            case 3:
                return this.getAssignmentThreeResult();

            case 4:

                break;
            case 5:

                break;
            default:

        }
        return null;
    }

    private AssignmentResult getAssignmentThreeResult() {
        assignmentsCalculations.generateAssignmentThreeResult();
    }

    private AssignmentOneResult getAssignmentOneResult() {
        return assignmentsCalculations.generateAssignmentOneResult();
    }

    private AssignmentTwoResult getAssignmentTwoResult() {
        return assignmentsCalculations.generateAssignmentTwoResult();
    }


}
