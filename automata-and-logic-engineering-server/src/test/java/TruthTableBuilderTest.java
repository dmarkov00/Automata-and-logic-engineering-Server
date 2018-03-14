import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.Formula;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TruthTableBuilderTest {


    @Test
    public void truthTableGeneration() {

        Formula formulaMock = mock(Formula.class);
        when(formulaMock.getFormula()).thenReturn("=(>(G,B),|(~(A),Z))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(formulaMock);

        assignmentsCalculations.generateAssignmentTwoResult();

    }

}
