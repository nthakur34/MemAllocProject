package manage;

import java.util.Collection;

/**
 * General interface that implements different methods of managing data
 * to handle free memory.
 * @author Nitin
 *
 */
public interface AllocScheme {

    /**
     * Function for allocating a free block of memory from fit model
     * to ArrayList of alloc ids.
     * @param size size of memory block to be allocated
     * @return whether alloc could happen or not
     */
    boolean alloc(int size);
    
    /**
     * Deallocate a block of allocated memory in allocated array list
     * based on id of allocation.
     * @param id id of allocation request   
     * @return whether request still had an allocated piece of memory
     *          attached to it.
     */
    boolean dealloc(int id);
    
    /**
     * Defragment the data with a bucket defrag method.
     * @return collection of defragmented memblocks
     */
    Collection<MemBlock> bucketDefrag();
    
    /**
     * Defragment the data with a quicksort method.
     * @return collection of defragmented memblocks
     */
    Collection<MemBlock> quickDefrag();
    
    /**
     * Rebuild the free memory scheme after a defrag.
     * @param blocks the newly defragmented free blocks of memory 
     */
    void rebuild(Collection<MemBlock> blocks);
    
}
