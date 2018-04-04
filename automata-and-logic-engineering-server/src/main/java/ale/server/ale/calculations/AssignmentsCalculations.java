package ale.server.ale.calculations;

import ale.server.models.*;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AssignmentsCalculations {

    private FormulaTree formulaTree;

    public AssignmentsCalculations(Formula formula) {
        FormulaReader formulaReader = new FormulaReader();

        this.formulaTree = formulaReader.readFormula(formula);

    }

    public AssignmentThreeResult generateAssignmentThreeResult() {
        if (formulaTree == null) {
            return null;
        }
        AssignmentTwoResult assignmentTwoResult = generateAssignmentTwoResult();
        List<Map<Character, Integer>> truthTable = assignmentTwoResult.getTableResults();


        SimplifiedTruthTableBuilder simplifiedTruthTableBuilder = new SimplifiedTruthTableBuilder(truthTable);

        List<Map<Character, Character>> simplifiedTruthTable = simplifiedTruthTableBuilder.simplifyTruthTable();

        return new AssignmentThreeResult(assignmentTwoResult.getTableData(),simplifiedTruthTable);

    }


    public AssignmentTwoResult generateAssignmentTwoResult() {
        if (formulaTree == null) {
            return null;
        }
        TruthTableBuilder truthTableBuilder = new TruthTableBuilder(formulaTree);

        List<Map<Character, Integer>> truthTable = truthTableBuilder.generateTruthTable();
        String hashString = truthTableBuilder.generateHash(truthTable);
        List<String> tableData = truthTableBuilder.generateTableData();


        return new AssignmentTwoResult(tableData, truthTable, hashString);
    }

    public AssignmentOneResult generateAssignmentOneResult() {
        if (formulaTree == null) {
            return null;
        }

        GraphGenerator.generateGraph(formulaTree);

        return new AssignmentOneResult();
    }
}
