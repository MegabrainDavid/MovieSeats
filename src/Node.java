public class Node<anyType> {
    private anyType payload;
    private Node<anyType> next;
    private Node<anyType> down;


    public Node(anyType payload) {
        this.payload = payload;
        this.next = null;
        this.down = null;
    }

    public anyType getPayload() {
        return payload;
    }

    public Node<anyType> getNext() {
        return next;
    }

    public void setNext(Node<anyType> next) {
        this.next = next;
    }

    public Node<anyType> getDown() {
        return down;
    }

    public void setDown(Node<anyType> down) {
        this.down = down;
    }
}
