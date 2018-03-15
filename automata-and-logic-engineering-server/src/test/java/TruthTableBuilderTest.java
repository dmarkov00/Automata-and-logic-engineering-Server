import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TruthTableBuilderTest {
    private AssignmentsCalculations assignmentsCalculations;

    @Before
    public void setUp() {
        Formula formulaMock = mock(Formula.class);
        when(formulaMock.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        assignmentsCalculations = new AssignmentsCalculations(formulaMock);
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
