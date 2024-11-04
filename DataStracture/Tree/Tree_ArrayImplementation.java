package DataStracture.Tree;

class ArrayQueue<E> {
    public static final int CAPACITY = 100;
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return (sz == 0);
    }

    public void enqueue(E e) throws IllegalStateException {
        if (sz == data.length) {
            throw new IllegalStateException("Queue is full.");
        }
        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }
}


class Tree_ArrayImplementation<E> {
    public static final int CAPACITY = 100;
    private E[] data;
    private int size = 0;
    // no need root variable as index zero will be root

    Tree_ArrayImplementation() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    Tree_ArrayImplementation(int capacity) {
        data = (E[]) new Object[capacity];
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getBufferSize() {
        return this.data.length;
    }

    int getArraySize() {
        return getBufferSize();
    }

    void deleteAllTreeElement() {
        for (int i = 0; i < this.getSize(); i++) {
            data[i] = null;
        }
        size = 0;
    }


    boolean isInternal(int indexOfNode) {
        int leftChild_Idx = 2 * indexOfNode + 1;
        int rightChild_Idx = leftChild_Idx + 1;

        if ((leftChild_Idx < this.getBufferSize() && this.data[leftChild_Idx] != null) ||
                (rightChild_Idx < this.getBufferSize() && this.data[rightChild_Idx] != null)) {
            return true;
        }
        return false;
    }

    int numOfChild(int indexOfNode) throws IndexOutOfBoundsException {
        int leftChild_Idx = 2 * indexOfNode + 1;
        int rightChild_Idx = leftChild_Idx + 1;

        if (rightChild_Idx < this.getBufferSize()) {
            E leftChild = this.data[leftChild_Idx];
            E rightChild = this.data[rightChild_Idx];

            if (leftChild == null && rightChild == null) {
                return 0;
            } else if ((leftChild == null && rightChild != null) || (leftChild != null && rightChild == null)) {
                return 1;
            } else {
                return 2;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    void addRoot(E element) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Root Exists");
        }
        data[0] = element;
        size = 1;
    }

    int addLeft(int nodePosition, E element) throws IllegalStateException {
        int indexOfLeftChild = 2 * nodePosition + 1;
        if (indexOfLeftChild < this.getBufferSize()) { // -------------- remember
            throw new IllegalStateException("Buffer Shortage");
        }
        if (this.data[indexOfLeftChild] != null) {
            throw new IllegalStateException("Node Exists");
        } else {
            this.data[indexOfLeftChild] = element;
            size++;
            return indexOfLeftChild;
        }
    }

    int addRight(int nodePosition, E element) {
        int rightNodePosition = 2 * nodePosition + 2;
        if (rightNodePosition < this.getBufferSize()) {
            throw new IllegalStateException("Array Shortage");
        } else if (this.data[rightNodePosition] != null) {
            throw new IllegalStateException("Node Already Exists");
        } else {
            this.data[rightNodePosition] = element;
            size++;
            return rightNodePosition;
        }
    }

    int set(int indexOfNode, E element) {
        if (indexOfNode >= getBufferSize()) { // ---------- whether correct index inputted or not
            throw new IndexOutOfBoundsException();
        } else {
            this.data[indexOfNode] = element;
            return indexOfNode;
        }
    }


}
