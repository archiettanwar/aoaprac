import java.util.Scanner;
import java.util.Arrays;

public class MergeSort {
    static void merge(int[] arr,int l,int mid, int r){
        int i=l,j=mid+1,k=0;
        int[] temp = new int[r-l+1];
        while(i<=mid && j<=r){
            if(arr[i]<=arr[j]){
                temp[k++]=arr[i++];
            }
            else{
                temp[k++]=arr[j++];
            }
        }
        if (i<=mid) temp[k++]=arr[i++];
        if (j<=r) temp[k++]=arr[j++];
        for(int idx=0;idx<temp.length;idx++){
            arr[l+idx]=temp[idx];
        }
    }
    static void sort(int[] arr,int l,int r){
        if(l>=r) return ;
        int mid=(l+r)/2;
        sort(arr, l, mid);
        sort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("\nEnter the elements: ");
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        sort(arr, 0, n-1);
        System.out.println("\nThe sorted array is : "+Arrays.toString(arr));
    }
}
