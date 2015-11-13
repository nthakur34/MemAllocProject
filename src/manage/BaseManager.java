package manage;

import java.util.Collection;

public class BaseManager implements MemoryManager {

    
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
