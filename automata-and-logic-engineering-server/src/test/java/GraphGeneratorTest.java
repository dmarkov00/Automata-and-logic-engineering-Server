import ale.server.ale.calculations.AssignmentsCalculations;
import ale.server.ale.calculations.GraphGenerator;
import ale.server.models.AssignmentOneResult;
import ale.server.models.Formula;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphGeneratorTest {

    /**
     * Tests if the generated .dot file is correct based on the passed formula tree
     */
    @Test
    public void testGraphGenerationFormula1() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        // This will generate a new graph image
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentsCalculations.generateAssignmentOneResult();

        // First, we need to read the generated .dot file, the code produces a list of lines
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./out/production/resources/dot-files/graph.dot"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        // Second, we test if all variables and operands are written inside the file as nodes
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"=\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \">\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"|\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"A\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"B\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"~\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"C\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"A\""));

        // Third, we check if some of the connections are accurate
        assertTrue(lines.contains("node0 -- node1")); // Node 0 always connects with Node 1 in my alg
        assertTrue(lines.contains("node2 -- node6"));


    }

    /**
     * Tests if the generated .dot file is correct based on the passed formula tree
     */
    @Test
    public void testGraphGenerationFormula2() {

        // Initialization
        Formula testFormula = mock(Formula.class);
        when(testFormula.getFormula()).thenReturn("|(|(A,~(B)),C)");

        // This will generate a new graph image
        AssignmentsCalculations assignmentsCalculations = new AssignmentsCalculations(testFormula);
        assignmentsCalculations.generateAssignmentOneResult();

        // First, we need to read the generated .dot file, the code produces a list of lines
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./out/production/resources/dot-files/graph.dot"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        // Second, we test if all variables and operands are written inside the file as nodes
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"|\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"A\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"B\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"~\""));
        assertTrue(GraphGeneratorTest.checkIfFileContainsLabel(lines, "label = \"C\""));

        // Third, we check if some of the connections are accurate
        assertTrue(lines.contains("node0 -- node1")); // Node 0 always connects with Node 1 in my alg
        assertTrue(lines.contains("node4 -- node9"));

    }


    /**
     * Loops all the lines and checks if the contain the node label that is passed
     */
    private static boolean checkIfFileContainsLabel(List<String> lines, String label) {
        for (String line : lines) {
            if (line.contains(label)) {
                return true;
            }
        }
        return false;
    }
}
