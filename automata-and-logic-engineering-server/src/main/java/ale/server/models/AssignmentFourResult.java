package ale.server.models;

import java.util.List;

public class AssignmentFourResult implements AssignmentResult {

    private String disjunctiveNormalFormTruthTable;
    private String disjunctiveNormalFormSimplifiedTruthTable;


    public AssignmentFourResult(List<String> disjunctiveNormalForms) {
        disjunctiveNormalFormTruthTable = disjunctiveNormalForms.get(0);
        disjunctiveNormalFormSimplifiedTruthTable = disjunctiveNormalForms.get(1);
    }

    // Required getters so Spring can send the result
    public String getDisjunctiveNormalFormTruthTable() {
        return disjunctiveNormalFormTruthTable;
    }

    public String getDisjunctiveNormalFormSimplifiedTruthTable() {
        return disjunctiveNormalFormSimplifiedTruthTable;
    }


}
