package ale.server.ale.calculations;

public class Utils {

    public FormulaTree formulaTree;

    public Utils() {
        this.formulaTree = new FormulaTree();
    }

    public void readFormula(StringBuilder formula) {

        formulaTree.addNode(formula.charAt(0));

        formula.deleteCharAt(0);

        if (formula.length() == 0) {
            return;
        }

        readFormula(formula);

    }
}
