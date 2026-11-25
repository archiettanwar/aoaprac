import java.util.Scanner;

public class RecursionTree {
    static class Node{
        int val;
        Node left,right;
        Node (int val){this.val=val;}
    }
    static Node buildtree(int n){
        Node root = new Node(n);
        if (n>1){
            root.left = buildtree(n/2);
            root.right = buildtree(n/2);
        }
        return root;
    }
    static void inorder(Node root){
        if (root == null) return ;
        inorder(root.left);
        System.out.println(root.val+" ");
        inorder(root.right);
    }
    static int height(Node root){
        if (root==null) return 0;
        return 1+Math.max(height(root.left),height(root.right));
    }
    static boolean search(Node root,int target){
        if(root == null) return false;
        if (root.val == target) return true;
        return search(root.left, target) || search(root.right, target);
    }
    static int totalWork(Node root){
        if(root == null) return 0;
        return root.val+totalWork(root.left)+totalWork(root.right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of n (e.g.,8): ");
        int n = sc.nextInt();

        Node root = buildtree(n);

        System.out.println("\nThe Inorder traversal is: ");
        inorder(root);
        System.out.println("\nThe height of the tree is "+height(root));
        System.out.println("\nEnter target to find in the tree: ");
        int target = sc.nextInt();

        System.out.println("\nFound target: "+search(root, target));
        System.out.println("\nTotal work done: "+totalWork(root));
    }
}