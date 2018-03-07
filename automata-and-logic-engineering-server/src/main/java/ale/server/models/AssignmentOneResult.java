package ale.server.models;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class AssignmentOneResult {

    private byte[] graphImage;

    public AssignmentOneResult() {
        System.out.println(getClass().getClassLoader().getResource("").getPath());
        System.out.println("-----------------------------------------------------------------");
        InputStream imageInput = getClass().getResourceAsStream("/images/graph.png");
        try {
            this.graphImage = IOUtils.toByteArray(imageInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getGraphImage() {
        return graphImage;
    }
}
