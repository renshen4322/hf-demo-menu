package computermethod;

public class Node {
    private Node pre;

    private Object value;

    public Node() {
    }

    public int data;
    public Node next;
    public Node(int d) {
        data = d;
        next = null;
    }

    public Node(Node pre, Node next, Object value) {
        this.pre = pre;
        this.next = next;
        this.value = value;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


}
