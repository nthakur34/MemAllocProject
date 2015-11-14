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
     * @param hasDefragged whether while running this defrag has happened or not
     * @return whether alloc could happen or not
     */
    boolean alloc(int size, boolean hasDefragged);
    
    /**
     * Deallocate a block of allocated memory in allocated array list
     * based on id of allocation.
     * @param id id of allocation request   
     * @return whether request still had an allocated piece of memory
     *          attached to it.
     */
    boolean dealloc(int id);
    
    /**
     * Dealloc covers the ArrayList allocMem's part of the dealloc.
     * @param id id of the block being deallocated
     * @return the memblock associated with the allocation
     *          will return null if request is out of bounds
     *          or id leads to failed request/request already removed
     */
    MemBlock deallocHelper(int id);
    
    /**
     * Defragment the data with a given defrag method. Will change data.
     * @param toSort the collection to be sorted
     * @param isBucket whether BucketSort or QuickSort
     */
    void defrag(boolean isBucket);
    
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
