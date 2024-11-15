import java.util.Scanner;

public class MemoryPlacement {

    // Function for First Fit memory allocation
    public static void firstFit(int[] memoryBlocks, int[] processes) {
        System.out.println("\nFirst Fit Allocation:");
        int[] allocation = new int[processes.length];

        // Initialize allocation array to -1 (indicates not allocated)
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Try to allocate each process to memory blocks
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processes[i]) {
                    allocation[i] = j; // Allocate process to the block
                    memoryBlocks[j] -= processes[i]; // Reduce available memory
                    break;
                }
            }
        }

        // Display the allocation
        printAllocation(allocation, processes);
    }

    // Function for Best Fit memory allocation
    public static void bestFit(int[] memoryBlocks, int[] processes) {
        System.out.println("\nBest Fit Allocation:");
        int[] allocation = new int[processes.length];

        // Initialize allocation array to -1 (indicates not allocated)
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Try to allocate each process to the best block
        for (int i = 0; i < processes.length; i++) {
            int bestIdx = -1;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processes[i]) {
                    if (bestIdx == -1 || memoryBlocks[j] < memoryBlocks[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx; // Allocate process to the best block
                memoryBlocks[bestIdx] -= processes[i]; // Reduce available memory
            }
        }

        // Display the allocation
        printAllocation(allocation, processes);
    }

    // Helper function to print allocation
    public static void printAllocation(int[] allocation, int[] processes) {
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processes.length; i++) {
            System.out.printf("%d\t\t%d\t\t%s\n", i + 1, processes[i], (allocation[i] != -1) ? allocation[i] + 1 : "Not Allocated");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input memory blocks
        System.out.print("Enter number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        int[] memoryBlocks = new int[numBlocks];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < numBlocks; i++) {
            memoryBlocks[i] = scanner.nextInt();
        }

        // Input processes
        System.out.print("Enter number of processes: ");
        int numProcesses = scanner.nextInt();
        int[] processes = new int[numProcesses];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = scanner.nextInt();
        }

        // Make a copy of memory blocks for each strategy
        int[] memoryBlocksCopy = memoryBlocks.clone();

        // Perform First Fit and Best Fit allocation
        firstFit(memoryBlocks, processes);
        bestFit(memoryBlocksCopy, processes);

        scanner.close();
    }
}

    
