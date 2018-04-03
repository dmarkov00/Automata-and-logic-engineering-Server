package ale.server.ale.calculations;

import ale.server.models.Formula;

import java.io.UnsupportedEncodingException;

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

    private boolean isFormulaCorrectlyFormatted(String formulaStr) {
        return false;
    }
}
