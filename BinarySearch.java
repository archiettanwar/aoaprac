import java.util.Scanner;

public class BinarySearch {
    static int search(int[] arr,int target){
        int low = 0 , high = arr.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==target) return mid;
            else if(arr[mid]<target) low = mid+1;
            else high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("\nEnter the "+n+" elements in sorted order.");
        for (int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        System.out.println("\nEnter the element to search for: ");
        int target = sc.nextInt();

        int idx = search(arr, target);
        if (idx != -1) System.out.println("\nThe target is present and is at "+idx);
        else System.out.println("\nThe target is not present in the array.");
    }
}