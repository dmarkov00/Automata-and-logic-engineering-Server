import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The stub values I use for assertion are not checked on paper, their authenticity relies on a comparisons with colleagues
 */
public class TruthTableBuilderTest {
    private AssignmentTwoResult assignmentTwoResult;


    /**
     * Test if the values representing the table(the data on top of the table) are correct, this includes:
     * 1. The extracted formula variables are unique
     * 2. The extracted formula variables are sorted alphabetically
     * 3. The formula string is correct
     */
    @Test
    public void tableDataGenerationWithFormula1() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        List<String> tableData = assignmentTwoResult.getTableData();

        List<String> tableDataStub = new ArrayList<>(Arrays.asList("A", "B", "&(A,B)"));

        assertThat(tableData, is(tableDataStub));

    }

    /**
     * Test if the truth table values are correct, this includes checks for:
     * 1. Number of table rows
     * 2. The generated table binary values for each variable, on certain "interesting" rows
     * 3. The generated table binary result based on the supplied binary values, on certain "interesting" rows
     */
    @Test
    public void truthTableValuesGenerationWithFormula1() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        // Retrieve the data
        List<Map<Character, Integer>> tableResult = assignmentTwoResult.getTableResults();

        // Check if the nr of table rows is correct
        assertThat(tableResult.size(), is(4));

        // Test for the first row of the table
        List<Integer> rowStub = new ArrayList<>(Arrays.asList(0, 0, 0));

        int firstRowAValue = tableResult.get(0).get('A');
        int firstRowBValue = tableResult.get(0).get('B');
        int firstRowResult = tableResult.get(0).get('=');

        List<Integer> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the third row of the table
        rowStub = new ArrayList<>(Arrays.asList(1, 1, 1));

        int thirdRowAValue = tableResult.get(3).get('A');
        int thirdRowBValue = tableResult.get(3).get('B');
        int thirdRowResult = tableResult.get(3).get('=');

        rowValues = new ArrayList<>(Arrays.asList(thirdRowAValue, thirdRowBValue, thirdRowResult));

        assertThat(rowValues, is(rowStub));
    }

    /**
     * Test if the hash code is generated correctly
     */
    @Test
    public void hashGenerationWithFormula1() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("&(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();
        String hashCode = assignmentTwoResult.getHashCode();

        assertThat(hashCode, is("8"));
    }

    /**
     * Test if the values representing the table(the data on top of the table) are correct
     * 1. The extracted formula variables are unique
     * 2. The extracted formula variables are sorted alphabetically
     * 3. The formula string is correct
     */
    @Test
    public void tableDataGenerationWithFormula2() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        List<String> tableData = assignmentTwoResult.getTableData();

        List<String> tableDataStub = new ArrayList<>(Arrays.asList("A", "B", "C", "=(>(A,B),|(~(A),C))"));

        assertThat(tableData, is(tableDataStub));

    }

    /**
     * Test if the truth table values are correct, this includes checks for:
     * 1. Number of table rows
     * 2. The generated table binary values for each variable, on certain "interesting" rows
     * 3. The generated table result based on the supplied binary values, on certain "interesting" rows
     */
    @Test
    public void truthTableValuesGenerationWithFormula2() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        // Retrieve the data
        List<Map<Character, Integer>> tableResult = assignmentTwoResult.getTableResults();

        // Check if the nr of table rows is correct
        assertThat(tableResult.size(), is(8));

        // Test for the first row of the table
        List<Integer> rowStub = new ArrayList<>(Arrays.asList(0, 0, 0, 1));

        int firstRowAValue = tableResult.get(0).get('A');
        int firstRowBValue = tableResult.get(0).get('B');
        int firstRowCValue = tableResult.get(0).get('C');
        int firstRowResult = tableResult.get(0).get('=');

        List<Integer> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowCValue, firstRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the fifth row of the table
        rowStub = new ArrayList<>(Arrays.asList(1, 0, 1, 0));

        int fifthRowAValue = tableResult.get(5).get('A');
        int fifthRowBValue = tableResult.get(5).get('B');
        int fifthRowCValue = tableResult.get(5).get('C');
        int fifthRowResult = tableResult.get(5).get('=');

        rowValues = new ArrayList<>(Arrays.asList(fifthRowAValue, fifthRowBValue, fifthRowCValue, fifthRowResult));

        assertThat(rowValues, is(rowStub));

    }

    /**
     * Test if the hash code is generated correctly
     */
    @Test
    public void hashGenerationWithFormula2() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();
        String hashCode = assignmentTwoResult.getHashCode();

        assertThat(hashCode, is("9f"));
    }

    /**
     * Test if the values representing the table(the data on top of the table) are correct
     * 1. The extracted formula variables are unique
     * 2. The extracted formula variables are sorted alphabetically
     * 3. The formula string is correct
     */
    @Test
    public void tableDataGenerationWithFormula3() {
        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(=(~(>(~(A),B)),|(~(A),B)),&(C,=(~(Y),~(Z))))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        List<String> tableData = assignmentTwoResult.getTableData();

        List<String> tableDataStub = new ArrayList<>(Arrays.asList("A", "B", "C", "Y", "Z", "=(=(~(>(~(A),B)),|(~(A),B)),&(C,=(~(Y),~(Z))))"));

        assertThat(tableData, is(tableDataStub));

    }

    /**
     * Test if the truth table values are correct, this includes checks for:
     * 1. Number of table rows
     * 2. The generated table binary values for each variable, on certain "interesting" rows
     * 3. The generated table result based on the supplied binary values, on certain "interesting" rows
     */
    @Test
    public void truthTableValuesGenerationWithFormula3() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(=(~(>(~(A),B)),|(~(A),B)),&(C,=(~(Y),~(Z))))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        // Retrieve the data
        List<Map<Character, Integer>> tableResult = assignmentTwoResult.getTableResults();

        // Check if the nr of table rows is correct
        assertThat(tableResult.size(), is(32));

        // Test for the first row of the table
        List<Integer> rowStub = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

        int firstRowAValue = tableResult.get(0).get('A');
        int firstRowBValue = tableResult.get(0).get('B');
        int firstRowCValue = tableResult.get(0).get('C');
        int firstRowYValue = tableResult.get(0).get('Y');
        int firstRowZValue = tableResult.get(0).get('Z');

        int firstRowResult = tableResult.get(0).get('=');

        List<Integer> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowCValue, firstRowYValue, firstRowZValue, firstRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the sixth row of the table
        rowStub = new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0, 0));

        int sixthRowAValue = tableResult.get(6).get('A');
        int sixthRowBValue = tableResult.get(6).get('B');
        int sixthRowCValue = tableResult.get(6).get('C');
        int sixthRowYValue = tableResult.get(6).get('Y');
        int sixthRowZValue = tableResult.get(6).get('Z');
        int sixthRowResult = tableResult.get(6).get('=');

        rowValues = new ArrayList<>(Arrays.asList(sixthRowAValue, sixthRowBValue, sixthRowCValue, sixthRowYValue, sixthRowZValue, sixthRowResult));

        assertThat(rowValues, is(rowStub));

    }

    /**
     * Test if the hash code is generated correctly
     */
    @Test
    public void hashGenerationWithFormula3() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(=(~(>(~(A),B)),|(~(A),B)),&(C,=(~(Y),~(Z))))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();
        String hashCode = assignmentTwoResult.getHashCode();

        assertThat(hashCode, is("6f906f90"));
    }

}
