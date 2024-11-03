package DataStracture.Tree;

import com.sun.source.tree.Tree;

import javax.annotation.processing.SupportedSourceVersion;

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
        Node<E> child = new Node<E>(element, parent, null, null);  // ------------------------- 2nd parameter hisebe parent add korte vule jassilam
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

class TestLinkedBinaryTree {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();

        Node<Integer> rootNode = tree1.addRoot(1); // --------------------------------- root node ke ekta variable er vitore rakhtesi
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

    }
}
