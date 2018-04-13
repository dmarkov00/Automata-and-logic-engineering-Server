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


/**
 * The stub values I use for assertion are not checked on paper, their authenticity relies on a comparisons with colleagues
 */
public class SimplifiedTruthTableBuilderTest {

    @Test
    public void truthTableSimplificationFormula1() {

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
        rowStub = new ArrayList<>(Arrays.asList('*', '*', '1', '1'));

        char secondRowAValue = simplifiedTruthTableResults.get(1).get('A');
        char secondRowBValue = simplifiedTruthTableResults.get(1).get('B');
        char secondRowCValue = simplifiedTruthTableResults.get(1).get('C');
        char secondRowResult = simplifiedTruthTableResults.get(1).get('=');

        rowValues = new ArrayList<>(Arrays.asList(secondRowAValue, secondRowBValue, secondRowCValue, secondRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the third row of the table
        rowStub = new ArrayList<>(Arrays.asList('*', '1', '*', '1'));

        char thirdRowAValue = simplifiedTruthTableResults.get(2).get('A');
        char thirdRowBValue = simplifiedTruthTableResults.get(2).get('B');
        char thirdRowCValue = simplifiedTruthTableResults.get(2).get('C');
        char thirdRowResult = simplifiedTruthTableResults.get(2).get('=');

        rowValues = new ArrayList<>(Arrays.asList(thirdRowAValue, thirdRowBValue, thirdRowCValue, thirdRowResult));

        assertThat(rowValues, is(rowStub));
        // Test for the forth row of the table
        rowStub = new ArrayList<>(Arrays.asList('1', '*', '*', '1'));

        char forthRowAValue = simplifiedTruthTableResults.get(3).get('A');
        char forthRowBValue = simplifiedTruthTableResults.get(3).get('B');
        char forthRowCValue = simplifiedTruthTableResults.get(3).get('C');
        char forthRowResult = simplifiedTruthTableResults.get(3).get('=');

        rowValues = new ArrayList<>(Arrays.asList(forthRowAValue, forthRowBValue, forthRowCValue, forthRowResult));

        assertThat(rowValues, is(rowStub));
    }


    @Test
    public void truthTableSimplificationFormula2() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(|(A, B),C)");

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


        // Test for the fifth row of the table
        rowStub = new ArrayList<>(Arrays.asList('*', '1', '1', '1'));

        char fifthRowAValue = simplifiedTruthTableResults.get(5).get('A');
        char fifthRowBValue = simplifiedTruthTableResults.get(5).get('B');
        char fifthRowCValue = simplifiedTruthTableResults.get(5).get('C');
        char fifthRowResult = simplifiedTruthTableResults.get(5).get('=');

        rowValues = new ArrayList<>(Arrays.asList(fifthRowAValue, fifthRowBValue, fifthRowCValue, fifthRowResult));

        assertThat(rowValues, is(rowStub));
        // Test for the sixth row of the table
        rowStub = new ArrayList<>(Arrays.asList('1', '*', '1', '1'));

        char sixthRowAValue = simplifiedTruthTableResults.get(6).get('A');
        char sixthRowBValue = simplifiedTruthTableResults.get(6).get('B');
        char sixthRowCValue = simplifiedTruthTableResults.get(6).get('C');
        char sixthRowResult = simplifiedTruthTableResults.get(6).get('=');

        rowValues = new ArrayList<>(Arrays.asList(sixthRowAValue, sixthRowBValue, sixthRowCValue, sixthRowResult));

        assertThat(rowValues, is(rowStub));
    }

    @Test
    public void truthTableSimplificationFormula3() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(=(A,B),|(C,D))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        AssignmentThreeResult assignmentThreeResult = assignmentsCalculations.generateAssignmentThreeResult();

        List<Map<Character, Character>> simplifiedTruthTableResults = assignmentThreeResult.getSimplifiedTableResults();

        // Test for the tenth row of the table
        List<Character> rowStub = new ArrayList<>(Arrays.asList('0', '0', '*', '1', '1'));

        char tenthRowAValue = simplifiedTruthTableResults.get(10).get('A');
        char tenthRowBValue = simplifiedTruthTableResults.get(10).get('B');
        char tenthRowCValue = simplifiedTruthTableResults.get(10).get('C');
        char tenthRowDValue = simplifiedTruthTableResults.get(10).get('D');
        char tenthRowResult = simplifiedTruthTableResults.get(10).get('=');

        List<Character> rowValues = new ArrayList<>(Arrays.asList(tenthRowAValue, tenthRowBValue, tenthRowCValue, tenthRowDValue, tenthRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the eleventh row of the table
        rowStub = new ArrayList<>(Arrays.asList('0', '0', '1', '*', '1'));

        char eleventhRowAValue = simplifiedTruthTableResults.get(11).get('A');
        char eleventhRowBValue = simplifiedTruthTableResults.get(11).get('B');
        char eleventhRowCValue = simplifiedTruthTableResults.get(11).get('C');
        char eleventhRowDValue = simplifiedTruthTableResults.get(11).get('D');
        char eleventhRowResult = simplifiedTruthTableResults.get(11).get('=');

        rowValues = new ArrayList<>(Arrays.asList(eleventhRowAValue, eleventhRowBValue, eleventhRowCValue, eleventhRowDValue, eleventhRowResult));

        assertThat(rowValues, is(rowStub));
        // Test for the twelfth row of the table
        rowStub = new ArrayList<>(Arrays.asList('1', '1', '*', '1', '1'));

        char twelfthRowAValue = simplifiedTruthTableResults.get(12).get('A');
        char twelfthRowBValue = simplifiedTruthTableResults.get(12).get('B');
        char twelfthRowCValue = simplifiedTruthTableResults.get(12).get('C');
        char twelfthRowDValue = simplifiedTruthTableResults.get(12).get('D');
        char twelfthRowResult = simplifiedTruthTableResults.get(12).get('=');

        rowValues = new ArrayList<>(Arrays.asList(twelfthRowAValue, twelfthRowBValue, twelfthRowCValue, twelfthRowDValue, twelfthRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the thirteenth row of the table
        rowStub = new ArrayList<>(Arrays.asList('1', '1', '1', '*', '1'));

        char thirteenthRowAValue = simplifiedTruthTableResults.get(13).get('A');
        char thirteenthRowBValue = simplifiedTruthTableResults.get(13).get('B');
        char thirteenthRowCValue = simplifiedTruthTableResults.get(13).get('C');
        char thirteenthRowDValue = simplifiedTruthTableResults.get(13).get('D');
        char thirteenthRowResult = simplifiedTruthTableResults.get(13).get('=');

        rowValues = new ArrayList<>(Arrays.asList(thirteenthRowAValue, thirteenthRowBValue, thirteenthRowCValue, thirteenthRowDValue, thirteenthRowResult));

        assertThat(rowValues, is(rowStub));

    }
}
