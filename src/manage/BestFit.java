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
        this.tree = new AVLtree<MemBlock>();
        MemBlock temp = new MemBlock(0, inMemSize, true);
        this.tree.add(temp);
    }
    
    /**
     * Find the free block to be used to allocate, deletes it from
     * the tree, and returns it.
     * @param size the size of the block to be allocated
     * @return the free memory block to use.
     *          Will return null if cannot find
     *          a fitting block
     */
    public MemBlock grabToAlloc(int size) {
        MemBlock temp = new MemBlock(0, size, true);
        MemBlock bf = this.tree.getBestFit(temp);
        this.tree.remove(bf);
        return bf;
    }
    
    /**
     * Add back the unused block of memory into the free mem scheme.
     * @param unAlloc unallocated block of memory
     */
    public void addUnalloc(MemBlock unAlloc) {
        this.tree.add(unAlloc);
        //oi
    }
    
    /**
     * Returns a collection of the memBlocks in the tree in inOrder.
     * @return the collection
     */
    public Collection<MemBlock> getCollection() {
        return (Collection<MemBlock>) this.tree.preOrder();
    }
    
    /**
     * Takes in an arraylist of memblocks and completely rebuilds
     * the tree from it.  This is used after defragging.
     * @param blocks an arraylist of memblocks
     */
    public void rebuild(ArrayList<MemBlock> blocks) {
        this.tree = new AVLtree<MemBlock>();
        for (int i = 0; i < blocks.size(); i++) {
            this.tree.add(blocks.get(i));
        }
    }
    
    
    
    
    
}
