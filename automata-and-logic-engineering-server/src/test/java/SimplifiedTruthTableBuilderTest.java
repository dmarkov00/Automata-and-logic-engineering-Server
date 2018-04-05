import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentThreeResult;
import ale.server.models.Formula;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimplifiedTruthTableBuilderTest {

    @Test
    public void truthTableSimplification(){

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("|(|(A,B),C)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentThreeResult assignmentThreeResult = assignmentsCalculations.generateAssignmentThreeResult();

        List<Map<Character,Character>> simplifiedTruthTable = assignmentThreeResult.getSimplifiedTableResults();



    }
}
