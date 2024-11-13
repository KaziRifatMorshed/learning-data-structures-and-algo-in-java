package DS_Lab_tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

class StackArray<E> {
    private E[] stack;
    //    private int size = 100; /*stack_capacity; default 100*/
    private static final int size = 100; /*stack_capacity; default 100*/
    private int top_idx = -1; /*index of the top element*/

    StackArray(int stack_capacity) {
//        size = stack_capacity; // omitted for applying farhan sir's method
        stack = (E[]) new Object[stack_capacity];
    }

    StackArray() {
        this(size);
    }


    boolean isEmpty() {
        return top_idx < 0; /*zero can be an index of any array*/
    }

    int getCapacity() { /*get the max capacity of the stack*/ // ------------ CAREFUL; dont mix with getSize
        return stack.length;
    }

    int getSize() { /*get the total number of elements*/ // ---------------------- CAREFUL
        return top_idx + 1;
    }

    void push(E d) { // add last
        if (top_idx >= getCapacity()) {
//            System.out.println("Stack Overflow !!! int " + d + " failed to add");
        } else {
            top_idx++;
            stack[top_idx] = d;
//            System.out.println("INSERTION: data " + d + " has been pushed to stack");
        }
    } //

    E pop() { // remove last
        if (isEmpty()) {
//            System.out.println("DELETION: Stack is empty");
            return null;
        } else {
            E return_value = stack[top_idx];
            stack[top_idx] = null;
            top_idx--;
//            System.out.println("DELETION: data " + return_value + " has been popped");
            return return_value;
        }
    }

    E getTop() { // remove last
        if (isEmpty()) {
            return null;
        } else {
            E return_value = stack[top_idx];
            return return_value;
        }
    }

    void printStack() {
        /*
         * returns true if stack has element,
         * returns false if stack is empty
         **/
        if (isEmpty()) {
            System.out.println("PRINTING: Stack is empty");
        } else {
            System.out.print("PRINTING: ( ");
            for (int i = 0; i <= top_idx; i++) {
                System.out.print(stack[i]);
            }
            System.out.println(")");
        }
    } // completed


} // WORKING

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
        return parent; // returning parent, not child
    }

    Node<E> addRight(Node<E> parent, E element) {
        if (parent != null && parent.getRight() != null) {
            System.out.println("Right is not empty");
            return null;
        }
        Node<E> child = new Node<E>(element, parent, null, null);
        parent.setRight(child);
        return parent; // returning parent, not child
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void createTree(E[] arr) {
        int arrLen = arr.length;
        Node<E> rootNode = this.addRoot(arr[0]);
        Node<E> temp = this.getRoot();

        for (int i = 0; i <= Math.sqrt(arrLen); i = i * 2 + 1) {
            temp = this.addLeft(temp, arr[(i * 2) + 1]);// returning parent, not child
            temp = this.addRight(temp, arr[(i * 2) + 2]);// returning parent, not child
            temp = temp.getLeft();
        }
    }

    void eulerTourTraversal(int index) {
        Node<E> temp = this.getRoot();

        // team will reach desired index
        for (int i = 1; i <= index; i++) {
            if (temp.getLeft() != null) {
                temp = temp.getLeft();
            } else if (temp.getRight() != null) {
                temp = temp.getRight();
            } else {
                temp = temp.getParent();
            }
        }

        StackArray<Node<E>> stack = new StackArray<>();
        ArrayList<Node<E>> visited = new ArrayList<>();

        Node<E> p = temp;

        if (p != null) {
            stack.push(p);
        }
        while (!stack.isEmpty()) {
            Node<E> top = stack.getTop();
            if ((int) top.getElement() != 0) {
                System.out.print(top + " ");
            }
            visited.add(top);

            if (top.getLeft() != null && !visited.contains(top.getLeft())) {
                stack.push(top.getLeft());
            } else if (top.getRight() != null && !visited.contains(top.getRight())) {
                stack.push(top.getRight());
            } else {
                stack.pop();
            }
        }

    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class test {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 5, 0, 4, 0, 0, 0, 2, 3};
        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();
        tree1.createTree(arr1);
//        BTreePrinter.printNode(tree1.getRoot());
        tree1.eulerTourTraversal(0);
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class BTreePrinter {
    // this class is copied from https://stackoverflow.com/a/4973083/15236351
    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.element);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null) System.out.print("/");
                else BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null) System.out.print("\\");
                else BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    public static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null) return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) return false;
        }

        return true;
    }

}