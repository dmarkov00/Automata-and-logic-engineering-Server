package ale.server.ale.calculations;

import java.util.Arrays;

public class AssignmentOne {

    private FormulaTree formulaTree = new FormulaTree();


    public void readFormula(StringBuilder formula) {

        formulaTree.addNode(formula.charAt(0));

        formula.deleteCharAt(0);

        if (formula.length() == 0) return;


        readFormula(formula);


    }
    public void generateShit(){
        formulaTree.generateImageFile();
    }

}
























