package ale.server.models;

import org.springframework.core.io.ClassPathResource;

public class AssignmentOneResult {

    private static ClassPathResource graphImage;
    private String test;

    public AssignmentOneResult() {
        test = "test";
    }

    public String getTest() {
        return test;
    }

    public static void setGraphImage(ClassPathResource graphImage) {
        AssignmentOneResult.graphImage = graphImage;
    }

    public ClassPathResource getGraphImage() {
        return graphImage;
    }
}
