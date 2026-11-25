import java.util.Arrays;
import java.util.Scanner;

public class JobSequencing {
    static class Job {
        String id;
        int deadline, profit;
        Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    static void scheduleJobs(Job[] jobs, int maxDeadline) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        String[] slots = new String[maxDeadline + 1];
        int totalProfit = 0;

        for (Job job : jobs) {
            for (int j = job.deadline; j >= 1; j--) {
                if (slots[j] == null) {
                    slots[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        System.out.print("Scheduled Jobs: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slots[i] != null) System.out.print(slots[i] + " ");
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];
        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID, deadline, profit for job " + (i + 1) + ": ");
            String id = sc.next();
            int d = sc.nextInt();
            int p = sc.nextInt();
            jobs[i] = new Job(id, d, p);
            maxDeadline = Math.max(maxDeadline, d);
        }

        scheduleJobs(jobs, maxDeadline);
    }
}