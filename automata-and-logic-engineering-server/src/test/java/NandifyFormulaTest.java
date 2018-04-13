import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentFiveResult;
import ale.server.models.Formula;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NandifyFormulaTest {

    @Test
    public void nandifiedFormulaGeneration() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=( >(A,B), |( ~(A) ,B) )");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);

        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
    }
}
