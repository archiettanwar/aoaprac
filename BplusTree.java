import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BplusTree {
    static int t = 2; // Minimum degree (max 2*t - 1 = 3 keys per node)

    static class Node {
        boolean leaf;
        List<Integer> keys = new ArrayList<>();
        List<Node> children = new ArrayList<>();
        Node next; // Links leaf nodes together

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    static Node root = new Node(true);

    static void splitChild(Node parent, int i, Node fullChild) {
        Node right = new Node(fullChild.leaf);
        int median = fullChild.keys.get(t - 1);

        if (fullChild.leaf) {
            // Leaf split: COPY median up (keep median in right leaf)
            right.keys.addAll(fullChild.keys.subList(t - 1, fullChild.keys.size()));
            fullChild.keys.subList(t - 1, fullChild.keys.size()).clear();

            // Stitch leaf linked list
            right.next = fullChild.next;
            fullChild.next = right;
        } else {
            // Internal split: PUSH median up (exact same as B-Tree)
            right.keys.addAll(fullChild.keys.subList(t, fullChild.keys.size()));
            right.children.addAll(fullChild.children.subList(t, fullChild.children.size()));
            fullChild.keys.subList(t - 1, fullChild.keys.size()).clear();
            fullChild.children.subList(t, fullChild.children.size()).clear();
        }

        parent.keys.add(i, median);
        parent.children.add(i + 1, right);
    }

    static void insertNonFull(Node node, int k) {
        int i = 0;
        while (i < node.keys.size() && k >= node.keys.get(i)) i++;

        if (node.leaf) {
            node.keys.add(i, k);
        } else {
            if (node.children.get(i).keys.size() == 2 * t - 1) {
                splitChild(node, i, node.children.get(i));
                if (k >= node.keys.get(i)) i++;
            }
            insertNonFull(node.children.get(i), k);
        }
    }

    static void insert(int k) {
        if (root.keys.size() == 2 * t - 1) {
            Node newRoot = new Node(false);
            newRoot.children.add(root);
            splitChild(newRoot, 0, root);
            root = newRoot;
        }
        insertNonFull(root, k);
    }

    static void printLeaves() {
        Node curr = root;
        while (!curr.leaf) {
            curr = curr.children.get(0); // Walk down to leftmost leaf
        }
        while (curr != null) {
            System.out.print(curr.keys + " -> ");
            curr = curr.next;
        }
        System.out.println("NULL");
    }

    static boolean search(Node node, int k) {
        if (node.leaf) return node.keys.contains(k);
        int i = 0;
        while (i < node.keys.size() && k >= node.keys.get(i)) i++;
        return search(node.children.get(i), k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            insert(sc.nextInt());
        }

        System.out.print("B+ Tree Leaf Linked List: ");
        printLeaves();

        System.out.print("Enter element to search: ");
        int target = sc.nextInt();
        System.out.println("Found: " + search(root, target));
    }
}