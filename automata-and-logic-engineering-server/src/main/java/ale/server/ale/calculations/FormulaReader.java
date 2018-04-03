package ale.server.ale.calculations;

import ale.server.models.Formula;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormulaReader {
    private FormulaTree formulaTree;
    public static String parsedFormulaString;

    FormulaReader() {
        this.formulaTree = new FormulaTree();
    }


    public FormulaTree readFormula(Formula formula) {

        StringBuilder convertedFormula = this.convertFormula(formula);

        if (convertedFormula != null) {
            readConvertedFormula(convertedFormula);

        } else return null;

        return formulaTree;
    }

    private void readConvertedFormula(StringBuilder formula) {
        formulaTree.addNode(formula.charAt(0));

        formula.deleteCharAt(0);

        if (formula.length() == 0) {
            return;
        }

        readConvertedFormula(formula);
    }

    private StringBuilder convertFormula(Formula formula) {

        String parsedFormula = "";
        try {
            parsedFormula = java.net.URLDecoder.decode(formula.getFormula(), "UTF-8");
            parsedFormulaString = parsedFormula;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (isFormulaCorrectlyFormatted(parsedFormulaString)) {
            return new StringBuilder(parsedFormula);

        } else return null;

    }

    /**
     * Checks if the formula meets the requirements by using a regular expression
     */
    private boolean isFormulaCorrectlyFormatted(String formulaStr) {
        // Remove spaces
        String trimmedFormula = formulaStr.replaceAll("\\s", "");

        return trimmedFormula.matches("[a-zA-Z()~|=&,>]+");
    }
}
