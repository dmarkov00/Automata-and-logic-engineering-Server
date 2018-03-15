import ale.server.ale.calculations.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class UtilsTest {

    @Test
    public void sortVariablesAlphabetically() {
        List<Character> charList = new ArrayList<>(Arrays.asList('C', 'A', 'B'));
        List<Character> sortedCharList = new ArrayList<>(Arrays.asList('A', 'B', 'C'));

        charList = Utils.sortListAlphabetically(charList);


        assertThat(charList, is(sortedCharList));
    }
}
