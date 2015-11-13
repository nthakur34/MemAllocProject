package manage;

import java.util.Collection;

public class WorstFitAlloc implements MemoryManager {
    
    private MemBlock usedBlocks[];  
    
    private  PriorityQueue<MemBlock> freeBlocks;
    
    private int size;
    
    private Defrag sortedBlocks;

    public WorstFitAlloc(int memSize) {
        
        this.size = memSize;
        this.usedBlocks = new MemBlock[memSize];
        this.freeBlocks = new PriorityQueue<MemBlock>(); 
        this.freeBlocks.add(new MemBlock(0, memSize, true));
    }

    @Override
    public boolean alloc(int size) {
        if (size > this.size) {
            return false;
        }
        MemBlock temp = this.freeBlocks.removeMax();
        if (temp == null) {
            return false;
        }
        if (temp.getSize() < size) {
            sortedBlocks = new Defrag(this.freeBlocks.getList(), size);
            
        }
        
        return false;
    }

    @Override
    public boolean dealloc(int id) {
        
        return false;
    }

    @Override
    public Collection<MemBlock> bucketDefrag() {
        sortedBlocks = new Defrag(this.freeBlocks.getList(), size);
        sortedBlocks.bucketSort();
        //sorted.
        return null;
    }

    @Override
    public Collection<MemBlock> quickDefrag() {
        sortedBlocks = new Defrag(this.freeBlocks.getList(), size);
        sortedBlocks.quickSort();
        return null;
    }

    @Override
    public void rebuild(Collection<MemBlock> blocks) {
        // TODO Auto-generated method stub
        
    }
}
