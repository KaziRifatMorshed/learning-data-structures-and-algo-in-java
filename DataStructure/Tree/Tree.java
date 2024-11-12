package DataStructure.Tree;


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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    8.5
    Describe an algorithm, relying only on the BinaryTree operations, that counts the
    number of leaves in a binary tree that are the left child of their respective parent.
    */
    int countLeftLeaves(Node<E> n) {
        if (n == null) {
            return 0;
        } else {
            int count = 0;
//            if ((n.getLeft() != null && n.getRight() == null) && // ------------- "&& n.getRight() == null)" হবে না
            if ((n.getLeft() != null) && (n.getLeft().getLeft() == null && n.getLeft().getRight() == null)) {
                count++;
            }
            return count + countLeftLeaves(n.getLeft()) + countLeftLeaves(n.getRight());
        }
    } // DONE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        Two ordered trees T` and T`` are said to be isomorphic if one of the following
        holds:
        • Both T` and T`` are empty.
        • Both T` and T`` consist of a single node
        • The th roots of T` and T`` have the same number th k ≥ 1 of subtrees, and the
        i-th such subtree of T` is isomorphic to the i-th such subtree of T`` for i = 1, ... , k.
        Design an algorithm that tests whether two given ordered trees are isomorphic.
        What is the running time of your algorithm?
    * */
    boolean isIsomorphic(Node<E> m, Node<E> n) {
        if (m == null && n == null) { // base case
            return true;
        }
        if ((m != null && n == null) || (m == null && n != null)) { // base case
//        if (m == null || n == null) { // base case
            return false;
        }
        if (!m.getElement().equals(n.getElement())) {
            return false;
        }
        return isIsomorphic(m.getLeft(), n.getLeft()) && isIsomorphic(m.getRight(), n.getRight());
    } // WORKING but NOT SURE ABOUT EDGE CASE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
    C-8.27
    Describe an efficient algorithm for converting a fully balanced string of paren-theses
    into an equivalent tree. The tree associated with such a string is defined
    recursively. The outermost pair of balanced parentheses is associated with the
    root and each substring inside this pair, defined by the substring between two
    balanced parentheses, is associated with a subtree of this root.
* */ // HARD


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*C-8.28
    The path length of a tree T is the sum of the depths of all positions in T . Describe
    a linear-time method for computing the path length of a tree T .
    */
    int calculatePathLength() {
        return _calculatePathLength(root, 0);
    }

    private int _calculatePathLength(Node<E> n, int d) {
        if (n == null) {
            return 0;
        }
        return d + _calculatePathLength(n.getLeft(), d + 1) + _calculatePathLength(n.getRight(), d + 1);
    } // SHOULD WORK

    int calculateInternalPathLength() {
        return _calculateInternalPathLength(this.getRoot(), 0);
    }

    private int _calculateInternalPathLength(Node<E> n, int d) {
        if (n == null) {
            return 0;
        }
        int pathLength = 0;
        if (isInternal(n)) {
            pathLength += d;
        }
        return d + _calculateInternalPathLength(n.getLeft(), d + 1) + _calculateInternalPathLength(n.getRight(), d + 1);
    } // Should Work


    /*
    Count number of nodes in a subtree
    */
    int countNodes(Node<E> n) {
        if (n == null) return 0;
        return 1 + countNodes(n.getLeft()) + countNodes(n.getRight());
    }


    /*
    * C-8.36 Add support in LinkedBinaryTree for a method, pruneSubtree(p), that removes
    the entire subtree rooted at position p, making sure to maintain an accurate count
    of the size of the tree. What is the running time of your implementation?
    * */
    void pruneSubtree(Node<E> p) {
        size -= countNodes(p);
        Node<E> parent = p.getParent();
        if (parent != null) {
            if (parent.getLeft() == p) {
                parent.setLeft(null);
            } else if (parent.getRight() == p) {
                parent.setRight(null);
            }
        } else { // root node does not have any parent
            // If p is the root, set the root to null to prune the whole tree
            this.setRoot(null);
        }
        _pruneSubtree(p);
    }

    void _pruneSubtree(Node<E> p) {
        if (p != null) {
            _pruneSubtree(p.getLeft());
            _pruneSubtree(p.getRight());
            p.setLeft(null);
            p.setRight(null);
            p.setParent(null);
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


class TestLinkedBinaryTree {
    public static void main(String[] args) {

        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();
        Node<Integer> rootNode1 = tree1.addRoot(1); // ------------ root node ke ekta variable er vitore rakhtesi
        Node<Integer> currentNode = tree1.addLeft(rootNode1, 2);
        currentNode = tree1.addLeft(currentNode, 4);
        currentNode = tree1.addRight(currentNode.getParent(), 5);
        currentNode = tree1.addRight(rootNode1, 3);
        currentNode = tree1.addLeft(currentNode, 6);
        currentNode = tree1.addRight(currentNode.getParent(), 7);

        LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
        Node<Integer> rootNode2 = tree2.addRoot(1); // ------------ root node ke ekta variable er vitore rakhtesi
        Node<Integer> currentNode2 = tree2.addLeft(rootNode2, 2);
        currentNode2 = tree2.addLeft(currentNode2, 4);
        currentNode2 = tree2.addRight(currentNode2.getParent(), 5);
        currentNode2 = tree2.addRight(rootNode2, 3);
        currentNode2 = tree2.addLeft(currentNode2, 6);
        currentNode2 = tree2.addRight(currentNode2.getParent(), 7);

//        tree1.preOrderTraversal(rootNode1);
//        System.out.println();
//        tree1.inOrderTraversal(rootNode1);
//        System.out.println();
//        tree1.postOrderTraversal(rootNode1);
//        System.out.println();
//        tree1.preOrderTraversal(rootNode2);
//        System.out.println();
//        tree1.inOrderTraversal(rootNode2);
//        System.out.println();
//        tree1.postOrderTraversal(rootNode2);
//        System.out.println();

        BTreePrinter.printNode(tree1.getRoot());
        BTreePrinter.printNode(tree2.getRoot());
//        System.out.println(tree1.countLeftLeaves(tree1.getRoot()));
        System.out.println(tree1.isIsomorphic(tree1.getRoot(), tree2.getRoot()));
        System.out.println(tree1.countNodes(tree1.getRoot()));
    }
}
