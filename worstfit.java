import java.util.Scanner;
class WorstFit {

    // Method to implement Worst Fit memory allocation
    static void worstFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];    // To store block allocated to each process
        int remblockSize[] = new int[n]; // To store remaining block size for allocated processes

        // Initialize all allocations as -1 (not allocated)
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        // Allocate memory to processes using the Worst Fit strategy
        for (int i = 0; i < n; i++) {
            int wstIdx = -1; // Index of the worst-fit block
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) { // Block can accommodate the process
                    if (wstIdx == -1 || blockSize[j] > blockSize[wstIdx]) {
                        wstIdx = j; // Update worst-fit block
                    }
                }
            }

            // If a suitable block is found
            if (wstIdx != -1) {
                allocation[i] = wstIdx;                // Allocate block to the process
                blockSize[wstIdx] -= processSize[i];  // Reduce the block size
                remblockSize[i] = blockSize[wstIdx];  // Store remaining block size
            } else {
                remblockSize[i] = -1; // Mark as unallocated
            }
        }

        // Print allocation details
        System.out.println("\nProcess No.\tProcess Size\tBlock No.\tRemaining Block Size");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print((allocation[i] + 1) + "\t\t" + remblockSize[i]);
            } else {
                System.out.print("Not Allocated\tN/A");
            }
            System.out.println();
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Input the number of memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int m = in.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = in.nextInt();
        }

        // Input the number of processes
        System.out.print("Enter the number of processes: ");
        int n = in.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter the memory requirements for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSize[i] = in.nextInt();
        }

        // Call the Worst Fit allocation method
        worstFit(blockSize, m, processSize, n);

        in.close();
    }
}
