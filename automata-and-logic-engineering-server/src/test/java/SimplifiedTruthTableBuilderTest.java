import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentThreeResult;
import ale.server.models.Formula;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimplifiedTruthTableBuilderTest {

    @Test
    public void truthTableSimplification() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("|(|(A,B),C)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentThreeResult assignmentThreeResult = assignmentsCalculations.generateAssignmentThreeResult();

        List<Map<Character, Character>> simplifiedTruthTableResults = assignmentThreeResult.getSimplifiedTableResults();

        // Test for the first row of the table
        List<Character> rowStub = new ArrayList<>(Arrays.asList('0', '0', '0', '0'));

        char firstRowAValue = simplifiedTruthTableResults.get(0).get('A');
        char firstRowBValue = simplifiedTruthTableResults.get(0).get('B');
        char firstRowCValue = simplifiedTruthTableResults.get(0).get('C');
        char firstRowResult = simplifiedTruthTableResults.get(0).get('=');

        List<Character> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowCValue, firstRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the second row of the table
        rowStub = new ArrayList<>(Arrays.asList('*', '*', '1', '0'));

        char secondRowAValue = simplifiedTruthTableResults.get(0).get('A');
        char secondRowBValue = simplifiedTruthTableResults.get(0).get('B');
        char secondRowCValue = simplifiedTruthTableResults.get(0).get('C');
        char secondRowResult = simplifiedTruthTableResults.get(0).get('=');

        rowValues = new ArrayList<>(Arrays.asList(secondRowAValue, secondRowBValue, secondRowCValue, secondRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the third row of the table
        rowStub = new ArrayList<>(Arrays.asList('*', '*', '1', '0'));

        char thirdRowAValue = simplifiedTruthTableResults.get(0).get('A');
        char thirdRowBValue = simplifiedTruthTableResults.get(0).get('B');
        char thirdRowCValue = simplifiedTruthTableResults.get(0).get('C');
        char thirdRowResult = simplifiedTruthTableResults.get(0).get('=');

        rowValues = new ArrayList<>(Arrays.asList(thirdRowAValue, thirdRowBValue, thirdRowCValue, thirdRowResult));

        assertThat(rowValues, is(rowStub));
        // Test for the forth row of the table
        rowStub = new ArrayList<>(Arrays.asList('*', '*', '1', '0'));

        char forthRowAValue = simplifiedTruthTableResults.get(0).get('A');
        char forthRowBValue = simplifiedTruthTableResults.get(0).get('B');
        char forthRowCValue = simplifiedTruthTableResults.get(0).get('C');
        char forthRowResult = simplifiedTruthTableResults.get(0).get('=');

        rowValues = new ArrayList<>(Arrays.asList(forthRowAValue, forthRowBValue, forthRowCValue, forthRowResult));

        assertThat(rowValues, is(rowStub));
        
    }
}
