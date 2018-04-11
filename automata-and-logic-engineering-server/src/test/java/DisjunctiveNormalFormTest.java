import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentFourResult;
import ale.server.models.Formula;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Testing will be applied by generating the disjunctive normal form and then it will be passed
 * for truth table generation and comparison with the truth table generated by the initial formula
 */
public class DisjunctiveNormalFormTest {

    @Test
    public void disjunctiveNormalFormGenerationFormula1() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn(">(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);

        AssignmentFourResult assignmentFourResult = assignmentsCalculations.generateAssignmentFourResult();

        // r
        String disjunctiveNormalFormTruthTable = assignmentFourResult.getDisjunctiveNormalFormTruthTable();



    }

}
