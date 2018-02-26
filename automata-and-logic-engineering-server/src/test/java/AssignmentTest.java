import org.junit.Test;
import ale.server.ale.calculations.AssignmentOne;

public class AssignmentTest {
    AssignmentOne assignment = new AssignmentOne();

    @Test
    public void randomTest() {

        StringBuilder formula = new StringBuilder("=(>(A,B),|(~(A),B))");
        
        assignment.readFormula(formula);
    }

}
