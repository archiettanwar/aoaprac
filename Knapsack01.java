import java.util.Scanner;

public class Knapsack01{
    static int knapsack(int W,int[] wt,int[] val,int n){
        int[][] dp = new int[n+1][W+1];
        for (int i = 1 ; i <= n ; i++){
            for (int w=1;w<=W;w++){
                if(wt[i-1]<=w){
                    int skip = dp[i-1][w];
                    int take = val[i-1]+dp[i-1][w-wt[i-1]];
                    dp[i][w] = Math.max(skip,take);
                }
                else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][W];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of items: ");
        int n = sc.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];

        System.out.println("\nEnter the weights one by one: ");
        for(int i=0;i<n;i++){
            wt[i]=sc.nextInt();
        }
        System.out.println("\nEnter the values one by one: ");
        for(int i=0;i<n;i++){
            val[i]=sc.nextInt();
        }

        System.out.println("\nEnter the capacity of the kanpsack: ");
        int W = sc.nextInt();

        System.out.println("\nThe max value of the knapsack is: "+knapsack(W, wt, val, n));
    }
}