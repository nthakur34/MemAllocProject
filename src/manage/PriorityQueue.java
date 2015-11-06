package manage;

/**
 * @author navthakur
 *
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<? super T>> {

    /**
     * array of free blocks.
     */
    private T[] freeBlocks;
    
    /**
     * current size of array.
     */
    private int size;
    
    /**
     * 
     */
    private int numBlocks;
    
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        final int sizeToSet = 5;
        this.size = sizeToSet;
        this.numBlocks = 1;
        this.freeBlocks = (T[]) new Object[this.size];
    }
    
    /**
     * @return the size
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Resize the array.
     */
    @SuppressWarnings("unchecked")
    public void resize() {
        T[] tempArray = (T[]) new Object[this.size * 2];
        for (int i = 1; i < this.size; i++) {
            tempArray[i] = this.freeBlocks[i];
        }
        this.freeBlocks = tempArray;
    }
    
    /**
     * @param value the T to add
     * @return ture if added, else false
     */
    public boolean add(T value) {
        if (this.numBlocks > this.size) {
            this.resize(); //call function to resize
        }
        this.freeBlocks[this.numBlocks] = value;

        int currVal = this.numBlocks;
        while ((currVal != 0) && (this.freeBlocks[currVal].compareTo(
                this.freeBlocks[this.parent(currVal)]) > 0)) {
            T temp = this.freeBlocks[this.parent(currVal)];
            this.freeBlocks[this.parent(currVal)] = this.freeBlocks[currVal];
            this.freeBlocks[currVal] = temp;
            currVal = this.parent(currVal);
        }
    //this.balance();
        this.numBlocks++;
        return false;
    }
    
    /**
     * Return position for left child of pos.
     * @param pos int
     * @return the left child index.
     */
    public int leftChild(int pos) {
        if (pos > this.numBlocks / 2) {
            return 0;
        }
        return 2 * pos;
    }
     
    /**
     * Return position for right child of pos.
     * @param pos the index of current block
     * @return the index of right child
     */
    public int rightChild(int pos) {
        if (pos > (this.numBlocks - 1) / 2) {
            return 0;
        }
        return 2 * pos + 1;
    }
    
    /**
     * Return position for parent.
     * @param pos position of child.
     * @return the parent position
     */
    public int parent(int pos) {
        if (pos <= 1) {
            return 0;
        }
        return (pos) / 2;
    }
    
    /**
     * @return the block
     */
    public T removeMax() {
        if (this.numBlocks == 0)  {
            return null;
        }
        T temp = this.freeBlocks[1];
        this.freeBlocks[1] = this.freeBlocks[this.numBlocks - 1];
        this.freeBlocks[this.numBlocks - 1] = null;
    
        if (this.numBlocks != 1) {      // Not on last element
            this.swap(1);
        }
        return temp;
    }
    
    /**
     * @param curr the current value to swap up
     */
    public void swap(int curr) {
        
        while (this.freeBlocks[curr].compareTo(this.freeBlocks
                [this.leftChild(curr)]) > 0 && this.freeBlocks[curr].
                compareTo(this.freeBlocks[this.rightChild(curr)]) > 0) {
            
            if (this.freeBlocks[this.leftChild(curr)].compareTo(this.freeBlocks
                [this.rightChild(curr)]) > 0) {
                T temp = this.freeBlocks[this.leftChild(curr)];
                this.freeBlocks[this.leftChild(curr)] = this.freeBlocks[curr];
                this.freeBlocks[curr] = temp;
                curr = this.leftChild(curr);
            } else if (this.freeBlocks[this.rightChild(curr)].compareTo(this.
                    freeBlocks[this.leftChild(curr)]) >= 0) {
                T temp = this.freeBlocks[this.rightChild(curr)];
                this.freeBlocks[this.rightChild(curr)] = this.freeBlocks[curr];
                this.freeBlocks[curr] = temp;
                curr = this.rightChild(curr);
            }
        }
    }
        
    /**
     * 
     *
    public void balance() {
        for(int i = 0; i < this.numBlocks; i++) {
            int temp = 0;
        }
    }*/
    
}
