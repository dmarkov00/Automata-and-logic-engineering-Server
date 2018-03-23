import ale.server.models.Formula;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class TestHelper {
    /**
     * Generates a list of mocked formulas for testing
     * It is required for the Formula object to be mocked because
     * it is retrieved by the Spring API setup, which I'm not calling during the tests
     * @return List of mocked formulas
     */

    public static List<Formula> generateFormulaMocks() {
        List<Formula> formulas;


        Formula testFormula1 = mock(Formula.class);
        when(testFormula1.getFormula()).thenReturn("=(>(A,B),|(~(A),C))");

        Formula testFormula2 = mock(Formula.class);
        when(testFormula1.getFormula()).thenReturn("|(A,B)");

        formulas = new ArrayList<>(Arrays.asList(testFormula1, testFormula2));

        return formulas;
    }

}
