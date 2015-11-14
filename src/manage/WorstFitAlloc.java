package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author navthakur
 *worst fit allocation for priority queue
 */
public class WorstFitAlloc extends BaseManager {
    
   // priority queue to manage
    /**
     * 
     */
    private  PriorityQueue<MemBlock> freeBlocks = new PriorityQueue<MemBlock>();

    //constructor from superclass
    /**
     * Constructor to set priority queue.
     * @param inMemSize The amount of memeory we handle.
     */
    public WorstFitAlloc(int inMemSize) {
        super(inMemSize);
        //initialize free block of given size
        this.freeBlocks.add(new MemBlock(0, inMemSize, true));
    }
    
    @Override
    public int alloc(int size, boolean hasDefragged) {
        return super.alloc(size, hasDefragged);
    }

    @Override
    public MemBlock dealloc(int id) {
        return super.dealloc(id);
    }
    
    @Override
    public MemBlock grabToAlloc(int size) {
        MemBlock temp = this.freeBlocks.getMax();
        //if the max is not large enough, return null,
        //else remove the block
        if (temp == null) {
            return null;
        } else if (temp.getSize() < size) {
            return null;
        } else {
            return this.freeBlocks.removeMax();
        }
    }

    @Override
    public void addUnalloc(MemBlock unAlloc) {
        this.freeBlocks.add(unAlloc);
        
    }

    @Override
    public Collection<MemBlock> getCollection() {
        return this.freeBlocks.getList();
    }

    @Override
    public void rebuild(ArrayList<MemBlock> blocks) {
        this.freeBlocks = new PriorityQueue<MemBlock>();
        for (int i = 0; i < blocks.size(); i++) {
            this.freeBlocks.add(blocks.get(i));
        }
    }
}
