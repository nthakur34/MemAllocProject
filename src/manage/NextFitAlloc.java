package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Next fit allocation scheme. Uses queue data structure.
 * @author Nitin
 *
 */
public class NextFitAlloc extends BaseManager {

    /**
     * Queue used to handle free mem blocks.
     */
    private Queue<MemBlock> queue;
    
    /**
     * Constructor for class, uses superclass constructor as well.
     * @param inMemSize inputted memory size
     */
    public NextFitAlloc(int inMemSize) {
        super(inMemSize);
        this.queue = new QueueSeq<MemBlock>();
        this.queue.enqueue(new MemBlock(0, inMemSize, true));
    }

    @Override
    public MemBlock grabToAlloc(int size) {
        MemBlock toAlloc;
        // while haven't iterated through queue once
        for (int i = 0; i < this.queue.size(); i++) {
            // get top of queue
            toAlloc = this.queue.dequeue();
            // check if can afford alloc
            if (toAlloc.getSize() >= size) {
                return toAlloc;
            }
            // otherwise put in back of queue
            this.queue.enqueue(toAlloc);
        }
        return null;
    }

    @Override
    public void addUnalloc(MemBlock unAlloc) {
        this.queue.enqueue(unAlloc);
    }

    @Override
    public Collection<MemBlock> getCollection() {
        return this.queue.getArrayList();
    }

    @Override
    public void rebuild(ArrayList<MemBlock> blocks) {
        for (int i = 0; i < blocks.size(); i++) {
            this.queue.enqueue(blocks.remove(blocks.size() - 1));
        }
    }

}
