package ale.server.models;

import java.util.List;
import java.util.Map;

public class AssignmentTwoResult implements AssignmentResult {

    private List<String> tableData;
    private List<Map<Character, Byte>> tableResults;
    private String hashCode;

    public List<String> getTableData() {
        return tableData;
    }

    public void setTableData(List<String> tableData) {
        this.tableData = tableData;
    }

    public List<Map<Character, Byte>> getTableResults() {
        return tableResults;
    }

    public void setTableResults(List<Map<Character, Byte>> tableResults) {
        this.tableResults = tableResults;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}
