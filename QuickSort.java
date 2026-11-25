import java.util.Scanner;
import java.util.Arrays;

public class QuickSort {
    static int partition(int[] arr,int low,int high){
        int pivot = arr[high];
        int i = low-1;

        for (int j=low;j<high;j++){
            if (arr[j]<=pivot){
                i++;
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        int t = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = t;
        return i+1;
    }
    static void sort(int[] arr,int low,int high){
        if (low<high){
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("\nEnter "+n+"elements one by one: ");
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        sort(arr,0,n-1);
        System.out.println("\nThe sorted array is: "+Arrays.toString(arr));

    }
}