package ale.server.models;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class AssignmentOneResult {

    private InputStream graphImage;
    private String test;

    public AssignmentOneResult() {
        this.graphImage = getClass().getResourceAsStream("./src/main/resources/images/graph.png");
        this.test = "test";
    }

    public String getTest() {
        return test;
    }

    public InputStream getGraphImage() {
        return graphImage;
    }
}
