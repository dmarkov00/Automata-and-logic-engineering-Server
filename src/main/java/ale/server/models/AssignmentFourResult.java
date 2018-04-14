package ale.server.models;


public class AssignmentFourResult implements AssignmentResult {

    private String disjunctiveNormalFormTruthTable;
    private String disjunctiveNormalFormSimplifiedTruthTable;


    public AssignmentFourResult(String disjunctiveNormalFormTruthTable, String disjunctiveNormalFormSimplifiedTruthTable) {
        this.disjunctiveNormalFormTruthTable = disjunctiveNormalFormTruthTable;
        this.disjunctiveNormalFormSimplifiedTruthTable = disjunctiveNormalFormSimplifiedTruthTable;
    }

    // Required getters so Spring can send the result
    public String getDisjunctiveNormalFormTruthTable() {
        return disjunctiveNormalFormTruthTable;
    }

    public String getDisjunctiveNormalFormSimplifiedTruthTable() {
        return disjunctiveNormalFormSimplifiedTruthTable;
    }


}
