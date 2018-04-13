package ale.server.ale.calculations.utils;

import ale.server.ale.calculations.structures.FormulaTree;
import ale.server.models.Formula;

import java.io.UnsupportedEncodingException;

public class FormulaReader {
    private FormulaTree formulaTree;
    public static String parsedFormulaString;

    public FormulaReader() {
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
        String notParsedFormula = formula.getFormula();
        try {
            StringBuilder tempBuffer = new StringBuilder();
            int incrementer = 0;
            int dataLength = notParsedFormula.length();
            while (incrementer < dataLength) {
                char characterAt = notParsedFormula.charAt(incrementer);
                if (characterAt == '%') {
                    tempBuffer.append("<percentage>");
                } else {
                    tempBuffer.append(characterAt);
                }
                incrementer++;
            }
            notParsedFormula = tempBuffer.toString();

            parsedFormula = java.net.URLDecoder.decode(notParsedFormula, "UTF-8").replaceAll("<percentage>", "%");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Remove spaces
        String trimmedFormula = parsedFormula.replaceAll("\\s", "");

        if (isFormulaCorrectlyFormatted(trimmedFormula)) {
            parsedFormulaString = trimmedFormula;
            return new StringBuilder(trimmedFormula);

        } else return null;

    }

    /**
     * Checks if the formula meets the requirements by using a regular expression
     */
    private boolean isFormulaCorrectlyFormatted(String formulaStr) {

        return formulaStr.matches("[a-zA-Z()~|=&,>%]+");
    }
}
