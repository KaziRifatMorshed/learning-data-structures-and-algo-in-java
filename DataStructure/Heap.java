package DataStructure;

class ArrayMaxHeap {
    public static final int CAPACITY = 100;
    private int[] data;
    private int size = 0;
    // No need of 'root' variable, since the root always stays in the 0 index of the array.

    // Array is initialized to zero values. In this program, zero value means empty/null.
    public ArrayMaxHeap() {
        this(CAPACITY);
    }

    public ArrayMaxHeap(int capacity) {
        data = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void heapifyUp(int lastIndex) {
        int lastElement = data[lastIndex];
        int tempIndex = lastIndex;
        while ((tempIndex > 0) && (lastElement > data[(tempIndex - 1) / 2])) {
            data[tempIndex] = data[(tempIndex - 1) / 2];
            tempIndex = (tempIndex - 1) / 2;
        }
        data[tempIndex] = lastElement;
    }

    public int heapifyDown(int lastIndex) {
        int tempIndex = 1;
        int lastElement = data[lastIndex];
        int maximumElement = data[0];
        lastIndex--;
        while (tempIndex < lastIndex) {
            if ((tempIndex < lastIndex) && (data[tempIndex] < data[tempIndex + 1])) {
                tempIndex++;
            }
            if (lastElement >= data[tempIndex]) {
                break;
            }
            data[(tempIndex - 1) / 2] = data[tempIndex];
            tempIndex = 2 * tempIndex + 1;
        }
        data[(tempIndex - 1) / 2] = lastElement;
        return maximumElement;
    }

    public void createMaxHeap(int[] arr) {
        // data array will contain the max heap
        for (int i = 0; i < arr.length; ++i) {
            data[i] = arr[i];
        }
        size = arr.length;
        for (int i = 1; i < size(); i++) {
            heapifyUp(i);
        }
    }

    public int deleteMaximum() {
        int maximumValue = heapifyDown(size() - 1);
        data[size() - 1] = 0;
        size--;
        return maximumValue;
    }

    public int[] heapSort() {
        for (int i = size() - 1; i > 0; i--) {
            data[i] = heapifyDown(i);
        }
        int[] arr = new int[size()];
        for (int i = 0; i < size(); i++) {
            arr[i] = data[i];
        }
        return arr;
    }

    public void preOrder(int indexOfNode) {
        if (data[indexOfNode] != 0) {
            System.out.print(data[indexOfNode] + " ");
            preOrder(2 * indexOfNode + 1);
            preOrder(2 * indexOfNode + 2);
        }
    }

    public void postOrder(int indexOfNode) {
        if (data[indexOfNode] != 0) {
            postOrder(2 * indexOfNode + 1);
            postOrder(2 * indexOfNode + 2);
            System.out.print(data[indexOfNode] + " ");
        }
    }

    public void inOrder(int indexOfNode) {
        if (data[indexOfNode] != 0) {
            inOrder(2 * indexOfNode + 1);
            System.out.print(data[indexOfNode] + " ");
            inOrder(2 * indexOfNode + 2);
        }
    }
}

class ArrayMaxHeapApp {
    public static void main(String[] args) {
        int[] arr = {50, 30, 40, 10, 20, 15};
        ArrayMaxHeap maxHeap = new ArrayMaxHeap();
        maxHeap.createMaxHeap(arr);
        maxHeap.preOrder(0);
        System.out.println("\nMaximum value " + maxHeap.deleteMaximum() + " is deleted.");
        maxHeap.preOrder(0);

        // Remember after sorting this is not a heap anymore.
        int[] sorted = maxHeap.heapSort();
        System.out.println("\nSorted array using heap sort: ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }
}

class TreeHeap {
    class Node<E> {
        E element;
        Node<E> parent, left, right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public String toString() {
            return this.element + " ";
        }
    }
    class LinkedBinaryTree<E> {
        Node<E> root;
        int size = 0;

        private Node<E> createNode(E data, Node parent, Node left, Node right) {
            size++; // ----------------------------------------------------------------- farhan sir mistakenly skipped this
            return new Node<E>(data, parent, left, right);
        }

        public Node<E> getRoot() {
            return this.root;
        }

        public void setRoot(Node<E> root) {
            this.root = root;
        }

        int getSize() {
            return size;
        }

        boolean isEmpty() {
            return size == 0;
        }


        public boolean isInternal(Node<E> node) {
            return (node.getRight() != null) && (node.getRight() != null);
        }

        int numberOfChild(Node<E> node) {
            if (node.getRight() == null && node.getLeft() == null) {
                return 0;
            } else if ((node.getLeft() == null && node.getRight() != null) || (node.getLeft() != null && node.getRight() == null)) {
                return 1;
            } else {
                return 2;
            }
        }

        Node<E> addRoot(E element) {
            this.root = createNode(element, null, null, null);
            this.size = 1;
            return root;
        }

        Node<E> addLeft(Node<E> parent, E element) {
            if (parent != null && parent.getLeft() != null) {
                System.out.println("Left is not empty.");
                return null;
            }
            Node<E> child = new Node<E>(element, parent, null, null);  // ------------------------- 2nd parameter hisebe parent add korte vule jassilamPosition
            parent.setLeft(child);
            return child;
        }

        Node<E> addRight(Node<E> parent, E element) {
            if (parent != null && parent.getRight() != null) {
                System.out.println("Right is not empty");
                return null;
            }
            Node<E> child = new Node<E>(element, parent, null, null);
            parent.setRight(child);
            return child;
        }

        //
//    void set(Node<E> parent, E newElement) {
//        parent.setElement(newElement);
//    }
        Node<E> set(Node<E> position, E newElement) {
            position.setElement(newElement);
            return position;
        }

        Node<E> updateNode(Node<E> position, E element) {
            return set(position, element);
        }

        void resetTree() {
            this.root = null;
            this.size = 0;
        }

        // CAREFUL
        boolean attach(Node<E> parent, LinkedBinaryTree<E> tree1, LinkedBinaryTree<E> tree2) {
            if (isInternal(parent)) {
                return false;
            }

            size += tree1.getSize() + tree2.getSize();

            tree1.getRoot().setParent(parent);
            parent.setLeft(tree1.getRoot());
            tree1.resetTree();

            tree2.getRoot().setParent(parent);
            parent.setRight(tree2.getRoot());
            tree2.resetTree();

            return true;
        }


        // may feel difficult
        Node<E> removeNode(Node<E> target) {

            if (numberOfChild(target) == 2) { // যদি দুইটা চাইল্ড থাকে তাহলে রিমুভ করব না , কিন্তু কেন
                return null;
            }

            Node<E> child = (target.getLeft() != null) ? target.getLeft() : target.getRight();

            if (child != null) {
                child.setParent(target.getParent()); //
            }
            if (target == root) {
                root = child;
                // আমি ডিলেট করার জন্য যে নোড পাস করেছি সেটা যদি রুট হয়ে
                // রুটের দুটা চাইল্ড থাকলে ডিলেট করতে পারব না , যেটা আগেই চেক করে এসেছি
                //
            } else {
                Node<E> targetParent = target.getParent();
                if (target == targetParent.getLeft()) {
                    targetParent.setLeft(child);
                } else if (target == targetParent.getRight()) {
                    targetParent.setRight(child);
                }
            }

            size--;
            target.setElement(null);
            target.setParent(null);
            target.setLeft(null);
            target.setRight(null);

            //        return position; // ------------------------------------ Why amra position remove korbo na ?
            return child;
        }

        void preOrderTraversal(Node<E> currentNode) {
            if (currentNode != null) { // remember base case
                System.out.print(currentNode.element + " ");
                preOrderTraversal(currentNode.getLeft());
                preOrderTraversal(currentNode.getRight());
            }
        }

        void inOrderTraversal(Node<E> currentNode) {
            if (currentNode != null) {
                inOrderTraversal(currentNode.getLeft());
                System.out.print(currentNode.element + " ");
                inOrderTraversal(currentNode.getRight());
            }
        }

        void postOrderTraversal(Node<E> currentNode) {
            if (currentNode != null) {
                postOrderTraversal(currentNode.getLeft());
                postOrderTraversal(currentNode.getRight());
                System.out.print(currentNode.element + " ");
            }
        }


    }

}