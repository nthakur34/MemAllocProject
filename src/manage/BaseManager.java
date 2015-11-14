package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author navthakur
 *
 */
public abstract class BaseManager implements MemoryManager {
        
    /**
     * ArrayList of allocated memory.
     */
    protected ArrayList<MemBlock> allocMem;
    
    /**
     * Size of whole memory.
     */
    protected int memSize;
    
    /**
     * Counter of number of defrags.
     */
    protected int defragCount;
    
    /**
     * Counter of number of failed allocations.
     */
    protected int failCount;
    
    /**
     * Total sizes of failed allocations.
     */
    protected int failSize;
    
    /**
     * Total alloc time (in nanoseconds).
     */
    protected long totalAllocTime;
    
    /**
     * Total size of all sorts.
     */
    protected double totalSortSize;
    
    /**
     * Total time for quickSorts.
     */
    protected long quickSortTime;
    
    /**
     * Total time for bucketSorts.
     */
    protected int bucketSortTime;
    
    /**
     * Check if previous allocation had a defrag.
     */
    private boolean prevDefrag;

    
    /**
     * Constructor.
     * @param inMemSize maximum size of whole memory
     */
    public BaseManager(int inMemSize) {
        // check if mem size is valid
        if (inMemSize < 1) {
            throw new IllegalArgumentException("Size must be greater than 0"); 
        }
        this.memSize = inMemSize;
        this.prevDefrag = false;
        this.defragCount = 0;
        this.failCount = 0;
        this.failSize = 0;
        // initialize allocMem
        // make 0 term null
        this.allocMem = new ArrayList<MemBlock>();
        this.allocMem.add(null);
        //this.allocMem.set(0, null);
        
    }
    
    /*
     * Alloc functions
     * (non-Javadoc)
     * @see manage.MemoryManager#alloc(int)
     */
    
    @Override
    public boolean checkPrevDefrag() {
        return this.prevDefrag;
    }
    
    /*
     * Alloc functions
     * (non-Javadoc)
     * @see manage.MemoryManager#alloc(int)
     */
    
    @Override
    public int alloc(int size, boolean hasDefragged) {
        // get time stuff
        long startTime = System.nanoTime();
        this.prevDefrag = hasDefragged;
        if (size <= 0) {
            this.failCount++;
            System.err.println("Size must be greater than 0.");
            this.allocMem.add(null);
            this.incAllocTime(startTime);
            return -1;
        }
        if (size > this.memSize) {
            this.failSize += size;
            this.failCount++;
            this.allocMem.add(null);
            this.incAllocTime(startTime);
            return -1;
        }
        // Grab mem block to be allocated
        // method of grabbing varies based on
        // alloc scheme
        MemBlock toAllocate = this.grabToAlloc(size);
        // check if failed allocation
        if (toAllocate == null) {
            // if has no been running after defrag
            if (!hasDefragged) {
                this.incAllocTime(startTime);
                this.defrag();
                return this.alloc(size, true);
            }
            // if has defragged and still no block need to count as fail
            this.failCount++;
            this.failSize += size;
            this.allocMem.add(null);
            this.incAllocTime(startTime);
            return -1;
        }
        // after grabbing block, allocate into new block
        // add request to allocMem array list
        MemBlock allocBlock = toAllocate.allocate(size);
        this.allocMem.add(allocBlock);
        // if toAllocate still has size, should be readded to free mem
        if (toAllocate.getSize() != 0) {
            this.addUnalloc(toAllocate);
        }
        
        this.incAllocTime(startTime);
        return allocBlock.getStartAddress();
    }
    
    private void incAllocTime(long startTime) {
        long timeDifference = System.nanoTime() - startTime;
        if ( timeDifference == 0) {
            System.err.println("Alloc messed up");
            System.exit(2);
        }
        this.totalAllocTime += timeDifference;
    }

    /**
     * Find the free block to be used to allocate.
     * @param size size of block to be allocated
     * @return the free memory block to use.
     *          Will return null if cannot find
     *          a fitting block
     */
    public abstract MemBlock grabToAlloc(int size);
    
    /**
     * Add back the unused block of memory into the free mem scheme.
     * @param unAlloc unallocated block of memory
     */
    public abstract void addUnalloc(MemBlock unAlloc);

    /*
     * Dealloc functions
     */
    
    @Override
    public MemBlock dealloc(int id) {
        // if id is within allocMem's size
        // and is >= 1
        if (this.allocMem.size() <= id || id < 1) {
            return null;
        }
        // will return a memblock at id if was successful alloc
        // will return null if failed
        MemBlock toReturn = this.allocMem.get(id);
        // set to null to indicate deallocated
        this.allocMem.set(id, null);
        if (toReturn != null) {
            toReturn.setFree(true);
            this.addUnalloc(toReturn);
        }
        return toReturn;
    }

    /*
     * Defrag functions
     */
    
    @Override
    public void defrag() {
        this.defragCount++;
        Collection<MemBlock> toSort = this.getCollection();
        this.totalSortSize += toSort.size();
        // initialize defragger
        Defrag defragger = new Defrag(toSort, this.memSize);
        long bucketTime = defragger.bucketSort();
        if (bucketTime == 0) {
            System.err.println("BucketSwagger");
            System.exit(2);
        }
        this.bucketSortTime += bucketTime;
        
        defragger = new Defrag(toSort, this.memSize);
        long quickTime = defragger.quickSort(); 
        if (quickTime == 0) {
            System.err.println("QuickSwagger");
            System.exit(2);
        }
        this.quickSortTime += quickTime;
                
        defragger.defragBlocks();
        this.rebuild(defragger.getCollection());
    }
    
    /**
     * Return collection (in whatever form) of free mem data.
     * @return Collection of MemBlocks
     */
    public abstract Collection<MemBlock> getCollection();
    
    /**
     * Rebuild the free memory block data structure.
     * @param blocks rebuild allocation scheme with new defragged blocks
     */
    public abstract void rebuild(ArrayList<MemBlock> blocks);
    
    @Override
    public int defragCount() {
        return this.defragCount;
    }

    @Override
    public int totalFails() {
        return this.failCount;
    }

    @Override
    public int avgFailSize() {
        if (this.failCount == 0) {
            return 0;
        }
        return this.failSize / this.failCount;
    }

    @Override
    public double avgTime() {
        return this.totalAllocTime / (1000 * ((double) this.allocMem.size() - 1 + this.defragCount));
    }

    @Override
    public double avgSortRatio(boolean isBucket) {
        double retVal;
        if (isBucket) {
            retVal = (double) this.bucketSortTime / 1000;
        } else {
            retVal = (double) this.quickSortTime / 1000;
        }
        return retVal / this.totalSortSize;
    }

}
