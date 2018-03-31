package ale.server.ale.calculations;

public class Node {
    private char value;
    private int binaryValue;

    Node(char value) {

        this.value = value;
        this.binaryValue = 0;
    }

    public int getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(int binaryValue) {
        this.binaryValue = binaryValue;
    }


    public char getValue() {

        return value;
    }
}