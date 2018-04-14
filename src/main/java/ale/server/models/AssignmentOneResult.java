package ale.server.models;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AssignmentOneResult implements AssignmentResult {

    private byte[] graphImage;

    public AssignmentOneResult() {
        InputStream imageInput = null;
        try {
            imageInput = new FileInputStream("graph.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        InputStream imageInput = getClass().getResourceAsStream("/images/graph.png");

        try {
            this.graphImage = IOUtils.toByteArray(imageInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Data needs to be accessible for the http response
    public byte[] getGraphImage() {
        return graphImage;
    }
}
