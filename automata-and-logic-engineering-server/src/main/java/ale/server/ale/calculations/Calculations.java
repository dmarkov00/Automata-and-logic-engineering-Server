package ale.server.ale.calculations;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Calculations {
    Calculations(FormulaTree formulaTree) {
        this.formulaTree = formulaTree;

    }

    private FormulaTree formulaTree;

    public void generateGraph() {
        Node[] arrayTree = formulaTree.getArrayTree();

        List<String> lines = new ArrayList<>();
        lines.add("graph logic {");
        lines.add("node [ fontname = \"Arial\" ]");


        for (int i = 0; i < arrayTree.length; i++) {
            if (arrayTree[i] != null) {

                lines.add("node" + i + "[ label = \"" + arrayTree[i].getValue() + "\" ]");

                if (formulaTree.nodeHasLeftChild(i)) {
                    lines.add("node" + i + " -- " + "node" + formulaTree.getLeftChildIndex(i));
                }
                if (formulaTree.nodeHasRightChild(i)) {
                    lines.add("node" + i + " -- " + "node" + formulaTree.getRightChildIndex(i));
                }
            }

        }
        lines.add("}");
        Path out = Paths.get("./out/production/resources/dot-files/graph.dot");

        try {
            Files.write(out, lines, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process p = Runtime.getRuntime().exec("dot -Tpng -o./out/production/resources/images/graph.png ./out/production/resources/dot-files/graph.dot");
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Character> getUniqueVariables(FormulaTree formulaTree) {
        Node[] arrayTree = formulaTree.getArrayTree();

        List<Character> variablesList = new ArrayList<>();
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~');
        for (Node node : arrayTree) {
            if (node != null) {
                if (!bannedChars.contains(node.getValue())) {
                    variablesList.add(node.getValue());
                    bannedChars.add(node.getValue());
                }

            }
        }
        return variablesList;
    }

    private boolean isNotVariable(Node node) {
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', '&', ',', '|', '~');
        return bannedChars.contains(node.getValue());
    }


    private byte evaluateTree(int root, Node[] arrayTree) {


        if (!formulaTree.nodeHasLeftChild(root) && !formulaTree.nodeHasLeftChild(root)) {

            return arrayTree[root].getBinaryValue();
        }

        byte leftBinaryValue = evaluateTree(formulaTree.getLeftChildIndex(root), arrayTree);
        byte rightBinaryValue = evaluateTree(formulaTree.getRightChildIndex(root), arrayTree);

        return getBinaryResult(arrayTree[root], leftBinaryValue, rightBinaryValue);

    }

    private byte getBinaryResult(Node root, byte left, byte right) {
        char rootValue = root.getValue();
        switch (rootValue) {
            case '|':
                return (byte) (left | right);
            case '&':
                return (byte) (left & right);
            case '~':
                if (left == 0) {
                    return 1;
                } else if (left == 1) {
                    return 0;
                }
            case '=':
                if (left == right) {
                    return 0;
                } else {
                    return 1;
                }
            case '>':
                if (left == 1 && right == 0) {
                    return 0;
                } else return 1;

            default:
                return 0;
            // Fix later
        }

    }

    public List<Map<Character, Byte>> generateTruthTable() {
        List<Map<Character, Byte>> truthTable;

        truthTable = fillTruthTableWithVariableData();
        for (Map<Character, Byte> tableRow : truthTable) {
            Node[] arrayTree = setBinaryValuesInArrayTree(tableRow);
            byte formulaEvaluationResult = evaluateTree(0, arrayTree);

            tableRow.put('=', formulaEvaluationResult);
        }
        return truthTable;

    }

    private Node[] setBinaryValuesInArrayTree(Map<Character, Byte> tableRow) {
        Node[] arrayTree = formulaTree.getArrayTree();
        for (Node node : arrayTree) {
            if (tableRow.containsKey(node.getValue())) {
                node.setBinaryValue(tableRow.get(node.getValue()));
            }
        }
        return arrayTree;

    }

    private List<Map<Character, Byte>> fillTruthTableWithVariableData() {

        List<Map<Character, Byte>> truthTable = new ArrayList<>();
        Map<Character, Byte> tableRow = new HashMap<>();

        List<Character> variablesList = getUniqueVariables(formulaTree);
        int nrOfVariables = variablesList.size();
        int nrOfRows = (int) Math.pow(2, nrOfVariables);

        for (int i = 0; i < nrOfRows; i++) {
            for (int j = nrOfVariables - 1; j >= 0; j--) {
                byte binaryValue = (byte) ((i / (int) Math.pow(2, j)) % 2);

                tableRow.put(variablesList.get(j), binaryValue);

//                System.out.print((i / (int) Math.pow(2, j)) % 2 + " ");
            }

            truthTable.add(tableRow);
            tableRow = new HashMap<>();
        }
        return truthTable;
    }
}
