package DataStracture.LinkedLists;

class DoublyLinkedList {

    private static class data_DLL {
        private int data;

        public data_DLL(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    private static class Node_DLL {
        private data_DLL data;
        private Node_DLL next, previous;
        private String Node_DLLName;

        public Node_DLL(data_DLL data, Node_DLL next, Node_DLL previous, String node_DLLName) {
            this.data = data;
            this.next = next;
            this.previous = previous;
            Node_DLLName = node_DLLName;
        }

        public data_DLL getData() {
            return data;
        }

        public void setData(data_DLL data) {
            this.data = data;
        }

        public Node_DLL getNext() {
            return next;
        }

        public void setNext(Node_DLL next) {
            this.next = next;
        }

        public Node_DLL getPrevious() {
            return previous;
        }

        public void setPrevious(Node_DLL previous) {
            this.previous = previous;
        }

        public String getNode_DLLName() {
            return Node_DLLName;
        }

        public void setNode_DLLName(String node_DLLName) {
            Node_DLLName = node_DLLName;
        }
    }

    private Node_DLL header =
            new Node_DLL(null, null, null, "Header"); // dummy
    private Node_DLL trailer =
            new Node_DLL(null, null, null, "Trailer"); // dummy
    private int size;

    private DoublyLinkedList() {
        header.setNext(trailer);
        trailer.setPrevious(header);
        size = 0;
    }

    private int getSize() {
        return this.size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void addFirst(data_DLL new_data) {
        if (new_data != null) {
            if (isEmpty()) {
                Node_DLL new_node = new Node_DLL(new_data, trailer, header, null);
                header.setNext(new_node);
                trailer.setPrevious(new_node);
                return;
            } else {
                Node_DLL new_node = new Node_DLL(new_data, header.next, header, null);
                header.getNext().setPrevious(new_node);
                header.setNext(new_node);
                return;
            }
        }
    }

    private void addBetween() {

    }

    private void addLast(data_DLL new_data) {
        if (new_data != null) {
            if (isEmpty()) {
                Node_DLL new_node = new Node_DLL(new_data, trailer, trailer.previous, null);
                trailer.getPrevious().setNext(new_node);
                trailer.setPrevious(new_node);
                return;
            } else {
                Node_DLL new_node = new Node_DLL(new_data, trailer, trailer.previous, null);

                return;
            }
        }

    }

}
