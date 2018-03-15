package ale.server.ale.calculations;

import ale.server.models.AssignmentOneResult;
import ale.server.models.AssignmentResult;
import ale.server.models.AssignmentTwoResult;
import ale.server.models.Formula;
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


    public AssignmentTwoResult generateAssignmentTwoResult() {
        TruthTableBuilder truthTableBuilder = new TruthTableBuilder(formulaTree);

        List<Map<Character, Byte>> truthTable = truthTableBuilder.generateTruthTable();
        String hashString = truthTableBuilder.generateHash(truthTable);
        return null;
    }

    public AssignmentOneResult generateAssignmentOneResult() {
        GraphGenerator.generateGraph(formulaTree);

        return new AssignmentOneResult();
    }
}
