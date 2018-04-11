import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentFourResult;
import ale.server.models.Formula;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DisjunctiveNormalFormTest {

    @Test
    public void disjunctiveNormalFormGenerationFormula1() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn(">(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);

        AssignmentFourResult assignmentFourResult = assignmentsCalculations.generateAssignmentFourResult();
        System.out.println("");
    }

}
