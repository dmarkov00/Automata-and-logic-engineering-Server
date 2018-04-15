package ale.server.ale.calculations.utils;

import ale.server.models.*;

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
                return this.getAssignmentFourResult();

            case 5:
                return this.getAssignmentFiveResult();
            default:

        }
        return null;
    }

    private AssignmentFiveResult getAssignmentFiveResult() {
        return assignmentsCalculations.generateAssignmentFiveResult();
    }

    private AssignmentFourResult getAssignmentFourResult() {
        return assignmentsCalculations.generateAssignmentFourResult();
    }

    private AssignmentThreeResult getAssignmentThreeResult() {
        return assignmentsCalculations.generateAssignmentThreeResult();
    }

    private AssignmentOneResult getAssignmentOneResult() {
        return assignmentsCalculations.generateAssignmentOneResult();
    }

    private AssignmentTwoResult getAssignmentTwoResult() {
        return assignmentsCalculations.generateAssignmentTwoResult();
    }


}
