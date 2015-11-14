package manage;
/**
 * Memory Simulation for 600.226 - partial main driver for I/O handling.
 */

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Driver for MemSimulator project - partial skeleton for I/O handling.
 */

final class MemSimMain {

    /**
     * Empty Constructor for Checkstyle.
     */
    private MemSimMain() {
    }

    /**
     * Main program.
     * @param args for Checkstyle
     * @throws FileNotFoundException if file not found
     * @throws IOException for bad filename
     */
    public static void main(String[] args) throws FileNotFoundException,
    IOException {

        final int numApproaches = 3;
        MemoryManager[] sims = new MemoryManager[numApproaches];
        // sims[0] must be best fit, sims[1] is worst fit, sims[2] is yours
        
        // read input filename from keyboard, or get from command-line args
        String filename = "";
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter filename: ");
            filename = sc.nextLine();
        } else {
            filename = args[0];
        }

        Scanner fromFile = new Scanner(new File(filename));
        int memSize = 0;

        try {
            memSize = Integer.parseInt(fromFile.nextLine());
        } catch (NumberFormatException e) {
            memSize = -1;
            System.out.println("invalid memory size, exiting");
            System.exit(1);
        }


        PrintWriter outPut1 = new PrintWriter("translog.txt");
        outPut1.println("Memory Transaction Log");
        outPut1.println("Memory Size:" + memSize);
        outPut1.println("Input file: " + filename);
        outPut1.println("Memory Address begins at location 0");
        outPut1.println();

        outPut1.println("------------------------------------------------------"
            + "----------------------------------------------------------");
        outPut1.println("           Best Fit                                "
            + "Worst Fit                                Your Fit");
        outPut1.println("------------------------------------------------------"
            + "----------------------------------------------------------");
        outPut1.println("   ID  DF? Success  Addr.  Size           ID  DF? "
            + "Success  Addr.  Size           ID  DF? Success  Addr.  Size");
        outPut1.println();

        ArrayList<String> lines = new ArrayList<String>();
        String output = "";


        // insert lots of stuff here to fill lines, calling methods below
        WorstFitAlloc priorityQueue = new WorstFitAlloc(memSize);
        BestFit avlTree = new BestFit(memSize);
        NextFitAlloc queue = new NextFitAlloc(memSize);
        
        sims[0] = avlTree;
        sims[1] = priorityQueue;
        sims[2] = queue;
        //fromFile.next();
        int val;
        while (fromFile.hasNext()) {
            String temp = fromFile.next();
            System.out.println(temp);
            if (temp.compareTo("A") == 0) {
                val = fromFile.nextInt();
                sims[1].alloc(val, false);
            } else if (temp.compareTo("D") == 0) {
                val = fromFile.nextInt();
                sims[1].dealloc(val);
            } else {
                System.out.println("Neither A nor D");
                //fromFile.next();
            }
        }
     
        printTrans(outPut1, lines);
        outPut1.close();
        PrintWriter outPut = new PrintWriter("analysis.txt");
        printOutput(outPut, sims, memSize, filename); 

    }


    /**
     * Formats allocation attempt for transaction log.
     * @param output String with allocation request info.
     * @param address Current address of allocation
     * @param defragged True if defrag occurred, false otherwise
     * @param id Current id number
     * @param size Allocation size
     * @return String representation of the allocation
     */
    public static String formatAlloc(String output, int address,
        boolean defragged, int id, int size) {

        String defrag = "";
        String sucString = "SUCCESS";

        if (defragged) {
            defrag = "DF";
        }
        if (address == -1) {
            sucString = "FAILED";
        }
        output += String.format("A%4d%4s  %-7s%6s%7s        ", 
             id, defrag, sucString, address, size);

        return output;
    }

     /**
     * Formats lines from deallocations for transaction log.
     * @param output String with deallocation info.
     * @param temp Block from deallocation
     * @param id Current id number
     * @return String representation of the deallocation
     */
    public static String formatDealloc(String output, MemBlock temp,
        int id) {

        String sucString = "SUCCESS";
        int deallocSize = 0;
        int address = 0;

        if (temp == null) {
            sucString = "FAILED";
            address = -1;
        } else {
            address = temp.getStartAddress();
            deallocSize = temp.getSize();
        }
        output += String.format("D%4s%4s  %-7s%6s%7s        ",
            id, "", sucString, address, deallocSize);
        return output;
    }

    /**
    * Print lines to transaction log.
    * @param outPut PrintWriter for transaction log
    * @param lines Arraylist of lines to be printed
    */
    public static void printTrans(PrintWriter outPut,
        ArrayList<String> lines) {
        for (String item : lines) {
            outPut.println(item);
        }
    }

    /**
    * Print lines to analysis log.
    * @param outPut PrintWriter for analysis log
    * @param sims Array of MemoryManagers
    * @param memSize Amount of memory in this simulation
    * @param filename Input file name
    */
    public static void printOutput(PrintWriter outPut, MemoryManager[] sims,
        int memSize, String filename) {
        outPut.println("Performance Analysis Chart");
        outPut.println("Memory Size " + memSize);
        outPut.println("Input File Used: " + filename);
        outPut.println("---------------------------------------------------"
            + "-------------------------");
        outPut.println("Statistics:                        Best Fit    "
            + "Worst Fit     Your Fit");
        outPut.println("---------------------------------------------------"
            + "-------------------------");
        outPut.println("");
        outPut.printf("%-35s%8s%13s%13s", "Number of Defragmentations:",
            sims[0].defragCount(), sims[1].defragCount(),
            sims[2].defragCount());
        outPut.println("");
        outPut.printf("%-35s%8s%13s%13s", "# of failed allocation requests:",
            sims[0].totalFails(), sims[1].totalFails(),
            sims[2].totalFails());
        outPut.println("");
        outPut.printf("%-35s%8s%13s%13s", "Average size failed allocs:",
            sims[0].avgFailSize(), sims[1].avgFailSize(),
            sims[2].avgFailSize());
        outPut.println("");
        outPut.printf("%-35s%8.2f%13.2f%13.2f",
                "Average time to process alloc*:",
            sims[0].avgTime(), sims[1].avgTime(),
            sims[2].avgTime());
        outPut.println("");
        outPut.printf("%-35s%8.2f%13.2f%13.2f",
                "Average time/size quicksort*:",
            sims[0].avgSortRatio(false), sims[1].avgSortRatio(false),
            sims[2].avgSortRatio(false));
        outPut.println("");
        outPut.printf("%-35s%8.2f%13.2f%13.2f",
                "Average time/size bucketsort*:",
            sims[0].avgSortRatio(true), sims[1].avgSortRatio(true),
            sims[2].avgSortRatio(true));
        outPut.println("");
        outPut.println("");
        outPut.println("*All times in microseconds.");
        outPut.close();
    }
}
