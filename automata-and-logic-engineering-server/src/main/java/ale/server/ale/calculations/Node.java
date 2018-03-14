package ale.server.ale.calculations;

public class Node {
    private char value;
    private byte binaryValue;

    Node(char value) {

        this.value = value;
        this.binaryValue = 0;
    }

    public byte getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(byte binaryValue) {
        this.binaryValue = binaryValue;
    }


    public char getValue() {

        return value;
    }
}