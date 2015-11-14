package manage;

import java.util.Collection;

public class WorstFitAlloc extends BaseManager {
    
   // private MemBlock usedBlocks[];  

    private  PriorityQueue<MemBlock> freeBlocks;
    
    //private int size;
    
    private Defrag sortedBlocks;

 /*   public WorstFitAlloc(int memSize) {
        
        this.size = memSize;
        this.usedBlocks = new MemBlock[memSize];
        this.freeBlocks = new PriorityQueue<MemBlock>(); 
        this.freeBlocks.add(new MemBlock(0, memSize, true));
    }
*/
    public WorstFitAlloc(int inMemSize) {
        super(inMemSize);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean alloc(int size) {
        return super.alloc(size);
     /*   if (size > this.size) {
            return false;
        }
      /*  MemBlock temp = this.freeBlocks.getMax();
        if (temp == null) {
            return false;
        }
        if (temp.getSize() < size) {
            sortedBlocks = new Defrag(this.freeBlocks.getList(), size);
            
        }*/
    }

    @Override
    public boolean dealloc(int id) {
        return super.dealloc(id);
        //return false;
    }

 /*   @Override
    public Collection<MemBlock> bucketDefrag() {
    /   sortedBlocks = new Defrag(this.freeBlocks.getList(), size);
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
*/
    @Override
    public void rebuild(Collection<MemBlock> blocks) {
        super.rebuild(blocks);
        
    }

    @Override
    protected MemBlock grabToAlloc(int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void addUnalloc(MemBlock unAlloc) {
        // TODO Auto-generated method stub
        
    }
}
