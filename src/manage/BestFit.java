package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of the best fit allocation scheme, using AVLTree.
 *
 */
public class BestFit extends BaseManager {
    
    /** AVLTree used for finding the best fitting free block. */
    private AVLtree<MemBlock> tree;

    /**
     * Constructor for the BestFit class.
     * @param inMemSize the size of memory
     */
    public BestFit(int inMemSize) {
        super(inMemSize);
        
        // TODO Auto-generated constructor stub
        
    }
    
    /**
     * Find the free block to be used to allocate.
     * @param size the size of the block to be allocated
     * @return the free memory block to use.
     *          Will return null if cannot find
     *          a fitting block
     */
    public MemBlock grabToAlloc(int size) {
        MemBlock temp = new MemBlock(0, size, true);
        return tree.getBestFit(temp);
    }
    
    /**
     * Add back the unused block of memory into the free mem scheme.
     * @param unAlloc unallocated block of memory
     */
    public void addUnalloc(MemBlock unAlloc) {
        
    }
    
    public Collection<MemBlock> getCollection();
    
    public void rebuild(ArrayList<MemBlock> blocks) {
        
    }
    
    
    
    
    
}
