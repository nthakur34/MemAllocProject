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
        this.allocMem = new ArrayList<MemBlock>(1);
        this.allocMem.set(0, null);
        
    }
    
    /*
     * Alloc functions
     * (non-Javadoc)
     * @see manage.MemoryManager#alloc(int)
     */
    
    @Override
    public boolean alloc(int size) {
        // Grab mem block to be allocated
        // method of grabbing varies based on
        // alloc scheme
        MemBlock toAllocate = this.grabToAlloc(size);
        // check if failed allocation
        if (toAllocate == null) {
            // if so, defrag
        }
        // after grabbing block, allocate into new block
        // add request to allocMem array list
        this.allocMem.add(toAllocate.allocate(size));
        // if toAllocate still has size, should be readded to free mem
        if (toAllocate.getSize() != 0) {
            this.addUnalloc(toAllocate);
        }
        return true;
    }
    
    /**
     * Find the free block to be used to allocate.
     * @param size
     * @return the free memory block to use.
     *          Will return null if cannot find
     *          a fitting block
     */
    protected abstract MemBlock grabToAlloc(int size);
    
    /**
     * Add back the unused block of memory into the free mem scheme.
     * @param unAlloc unallocated block of memory
     */
    protected abstract void addUnalloc(MemBlock unAlloc);

    @Override
    public boolean dealloc(int id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public MemBlock deallocHelper(int id) {
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
