package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * General interface that implements different methods of managing data
 * to handle free memory.
 *
 */
public interface MemoryManager {

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
     * Defragment the data with a given defrag method.
     * @param toSort the collection to be sorted
     * @param isBucket whether BucketSort or QuickSort
     * @return collection of defragmented memblocks
     */
    Collection<MemBlock> defrag(ArrayList<MemBlock> toSort, boolean isBucket);
    
    /**
     * Rebuild the free memory scheme after a defrag.
     * @param blocks the newly defragmented free blocks of memory 
     */
    void rebuild(Collection<MemBlock> blocks);
    
    /**
     * Get number of defrags during session.
     * @return number of defrags
     */
    int defragCount();
    
    /**
     * Get number of failed allocations during session.
     * @return number of failed allocations
     */
    int totalFails();
    
    /**
     * Get average size of failed allocations.
     * @return average failed allocation size
     */
    int avgFailSize();
    
    /**
     * Get average time to process allocation.
     * @return average time to process allocation
     */
    int avgTime();
    
    /**
     * Get average time/size for a sort.
     * @param isBucket whether BucketSort or QuickSort
     * @return average failed allocation size
     */
    int avgSortRatio(boolean isBucket);
    
}
