import org.junit.Test;
import ale.server.ale.calculations.assignments.AssignmentOne;

public class AssignmentTest {
    AssignmentOne assignment = new AssignmentOne();
    char a;

    @Test
    public void randomTest() {

        StringBuilder formula = new StringBuilder("=(>(A,B),|(~(A),B))");

        assignment.readFormula(formula);


    }

}
