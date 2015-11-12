package manage;

import java.util.Comparator;

/**
 * MemBlock is a class used to represent a block of memory.
 * @author Nitin
 *
 */
public class MemBlock implements Comparable<MemBlock> {
    
    /**
     * The start address of the memblock.
     */
    private int startAddress;
    
    /**
     * The size of the memblock.
     */
    private int size;
    
    /**
     * Boolean variable to see if free. Mainly usable in tests.
     */
    private boolean isFree;
    
    
    /**
     * Constructor for memblock.
     * @param beginAddress start address for the memblock
     * @param allocSize size of the allocation
     * @param free whether or not the block is free
     */
    public MemBlock(int beginAddress, int allocSize, boolean free) {
        this.size = allocSize;
        this.startAddress = beginAddress;
        this.isFree = free;
    }
    
    /**
     * getRightAdjacent() returns the address next to the end of it
     * to know which block it can combine with.
     * @return the next memaddress after the end of the block
     */
    public int getRightAdjacent() {
        return this.startAddress + this.size;
    }
    
    /**
     * combineData() combines the 2 blocks of memory.
     * @param block block of memory being combined with this one.
     */
    public void combineData(MemBlock block) {
        // check if bad block
        if (this.getRightAdjacent() != block.getStartAddress()) {
            System.err.println("Blocks not adjacent");
            return;
        } else if (!this.isFree || !block.isFree()) {
            System.err.println("Block not free");
            return;
        }
        // if good, increase this size
        this.size += block.getSize();
        // that's basically it
    }
    
    /**
     * Make an allocation from current memblock to another.
     * @param newSize the size of the new block being allocated
     * @return the new allocated memblock
     */
    public MemBlock allocate(int newSize) {
        // check if new size is bigger than prev size
        if (newSize > this.size) {
            System.err.println("New size too big.");
            return null;
        } else if (newSize <= 0) {
            System.err.println("New size too small.");
            return null;
        }
        // construct new block to be returned 
        MemBlock newBlock = new MemBlock(this.startAddress,
                newSize, false);
        // fix the memblock fields based on inputs
        this.startAddress += newSize;
        this.size -= newSize;
        return newBlock;
    }
    
    /**
     * Getter for the start address of block.
     * @return the start address of block
     */
    public int getStartAddress() {
        return this.startAddress;
    }
    
    /**
     * Getter for the size of block.
     * @return the size of block
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Check if memblock is free.
     * @return if block is free
     */
    public boolean isFree() {
        return this.isFree;
    }

    /**
     * Set if block is free or not.
     * @param newFree whether the block is free or not
     */
    public void setFree(boolean newFree) {
        this.isFree = newFree;
    }

    @Override
    public int compareTo(MemBlock o) {
        if (this.getSize() < o.getSize()) {
            return -1;
        } else if (this.getSize() > o.getSize()) {
            return 1;
        } 
        return 0;
    }
    
    /**
     * Comparator to compare addresses of memblocks.
     * @author Nitin
     *
     */
    public static class MemBlockComparator implements Comparator<MemBlock> {

        /**
         * Address compare.
         */
        @Override
        public int compare(MemBlock o1, MemBlock o2) {
            return o1.getStartAddress() - o2.getStartAddress();
        } 
    }
    
}
