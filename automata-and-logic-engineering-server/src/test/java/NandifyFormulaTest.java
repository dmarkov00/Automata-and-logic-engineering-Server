import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.models.AssignmentFiveResult;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * The way I am going to test the nandify is:
 * 1. Generate nandified formula
 * 2. Retrieve the truth table for the nandified formula
 * 3. Compare the truth tables of the nandified formula and the initial formula
 */
public class NandifyFormulaTest {

    @Test
    public void nandifiedFormulaGenerationBaseFormula1() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("~(A)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationBaseFormula2() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("|(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationBaseFormula3() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("&(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationBaseFormula4() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("=(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationBaseFormula5() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn(">(A,B)");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationFormula1() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("&(&(p,q),~(p))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }
    @Test
    public void nandifiedFormulaGenerationFormula2() {
        // Initialization
        Formula initialFormula = mock(Formula.class);
        when(initialFormula.getFormula()).thenReturn("~(&(>(A,C),~(&(~(B),C))))");

        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(initialFormula);

        // Calculate the nandified version of the initial formula
        AssignmentFiveResult assignmentFiveResult = assignmentsCalculations.generateAssignmentFiveResult();
        String nandifiedFormula = assignmentFiveResult.getNandifiedFormula();

        // Calculate the truth table of the initial formula
        AssignmentTwoResult assignmentTwoResultInitialFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> initialFormulaTruthTableValues = assignmentTwoResultInitialFormula.getTableResults();

        // New initialization with the nandified formula
        Formula nandifiedTestFormula = mock(Formula.class);
        when(nandifiedTestFormula.getFormula()).thenReturn(nandifiedFormula);

        assignmentsCalculations = new AssignmentsCalculations(nandifiedTestFormula);

        // Calculate the truth table of the nandified formula
        AssignmentTwoResult assignmentTwoResultNandifiedFormula = assignmentsCalculations.generateAssignmentTwoResult();
        List<Map<Character, Integer>> nandifiedFormulaTruthTableValues = assignmentTwoResultNandifiedFormula.getTableResults();


        assertThat(initialFormulaTruthTableValues, is(nandifiedFormulaTruthTableValues));

    }


}
