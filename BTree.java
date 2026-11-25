import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BTree {
    static int t = 2; // Minimum degree (max 2*t - 1 = 3 keys per node)

    static class Node {
        boolean leaf;
        List<Integer> keys = new ArrayList<>();
        List<Node> children = new ArrayList<>();

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    static Node root = new Node(true);

    static void splitChild(Node parent, int i, Node fullChild) {
        Node right = new Node(fullChild.leaf);
        int median = fullChild.keys.get(t - 1);

        // 1. Move right half to new right node
        right.keys.addAll(fullChild.keys.subList(t, fullChild.keys.size()));
        if (!fullChild.leaf) {
            right.children.addAll(fullChild.children.subList(t, fullChild.children.size()));
        }

        // 2. Trim old left node
        fullChild.keys.subList(t - 1, fullChild.keys.size()).clear();
        if (!fullChild.leaf) {
            fullChild.children.subList(t, fullChild.children.size()).clear();
        }

        // 3. Push median and right child into parent
        parent.keys.add(i, median);
        parent.children.add(i + 1, right);
    }

    static void insertNonFull(Node node, int k) {
        int i = 0;
        while (i < node.keys.size() && k > node.keys.get(i)) i++;

        if (node.leaf) {
            node.keys.add(i, k);
        } else {
            if (node.children.get(i).keys.size() == 2 * t - 1) {
                splitChild(node, i, node.children.get(i));
                if (k > node.keys.get(i)) i++;
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

    static void traverse(Node node) {
        for (int i = 0; i < node.keys.size(); i++) {
            if (!node.leaf) traverse(node.children.get(i));
            System.out.print(node.keys.get(i) + " ");
        }
        if (!node.leaf) traverse(node.children.get(node.keys.size()));
    }

    static boolean search(Node node, int k) {
        int i = 0;
        while (i < node.keys.size() && k > node.keys.get(i)) i++;
        if (i < node.keys.size() && node.keys.get(i) == k) return true;
        if (node.leaf) return false;
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

        System.out.print("B-Tree Traversal: ");
        traverse(root);

        System.out.print("\nEnter element to search: ");
        int target = sc.nextInt();
        System.out.println("Found: " + search(root, target));
    }
}
