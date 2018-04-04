import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * If assignment calculation returns null, this meas that the formula was incorrect
 */
public class FormulaReaderTest {

    @Test
    public void badFormulasRejectionFormula1() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(A,1)");

        // Call random assignment
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        Assert.assertNull(assignmentTwoResult);

    }

    @Test
    public void badFormulasRejectionFormula2() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("#(A,B)");

        // Call random assignment
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        Assert.assertNull(assignmentTwoResult);

    }

    @Test
    public void badFormulasRejectionFormula3() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(A,? )");

        // Call random assignment
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        Assert.assertNull(assignmentTwoResult);

    }

    /**
     * Spaces are allowed, they are filtered out afterwards
     */
    @Test
    public void badFormulasRejectionFormulaWithSpaces() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&  ( A, B ) ");

        // Call random assignment
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentTwoResult assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();
        // Should not be null
        Assert.assertNotNull(assignmentTwoResult);

    }
}
