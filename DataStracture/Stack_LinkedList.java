package DataStracture;

class Stack_LinkedList {
    int data;
    Stack_LinkedList next;

    public Stack_LinkedList(int data) {
        this.data = data;
        this.next = null;
    }

    Stack_LinkedList(int data, Stack_LinkedList next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return STR."\{data} ";
    }

    Stack_LinkedList Push_to_stack(Stack_LinkedList t, int new_data) {
        Stack_LinkedList new_node = new Stack_LinkedList(new_data);
        new_node.next = t;
        return new_node;
    }

    Stack_LinkedList Take_data_and_POP(Stack_LinkedList t) {
        return t.next;
    }

    static void main(String[] args) {

    }
}

