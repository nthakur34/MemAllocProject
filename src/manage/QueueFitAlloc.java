package manage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of code for next fit (i.e. queue fit).
 * @author Nitin
 *
 */
public class QueueFitAlloc implements MemoryManager {

    /**
     * Overall size of the memory (free and allocated).
     */
    //private int size;
    
    /**
     * Allocated memory, indexed by allocation id.
     */
    private ArrayList<MemBlock> allocMem;
    
    @Override
    public boolean alloc(int size) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dealloc(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<MemBlock> bucketDefrag() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<MemBlock> quickDefrag() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void rebuild(Collection<MemBlock> blocks) {
        // TODO Auto-generated method stub

    }

}
