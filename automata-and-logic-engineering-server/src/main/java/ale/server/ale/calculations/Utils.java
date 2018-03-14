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




    public void generateGraph() {
        Calculations calculations = new Calculations(formulaTree);
        calculations.generateGraph();
    }

    public AssignmentTwoResult generateAssignmentTwoResult() {
        TruthTableBuilder truthTableBuilder = new TruthTableBuilder(formulaTree);



        return null;

    }
}
