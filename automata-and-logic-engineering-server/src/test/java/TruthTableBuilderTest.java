import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class TruthTableBuilderTest {
    private AssignmentsCalculations assignmentsCalculations;
    private List<Formula> formulas;

    @Before
    public void setUp() {
        formulas = TestHelper.generateFormulaMocks();
        assignmentsCalculations = new AssignmentsCalculations(formulas.get(0));
    }

    @Test
    public void truthTableGeneration() {


        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();


    }

    @Test
    public void tableDataGeneration() {

    }

    @Test
    public void hashGeneration() {
        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();
        assignmentTwoResult.getHashCode();
    }

}
