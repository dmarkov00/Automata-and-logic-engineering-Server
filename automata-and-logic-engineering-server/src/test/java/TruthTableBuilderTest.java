import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.Formula;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TruthTableBuilderTest {


    @Test
    public void truthTableGeneration() {

        Formula formulaMock = mock(Formula.class);
        when(formulaMock.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(formulaMock);

        assignmentsCalculations.generateAssignmentTwoResult();

    }

}
