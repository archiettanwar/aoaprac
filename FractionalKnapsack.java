import java.util.Scanner;
import java.util.Arrays;

public class FractionalKnapsack {
    static class Item{
        int id,val,wt;
        Item(int id,int val,int wt){
            this.id=id;
            this.val=val;
            this.wt=wt;
        }
    } 
    static double getMaxValue(Item[] items,int capacity){
        Arrays.sort(items,(a,b)->Double.compare((double) b.val/b.wt,(double) a.val/a.wt));
        double totalValue = 0.0;
        System.out.println("Selection Sequence");

        for(Item item:items){
            if (capacity==0) break;
            if (capacity>=item.wt){
                capacity-=item.wt;
                totalValue+=item.val;
                System.out.println("\n->item "+item.id+" (value:"+item.val+" weight:"+item.wt+"). Took 100% | Value added: "+item.val);
            }
            else{
                double fraction = (double) capacity/item.wt;
                double roundedFraction = Math.round(fraction * 100.0) / 100.0;
                double addedval = item.val * fraction;
                totalValue += addedval;
                System.out.println("\n->item "+item.id+" (value:"+item.val+" weight:"+item.wt+"). Took "+roundedFraction*100+"% | Value added: "+addedval);
                capacity = 0;
                break;
            }
        }
        return totalValue;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n];

        for (int i = 0 ; i<n ; i++){
            System.out.println("\nEnter the value and weight for "+(i+1)+" : ");
            int v = sc.nextInt();
            int w = sc.nextInt();
            items[i] = new Item(i+1,v,w);
        }
        System.out.println("\nEnter Knapsack capacity: ");
        int W = sc.nextInt();

        double maxval = getMaxValue(items, W);
        System.out.println("\nThe maxxvalue is: "+maxval);
    }
}
