package ale.server.ale.calculations;

import ale.server.ale.calculations.structures.FormulaTree;
import ale.server.ale.calculations.structures.Node;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {


    /**
     * Generates image based on the formula tree that is passed
     * @param formulaTree tree of formula characters
     */
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
}
