import java.util.Scanner;

public class MasterMethod {
    static void solveMaster(int a, int b, double k) {
        System.out.println("\n--- MASTER METHOD ANALYSIS ---");
        if (a < 1 || b <= 1) {
            System.out.println("Invalid input: Master Theorem requires a >= 1 and b > 1.");
            return;
        }

        System.out.println("Recurrence: T(n) = " + a + "T(n/" + b + ") + O(n^" + k + ")");
        double c = Math.log(a) / Math.log(b);
        c = Math.round(c * 100.0) / 100.0;

        System.out.println("Step 1: Critical exponent c = log_" + b + "(" + a + ") = " + c);
        System.out.println("Step 2: Compare Leaf Work n^" + c + " vs Root Work f(n) = n^" + k);

        if (c > k) {
            System.out.println("Result: Since c (" + c + ") > k (" + k + "), recursion leaves dominate.");
            System.out.println("Case 1 Applies:");
            System.out.println("Final Complexity: T(n) = Theta(n^" + c + ")");
        } else if (Math.abs(c - k) < 1e-9) {
            System.out.println("Result: Since c (" + c + ") == k (" + k + "), both sides do equal work.");
            System.out.println("Case 2 Applies:");
            System.out.println("Final Complexity: T(n) = Theta(n^" + k + " * log n)");
        } else {
            System.out.println("Result: Since c (" + c + ") < k (" + k + "), outside work f(n) dominates.");
            System.out.println("Case 3 Applies:");
            System.out.println("Final Complexity: T(n) = Theta(n^" + k + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a (number of subproblems, >= 1): ");
        int a = sc.nextInt();

        System.out.print("Enter b (factor size shrinks by, > 1): ");
        int b = sc.nextInt();

        System.out.print("Enter k (power of n in f(n) = n^k): ");
        double k = sc.nextDouble();

        solveMaster(a, b, k);
    }
}
