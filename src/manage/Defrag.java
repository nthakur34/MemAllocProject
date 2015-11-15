package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Defragger class that both pulls data and defrags through
 * quick sort method.
 * @author Nitin and navthakur
 * Navjyoth Thakur - nthakur2 -  600.226.01 - P4
 * 
 */
public class Defrag {

    /**
     * ArrayList holding free blocks.
     */
    private ArrayList<MemBlock> freeList;
    
    /**
     * QuickSort to sort the list, to keep in order.
     */
    private QuickSort<MemBlock> quickSort;
    
    /**
     * BucketSort to sort the list, to keep in order.
     */
    private BucketSort<MemBlock> bucketSort;
    
    /**
     * Constructor to set up arraylist of blocks.
     * 
     * Inline comment has error throw
     * 
     * @param blockList List to sort and defrag.
     * @param maxSize total size of all memory
     */
    public Defrag(Collection<MemBlock> blockList, int maxSize) {
        if (blockList == null) {
            System.err.println("No list");
            return;
        }
        this.freeList = new ArrayList<MemBlock>();
        this.freeList.addAll(blockList);
        // initialize BucketSort but not QuickSort
        // because QuickSort sorts upon initialization
        this.bucketSort = new BucketSort<MemBlock>(maxSize);
        this.quickSort = new QuickSort<MemBlock>(this.freeList);
    }
    
    /**
     * Function to quickSort the freeList.
     * @return how long the program took to run
     */
    public long quickSort() {
        long startTime = System.nanoTime();
        this.quickSort.sort(new MemBlock.MemBlockComparator());
//        this.quickSort = new QuickSort<MemBlock>(this.freeList,
//                new MemBlock.MemBlockComparator());
        this.freeList = this.quickSort.getList();
        return System.nanoTime() - startTime;
    }
    
    /**
     * Function to bucketSort the freeList.
     * @return how long the program took to run
     */
    public long bucketSort() {
        long startTime = System.nanoTime();
        this.bucketSort.sort(this.freeList);
        long timeDifference = System.nanoTime() - startTime;
        this.freeList = this.bucketSort.getData();
        return timeDifference;
    }
    
    /**
     * Defragment the adjacent blocks after sorting. Assumes sorted list.
     */
    public void defragBlocks() {
        if (this.freeList == null) {
            return;
        }
        // make a loop to iterate through list of blocks
        // except last one, as always checking i block and
        // i + 1 for adjacency
        for (int i = 0; i < this.freeList.size() - 1; i++) {
            MemBlock curr = this.freeList.get(i);
            MemBlock next = this.freeList.get(i + 1);
            // check if the 2 blocks are adjacent    
            if (curr.getRightAdjacent() == next.getStartAddress()) {
                // if they are, combine the 2
                curr.combineData(next);
                // move the current expanded block forward an index
                this.freeList.set(i + 1, curr);
                // set the previous one to null
                this.freeList.set(i, null);
            }
        }
    }
    
    /**
     * Get string form.
     * @return string form "[1, 2, 4]"
     */
    public String toString() {
        // if list is null or empty, return empty brackets
        if (this.freeList == null || this.freeList.size() <= 1)  {
            return "[]";
        }
        // if not, return a bigger list
        String list = "[";
        // iterate through array list
        for (int i = 0; i < this.freeList.size() - 1; i++) {
            // if value is not null
            // add to string being outputted
            if (this.freeList.get(i) != null) {
                list += this.freeList.get(i).getStartAddress() + ", ";
            }
        }
        list += this.freeList.get(this.freeList.size() - 1)
                .getStartAddress() + "]";
        return list;
        
    }
    
    /**
     * gives arraylist back sorted and defragged.
     * Return arraylist (post sort).
     * @return sorted arraylist
     */
    public ArrayList<MemBlock> getCollection() {
        return this.freeList;
    }
}