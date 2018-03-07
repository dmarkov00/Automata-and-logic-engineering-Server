package ale.server.ale.calculations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculations {

    public static void generateGraph(FormulaTree formulaTree) {
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

    public List<Character> getUniqueVariables(FormulaTree formulaTree) {
        Node[] arrayTree = formulaTree.getArrayTree();

        List<Character> variablesList = new ArrayList<>();
        List<Character> bannedChars = Arrays.asList('=', ')', '(', '>', ',', '|', '~');
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

    public void generateTruthTable(FormulaTree formulaTree) {
        Node[] arrayTree = formulaTree.getArrayTree();
        int leftmostNodeIndex = 0;
        int rightmostNodeIndex = 0;

        // Retrieve leftmost node
        while (true) {

            if (formulaTree.nodeHasLeftChild(leftmostNodeIndex)) {
                leftmostNodeIndex = formulaTree.getLeftChildIndex(leftmostNodeIndex);
            } else {
                break;
            }
        }
        // Retrieve rightmost node
        while (true) {

            if (formulaTree.nodeHasRightChild(rightmostNodeIndex)) {
                rightmostNodeIndex = formulaTree.getRightChildIndex(rightmostNodeIndex);
            } else {
                break;
            }
        }

        // If the leftmost and the rightmost node have the same parent
        if (formulaTree.getParentIndex(leftmostNodeIndex) == formulaTree.getParentIndex(rightmostNodeIndex))


            for (int i = 0; i < arrayTree.length; i++) {

            }
    }

}
