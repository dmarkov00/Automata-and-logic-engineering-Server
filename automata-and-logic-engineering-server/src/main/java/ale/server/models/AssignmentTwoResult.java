package ale.server.models;

import java.util.List;
import java.util.Map;

public class AssignmentTwoResult implements AssignmentResult {

    private List<String> tableData;
    private List<Map<Character, Byte>> tableResults;
    private String hashCode;

    public AssignmentTwoResult(List<String> tableData, List<Map<Character, Byte>> tableResults, String hashCode) {
        this.tableData = tableData;
        this.tableResults = tableResults;
        this.hashCode = hashCode;
    }

    public List<String> getTableData() {
        return tableData;
    }

    public List<Map<Character, Byte>> getTableResults() {
        return tableResults;
    }

    public String getHashCode() {
        return hashCode;
    }


}
