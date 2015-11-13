package manage;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Defragger class that both pulls data and defrags through
 * quick sort method.
 * @author Nitin and navthakur
 * 
 */
public class Defrag {

    /**
     * ArrayList holding free blocks.
     */
    private ArrayList<MemBlock> freeList = new ArrayList<MemBlock>();
    
    /**
     * QuickSort to sort the list, to keep in order.
     */
    private QuickSort<MemBlock> quickSort;
    
    /**
     * BucketSort to sort the list, to keep in order.
     */
    private BucketSort<MemBlock> bucketSort;
    
    /**
     * Comparator for MemBlock to be used in sorts.
     */
    private Comparator<MemBlock> comparator;
    
    /**
     * Constructor to set up arraylist of blocks.
     * 
     * Inline comment has error throw
     * 
     * @param blockList List to sort and defrag.
     * @param maxSize total size of all memory
     */
    public Defrag(ArrayList<MemBlock> blockList, int maxSize) {
        
        if (blockList == null || blockList.size() < 1) {
            // need to make this an error throw
            return;
        }
        this.freeList = blockList;
        // initialize BucketSort but not QuickSort
        // because QuickSort sorts upon initialization
        
    }
    
    /**
     * Function to quickSort the freeList.
     */
    public void quickSort() {
        this.quickSort = new QuickSort<MemBlock>(this.freeList, new MemBlock.MemBlockComparator());
        this.freeList = this.quickSort.getList();
    }
    
    /**
     * Defragment the adjacent blocks after sorting. Assumes sorted list.
     */
    public void defragBlocks() {
        // make a loop to iterate through list of blocks
        // except last one, as always checking i block and
        // i + 1 for adjacency
        for (int i = 0; i < this.freeList.size() - 1; i++) {
            MemBlock curr = this.freeList.get(i);
            MemBlock next = this.freeList.get(i + 1);
            // check if the 2 blocks are adjacent    
            if(curr.getRightAdjacent() == next.getStartAddress()) {
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
        for (int i = 1; i < this.freeList.size() - 1; i++) {
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
}


















































