package ale.server.models;

import java.util.List;
import java.util.Map;

public class AssignmentThreeResult implements AssignmentResult {
    private List<String> tableData;
    private List<Map<Character, Character>> tableResults;

    public AssignmentThreeResult(List<String> tableData, List<Map<Character, Character>> tableResults) {
        this.tableData = tableData;
        this.tableResults = tableResults;
    }

    public List<String> getTableData() {
        return tableData;
    }

    public List<Map<Character, Character>> getTableResults() {
        return tableResults;
    }
}
