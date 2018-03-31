import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TruthTableBuilderTest {
    private AssignmentTwoResult assignmentTwoResult;

    @Before
    public void setUp() {
        Formula basicTestFormula = mock(Formula.class);
        when(basicTestFormula.getFormula()).thenReturn(">(A,B)");


        Formula complexTestFormula = mock(Formula.class);
        when(complexTestFormula.getFormula()).thenReturn("=(=( ~(>(~(A),B)), |( ~(A) ,B) ), &(C,=(~(Y),~(Z))))");


    }

    /**
     * Test if the truth table values are correct with medium complex formula, this includes checks for:
     * 1. Number of table rows
     * 2. The generated table binary values for each variable
     * 3. The generated table result based on the supplied binary values
     */
    @Test
    public void truthTableValuesGenerationWithMediumFormula() {

        // Initialization
        Formula mediumTestFormula = mock(Formula.class);
        when(mediumTestFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(mediumTestFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

        // Retrieve the data
        List<Map<Character, Integer>> tableResult = assignmentTwoResult.getTableResults();

        assertThat(tableResult.size(), is(8));

        // Test for the first row of the table
        List<Integer> rowStub = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

        int firstRowAValue = tableResult.get(0).get('A');
        int firstRowBValue = tableResult.get(0).get('B');
        int firstRowCValue = tableResult.get(0).get('C');
        int firstRowResult = tableResult.get(0).get('=');

        List<Integer> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowCValue, firstRowResult));

        assertThat(rowValues, is(rowStub));

        // Test for the fifth row of the table
        rowStub = new ArrayList<>(Arrays.asList(1, 0, 1, 1));

        int fifthRowAValue = tableResult.get(5).get('A');
        int fifthRowBValue = tableResult.get(5).get('B');
        int fifthRowCValue = tableResult.get(5).get('C');
        int fifthRowResult = tableResult.get(5).get('=');

        rowValues = new ArrayList<>(Arrays.asList(fifthRowAValue, fifthRowBValue, fifthRowCValue, fifthRowResult));

        assertThat(rowValues, is(rowStub));


    }

    /**
     * Test if the values representing the table(the data on top of the table) are correct
     * 1. The extracted formula variables are unique
     * 2. The extracted formula variables are sorted alphabetically
     * 3. The formula string is correct
     */
    @Test
    public void tableDataGeneration() {

    }

    /**
     * Test if the hash code is generated correctly
     */
    @Test
    public void hashGeneration() {

        assignmentTwoResult.getHashCode();
    }

}
