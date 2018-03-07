package ale.server.models;

import java.util.Map;

public class AssignmentTwoResult {

    private String[] tableData;
    private Map<String, Byte>[] tableResults;
    private String hashCode;

    public String[] getTableData() {
        return tableData;
    }

    public void setTableData(String[] tableData) {
        this.tableData = tableData;
    }

    public Map<String, Byte>[] getTableResults() {
        return tableResults;
    }

    public void setTableResults(Map<String, Byte>[] tableResults) {
        this.tableResults = tableResults;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}
