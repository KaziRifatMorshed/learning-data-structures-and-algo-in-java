package DataStracture.Tree;


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

class LinkedBinaryTree<E> {
    Node<E> root;
    int size = 0;

    private Node<E> createNode(E data, Node parent, Node left, Node right) {
        size++; // ----------------------------------------------------------------- farhan sir mistakenly skipped this
        return new Node<E>(data, parent, left, right);
    }

    public Node<E> getRoot() {
        return root;
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

//class DrawingTree<E> extends LinkedBinaryTree<E> {
//    public static <E> int layout(Node<E> T, Position<E> p, int d, int x) {
//        if (T.getLeft(p)){
//
//        }
//        return x;
//    }
//}

class BTreePrinter {
    // this class is copied from https://stackoverflow.com/a/4973083/15236351
    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

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

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

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
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}

class TestLinkedBinaryTree {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();

        Node<Integer> rootNode = tree1.addRoot(1); // ------------ root node ke ekta variable er vitore rakhtesi
        Node<Integer> currentNode = tree1.addLeft(rootNode, 2);
        currentNode = tree1.addLeft(currentNode, 4);
        currentNode = tree1.addRight(currentNode.getParent(), 5);
        currentNode = tree1.addRight(rootNode, 3);
        currentNode = tree1.addLeft(currentNode, 6);
        currentNode = tree1.addRight(currentNode.getParent(), 7);

        tree1.preOrderTraversal(rootNode);
        System.out.println();
        tree1.inOrderTraversal(rootNode);
        System.out.println();
        tree1.postOrderTraversal(rootNode);
        System.out.println();

        BTreePrinter.printNode(rootNode);
    }
}
