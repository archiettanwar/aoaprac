import java.util.Scanner;

public class LCS{
    static void lcs(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        System.out.println("DP Table:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3d", dp[i][j]);
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        int i = m , j = n;
        while (i>0 && j>0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }
            else if (dp[i-1][j]>dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        System.out.println("\nThe LCS is "+sb.reverse().toString()+" length "+dp[m][n]);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first String: ");
        String s1 = sc.next();
        System.out.println("\nEnter second string: ");
        String s2 = sc.next();

        lcs(s1,s2);
    }
}