package manage;

import java.util.ArrayList;
import java.util.Collection;

public class WorstFitAlloc extends BaseManager {
    
   // private MemBlock usedBlocks[];  
    private  PriorityQueue<MemBlock> freeBlocks = new PriorityQueue<MemBlock>();

    public WorstFitAlloc(int inMemSize) {
        super(inMemSize);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean alloc(int size, boolean hasDefragged) {
        return super.alloc(size, false);
    }

    @Override
    public boolean dealloc(int id) {
        return super.dealloc(id);
    }
    
    @Override
    public MemBlock grabToAlloc(int size) {
        MemBlock temp = this.freeBlocks.getMax();
        if (temp.getSize() < size) {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void rebuild(ArrayList<MemBlock> blocks) {
        this.freeBlocks = new PriorityQueue<MemBlock>();
        for (int i = 0; i < blocks.size(); i++) {
            this.freeBlocks.add(blocks.get(i));
        }
    }
}
