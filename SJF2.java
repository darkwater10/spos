import java.util.*;

class Process {
    int processID, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime;

    public Process(int processID, int arrivalTime, int burstTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time and burst time for Process " + (i + 1));
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Sorting processes based on Arrival Time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[n];
        int[] remainingBurstTime = new int[n];
        for (int i = 0; i < n; i++) {
            remainingBurstTime[i] = processes[i].burstTime;
        }

        while (completed < n) {
            // Find the process with the shortest burst time among the available processes
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && processes[i].arrivalTime <= currentTime && remainingBurstTime[i] < minBurstTime) {
                    minBurstTime = remainingBurstTime[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                // If no process is ready to execute, move time forward
                currentTime++;
            } else {
                // Execute the chosen process
                Process p = processes[idx];
                p.completionTime = currentTime + p.burstTime;
                p.turnAroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnAroundTime - p.burstTime;

                currentTime = p.completionTime;
                isCompleted[idx] = true;
                completed++;
            }
        }

        // Output results
        System.out.println("ProcessID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (Process p : processes) {
            System.out.println(p.processID + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.turnAroundTime + "\t\t" + p.waitingTime);
        }
    }
}
