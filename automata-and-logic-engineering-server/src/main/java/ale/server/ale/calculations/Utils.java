package ale.server.ale.calculations;

import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;

import java.io.UnsupportedEncodingException;

public class Utils {

    private FormulaTree formulaTree;
//    public static String parsedFormulaString;

    Utils() {
        this.formulaTree = new FormulaTree();
    }

    public void readFormula(Formula formula) {

        StringBuilder convertedFormula = this.convertFormula(formula);
        readConvertedFormula(convertedFormula);

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
//            parsedFormulaString = parsedFormula;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new StringBuilder(parsedFormula);

    }


    public void generateGraph() {
        Calculations calculations = new Calculations(formulaTree);
        calculations.generateGraph();
    }

    public AssignmentTwoResult generateAssignmentTwoResult() {
        TruthTableBuilder truthTableBuilder = new TruthTableBuilder(formulaTree);



        return null;

    }
}
