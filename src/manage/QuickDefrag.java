package manage;

import java.util.ArrayList;

/**
 * @author navthakur
 *
 * @param <T>
 */
public class QuickDefrag<T extends Comparable<? super T>> {

    /**
     * Arraylist holding free blocks
     */
    private ArrayList<T> freeList = new ArrayList<T>();
    
    /**
     * Constructor to set up arraylist of blocks
     * @param blockList List to sort and defrag.
     */
    public QuickDefrag(ArrayList<T> blockList) {
        
        if (blockList == null || blockList.size() <= 1) {
            return;
        }
        this.freeList = blockList;
        this.quickSort(1, blockList.size() - 1);
    }
    
    /**
     * quick sorts all values
     * @param lowerIndex lower index to compare to pivot
     * @param higherIndex higher index to compare to pivot
     */
    private void quickSort(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        int pivot = ((MemBlock) this.freeList.get(lowerIndex + (higherIndex 
                - lowerIndex) / 2)).getStartAddress();
        
        while (i <= j) {
            while (((MemBlock) this.freeList.get(i)).getStartAddress() < (pivot)) {
                i++;
            }
            while (((MemBlock) this.freeList.get(j)).getStartAddress() > pivot) {
                j--;
            }
            if (i <= j) {
                T temp = this.freeList.get(i);
                this.freeList.set(i, this.freeList.get(j));
                this.freeList.set(j, temp);
                i++;
                j--;
            }
        }
        
        if (lowerIndex < j) {
            this.quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            this.quickSort(i, higherIndex);
        }   
    }
    
    /**
     * Defragment the adjacent blocks.
     */
    public void defragBlocks() {
     
        int i = 1;
        int j = 1;

        while (i < this.freeList.size() - 1) {
            int size = 0;
            int count = 0;

            while (this.hasAdjacent(j)) {
                size += ((MemBlock) this.freeList.get(j)).getSize();
                count++;
                j++;
            }
            if (count > 0) {
                size += ((MemBlock) this.freeList.get(j)).getSize();
                this.combine(i, j, size);
                j = 1;
            } else {
                j++;
            }
            i = j;
            
        }
        
        this.quickSort(1, this.freeList.size() - 1);
    }
    
    /**
     * check if has adjacent.
     * @param index index to check for adjacent blocks
     * @return true if adjacent, else false.
     */
    private boolean hasAdjacent(int index) {
        //HOW TO WRITE THIS
        //TODO 
        if (index < this.freeList.size() - 1) {
            int pos = ((MemBlock) this.freeList.get(index)).getStartAddress() 
                    + ((MemBlock) this.freeList.get(index)).getSize();
            int nextPos = ((MemBlock) this.freeList.get(index + 1)).
                    getStartAddress();
            
            if (pos == nextPos) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param bottomIndex index to start at
     * @param topIndex index to stop at.
     * @param size size of new block
     */
    private void combine(int bottomIndex, int topIndex, int size) {
        int start = ((MemBlock) this.freeList.get(bottomIndex)).
                getStartAddress(); 
        @SuppressWarnings("unchecked")
        T newBlock = (T) new MemBlock(start, size, true);
        int i = bottomIndex;
        int count = 0;
        //remove all blocks that are adjacent and combinable
        while (count <= (topIndex - bottomIndex)) {
            this.freeList.remove(i);
            count++;
        }
        System.out.println("-----");
        this.freeList.add(newBlock);
    }
    
    /**
     * Get string form.
     * @return string form "[1, 2, 4]"
     */
    public String toString() {
        if (this.freeList == null || this.freeList.size() <= 1)  {
            return "[]";
        }
        String list = "[";
        for (int i = 1; i < this.freeList.size() - 1; i++) {
            list += ((MemBlock) this.freeList.get(i)).getStartAddress() + ", ";
        }
        list += ((MemBlock) this.freeList.get(this.freeList.size() - 1)).getStartAddress() + "]";
        return list;
        
    }
}


















































