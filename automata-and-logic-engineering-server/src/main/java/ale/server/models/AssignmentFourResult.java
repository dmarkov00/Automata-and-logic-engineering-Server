package ale.server.models;

import java.util.List;

public class AssignmentFourResult implements AssignmentResult {
    private List<String> disjunctiveNormalForms;

    public AssignmentFourResult(List<String> disjunctiveNormalForms) {
        this.disjunctiveNormalForms = disjunctiveNormalForms;
    }
    // Required so Spring can re return the result
    public List<String> getDisjunctiveNormalForms() {
        return disjunctiveNormalForms;
    }
}
