package ale.server.models;

public class AssignmentFiveResult implements AssignmentResult {
    private String nandifiedFormula;


    public AssignmentFiveResult(String nandifiedFormula) {
        this.nandifiedFormula = nandifiedFormula;
    }

    public String getNandifiedFormula() {
        return nandifiedFormula;
    }
}
