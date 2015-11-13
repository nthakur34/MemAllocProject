package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author navthakur
 *
 */
public class BaseManager implements MemoryManager {
    
    /**
     * ArrayList of allocated memory.
     */
    ArrayList<MemBlock> allocMem;
    
    /**
     * Size of whole memory.
     */
    private int memSize;
    
    /**
     * Counter of number of defrags.
     */
    private int defragCount;
    
    /**
     * Counter of number of failed allocations.
     */
    private int failCount;
    
    /**
     * Total sizes of failed allocations.
     */
    private int failSize;
    
    /**
     * Constructor.
     * @param inMemSize maximum size of whole memory
     */
    public BaseManager(int inMemSize) {
        this.memSize = inMemSize;
        this.defragCount = 0;
        this.failCount = 0;
        this.failSize = 0;
        // initialize allocMem
        // make 0 term null
        this.allocMem = new ArrayList<MemBlock>(2);
        
    }
    
    @Override
    public boolean alloc(int size) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dealloc(int id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * Dealloc covers the ArrayList allocMem's part of the dealloc.
     * @param id id of the block being deallocated
     * @return the memblock associated with the allocation
     *          will return null if request is out of bounds
     *          or id leads to failed request/request already removed
     */
    private MemBlock deallocHelper(int id) {
        // if id is within allocMem's size
        // and is >= 1
        if (this.allocMem.size() > id
                && id >= 1) {
            // will return a memblock at id if was successful alloc
            // will return null if failed
            MemBlock toReturn = this.allocMem.get(id);
            // set to null to indicate deallocated
            this.allocMem.set(id, null);
            return toReturn;
        }
        // if out of bounds, return null
        return null;
    }

    @Override
    public Collection<MemBlock> defrag(ArrayList<MemBlock> toSort, 
            boolean isBucket) {
        // increment defragCount
        this.defragCount++;
        // initialize defragger
        Defrag defragger = new Defrag(toSort, this.memSize);
        if (isBucket) {
            // if bucket defrag
            defragger.bucketSort();
        } else {
            // otherwise is quicksort defrag
            defragger.quickSort();            
        }
        return defragger.getCollection();
    }

    @Override
    public void rebuild(Collection<MemBlock> blocks) {
        // TODO Auto-generated method stub

    }

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
        return this.failSize / this.failCount;
    }

    @Override
    public int avgTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int avgSortRatio(boolean isBucket) {
        // TODO Auto-generated method stub
        return 0;
    }

}
