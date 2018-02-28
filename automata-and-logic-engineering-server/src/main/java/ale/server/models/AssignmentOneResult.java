package ale.server.models;


import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class AssignmentOneResult {

    private byte[] graphImage;
    private String test;

    public AssignmentOneResult() {

        InputStream imageInput = getClass().getResourceAsStream("/images/graph.png");
        try {
            graphImage = IOUtils.toByteArray(imageInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.test = "test";
    }

    public String getTest() {
        return test;
    }

    public byte[] getGraphImage() {
        return graphImage;
    }
}
