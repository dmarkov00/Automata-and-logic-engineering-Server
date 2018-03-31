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

        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentTwoResult = assignmentsCalculations.generateAssignmentTwoResult();

    }

    /**
     * Test if the truth table values are correct.
     * 1. Number of table rows
     * 2. The generated table values for each variable
     * 3. The results when supplying the formula with the different values
     */
    @Test
    public void truthTableValuesGeneration() {
        // Retrieve the data
        List<Map<Character, Integer>> tableResult = assignmentTwoResult.getTableResults();

        assertThat(tableResult.size(), is(8));

        List<Integer> rowStub = new ArrayList<>(Arrays.asList(0, 0, 0, 0));


        int firstRowAValue = tableResult.get(0).get('A');
        int firstRowBValue = tableResult.get(0).get('B');
        int firstRowCValue = tableResult.get(0).get('C');
        int firstRowResult = tableResult.get(0).get('=');
        List<Integer> rowValues = new ArrayList<>(Arrays.asList(firstRowAValue, firstRowBValue, firstRowCValue, firstRowResult));


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
