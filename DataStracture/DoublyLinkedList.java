package DataStracture;

class dNode {
    int data;
    dNode next, prev;

    dNode(int n) {
        this.data = n;
    }

    dNode(int data, dNode prev, dNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    int getData() {
        return data;
    }
}

class DoublyLinkedList {

    static dNode Create_a_DoubleLinkedList(int n) {
        dNode head = null;
        dNode p = null;
        for (int i = 0; i < n; i += 2) {
            if (i == 0) {
                head = p = new dNode(i, null, null);
            } else {
//                dNode new_node = new dNode(i, p, null);
//                p.next = new_node;
//                p = p.next;
                p.next = new dNode(i, p, null);
                p = p.next;
            }
        }
        return head;
    } // WORKING

    static dNode Insert_Node(dNode head, int new_node_data) {
        // first case : হেড থেকে ছোট কি না
        dNode new_node = null;
        if (new_node_data < head.data) {
            new_node = new dNode(new_node_data, null, head);
            head.prev = new_node;
            return new_node;
        } else {
            // second case : হেড বা শুরুর কর্ণারে নয়
            for (dNode p = head.next; p != null; p = p.next) { // linear search
                if (p.next == null) { // লাস্টে কি না
                    new_node = new dNode(new_node_data, p, null);
                    System.out.println(STR."new node data will be \{new_node_data} and it has become \{new_node.data}");
                    p.next = new_node;
                    return head;
                }
                if (p.data <= new_node_data && new_node_data < p.next.data) { // লিনিয়ার সার্চে খুজে পেলে
                    // যদি শিওরলি মাঝেই হয়
                    dNode next_node = p.next;
                    new_node = new dNode(new_node_data, p, next_node);
                    p.next = new_node;
                    next_node.prev = new_node;
                    return head;
                }
            }
        }
        return head;
    } // WORKING

    static dNode Delete_Node(dNode head, int key) {

        if (head.data == key) {
            dNode temp = head;
            head = head.next;
            temp.next = null;
            return head;
        }
        for (dNode p = head; p.next != null; p = p.next) {
            if ((p.next).next == null && p.next.data == key) {
                p.next.prev = null;
                p.next = null;
                return head; // after coding, I forgot to return or, forgot to terminate the code
            }
            if ((p.next).data == key) {
                dNode next_node = p.next.next;
                next_node.prev = p;
                p.next = next_node;
                return head;
            }
        }
        return head;
    } // WORKING

    static void PrintAllNodesOnce(dNode head) {
        dNode p = head;
        System.out.print("Printing All Nodes once: ");
        while (p != null) {
            System.out.print(STR."\{p.getData()} ");
            p = p.next;
        }
        System.out.println();
    } // DONE

    static void PrintAllNodesBoomerang(dNode head) {
        dNode p = head;
        System.out.print("Printing All Nodes once and then reversely: ");

        for (; ; p = p.next) {
            /*
             * যদি for loop এর কন্ডিশনে p.next != null দিতাম তখন লাস্ট নোডে যেয়ে এই কন্ডিশন
             * মিথ্যা হয়ে যাওয়ায় লুপের ভিতরে ঢুকে (মানে প্রিন্ট না করেই) লুপ থেকে বের হয়ে যেত
             * আর p = p.prev; এর জন্য লাস্ট নোড এ বসে তার আগের নোড কে ক্যাপচার করত
             * */
            System.out.print(STR."\{p.getData()} ");
            if (p.next == null) { // use if for break inside the loop
                p = p.prev;
                break;
            }
        }


        while (p != null) {
            System.out.print(STR."\{p.getData()} ");
            p = p.prev;
        }
        System.out.println();
    } // now WORKING


    // ================= MAIN METHOD =====================

    static void main(String[] args) {
        dNode head = Create_a_DoubleLinkedList(20);
//        PrintAllNodesOnce(head);
//        PrintAllNodesBoomerang(head);

        int k = 0;
        System.out.println(STR."Deleting k = \{k} into the linked list...");
        head = Delete_Node(head, k);
        PrintAllNodesBoomerang(head);
        PrintAllNodesOnce(head);
    }
}
