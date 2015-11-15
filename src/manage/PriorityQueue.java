package manage;

import java.util.ArrayList;

/**
 * Manages, the storage and removal of T values.
 * Navjyoth Thakur - nthakur2 -  600.226.01 - P4
 * Jonathan Liu
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<? super T>> {

    /**
     * arraylist of free blocks.
     */
    private ArrayList<T> freeBlocks;
    
    /**
     * the index in the arraylist.
     */
    private int blockIndex;
    
    /**
     * Constructor to set up arraylist with empty position 0.
     */
    public PriorityQueue() {
        //represents index
        this.blockIndex = -1;
        this.freeBlocks = new ArrayList<T>();
    }
    
    /**
     * @return the size
     */
    public int size() {
        return this.blockIndex + 1;
    }
    
    /**
     * Add the value to the priority queue.
     * @param value the T to add
     * @return true if added, else false
     */
    public boolean add(T value) {
        if (value == null) {
            return false; 
        }
     
        //add to array list and increase block count
        this.freeBlocks.add(value);
        this.blockIndex++;
        int currVal = this.blockIndex;
        // until block is no longer greater than its parent and the block
        // is not the root
        while ((currVal > 0) && (this.freeBlocks.get(currVal).compareTo(
                this.freeBlocks.get(this.parent(currVal))) > 0)) {
            //set parent as current block, and the block as the parent
            T temp = this.freeBlocks.get(this.parent(currVal));
            this.freeBlocks.set(this.parent(currVal), this.freeBlocks.
                    get(currVal));
            this.freeBlocks.set(currVal, temp);
            currVal = this.parent(currVal);
        }

        return true;
    }
    
    /**
     * Return position for left child of pos.
     * @param pos int
     * @return the left child index.
     */
    private int leftChild(int pos) {
        if (this.blockIndex == 0) {
            return -1;
        }
        if (pos > (this.blockIndex - 1) / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }
     
    /**
     * Return position for right child of pos.
     * @param pos the index of current block
     * @return the index of right child
     */
    private int rightChild(int pos) {
        if (this.blockIndex == 1) {
            return -1;
        }
        if (pos > (this.blockIndex - 2) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }
    
    /**
     * Return position for parent.
     * @param pos position of child.
     * @return the parent position
     */
    private int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }
    
    /**
     * @return the block at root
     */
    public T removeMax() {
        //if empty, there is no value to remove
        if (this.blockIndex < 0)  {
            return null;
        }
        //swap last value with the root
        T temp = this.freeBlocks.get(0);
        this.freeBlocks.set(0, this.freeBlocks.get(this.blockIndex));
        //remove the last value
        this.freeBlocks.remove(this.blockIndex);
        this.blockIndex--;
        //as long as there is not only a root
        if (this.blockIndex > 0) {      // Not on last element
            this.swap(0);
        }
        return temp;
    }
    
    /**
     * @return the block at root
     */
    public T getMax() {
        //if empty, there is no value to remove
        if (this.blockIndex < 0)  {
            return null;
        }
        //return root value
        return this.freeBlocks.get(0);
    }
    
    
    
    /**
     * Swap with children until none of the children are greater than curr.
     * @param curr the current value to swap up
     */
    private void swap(int curr) {
        
        //if no children end swapping
        if ((this.rightChild(curr) < 0 && this.leftChild(curr) < 0)) {
            return;
        //if no left child swap with right child
        } else if (this.leftChild(curr) < 0) {
            //if right child is greater, swap with child
            if (this.freeBlocks.get(curr).
                compareTo(this.freeBlocks.get(this.rightChild(curr))) < 0) {
                
                T temp = this.freeBlocks.get(this.rightChild(curr));
                this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                    .get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.rightChild(curr);
                //call back swap with new curr
                this.swap(curr);
            }
        //if no right child, swap with left child
        } else if (this.rightChild(curr) < 0) {
          //if left child is greater, swap with child
            if (this.freeBlocks.get(curr).compareTo(this.freeBlocks.get(
                this.leftChild(curr))) < 0) {
                
                T temp = this.freeBlocks.get(this.leftChild(curr));
                this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.
                        get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.leftChild(curr);
                //call back swap with new curr
                this.swap(curr);
            }
        } else {   
            /*ROUNDABOUT WAY OF DOING IT, but it works about the same way*/
            int temp = curr;
            curr = this.swapLeft(curr);
            //if not changed from swapLeft
            if (temp == curr) {
                curr = this.swapRight(curr);
            } 
            //if changed from either swapLeft or swapRight, call back swap wih
            //new curr value
            if (temp != curr) {
                this.swap(curr);
            }
        }
    }
    
    /**
     * decide whether or not to swap with left child.
     * @param curr the current value to swap up
     * @return new position curr
     */
    private int swapLeft(int curr) {
        //checks if child is larger than parent
        if (this.freeBlocks.get(curr).compareTo(this.freeBlocks.get(
                this.leftChild(curr))) < 0) {
            //checks if left child greater than right child
            if (this.freeBlocks.get(this.leftChild(curr)).compareTo(this
                    .freeBlocks.get(this.rightChild(curr))) >= 0) {
                
                T temp = this.freeBlocks.get(this.leftChild(curr));
                this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.
                        get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.leftChild(curr);
            }
        }
        //returns new curr value
        return curr;
    }
    
    /**
     * Decide whether or not to swap with right child.
     * @param curr the current value to swap up
     * @return new position curr
     */
    private int swapRight(int curr) {
        //checks if child is larger than parent
        if (this.freeBlocks.get(curr).
                compareTo(this.freeBlocks.get(this.rightChild(curr))) < 0) {
            //checks if right child greater than left child
            if (this.freeBlocks.get(this.rightChild(curr)).compareTo(
                    this.freeBlocks.get(this.leftChild(curr))) > 0) {
                
                T temp = this.freeBlocks.get(this.rightChild(curr));
                this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                        .get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.rightChild(curr);
            }
        }
        //returns new curr value
        return curr;
    }
    
    /**
     * @return the if list is empty
     */
    public boolean isEmpty() {
        if (this.blockIndex == -1)  {
            return true;
        }
        return false;
    }
    
    /**
     * @param value object to check
     * @return if priority queue contains value
     */
    public boolean contains(T value) {
        //if empty list, return false
        if (this.blockIndex == -1)  {
            return false;
        }
        //goes through every position, returns true if found
        for (int i = 0; i <= this.blockIndex; i++) {
            if (this.freeBlocks.get(i).equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get string form. If MemBlock, creates string of starting address.
     * @return string form "[1, 2, 4]"
     */
    public String toString() {
        if (this.isEmpty())  {
            return "[]";
        }
        String list = "[";
        for (int i = 0; i < this.blockIndex; i++) {
            list += this.freeBlocks.get(i).toString() + ", ";
        }
        list += this.freeBlocks.get(this.blockIndex).toString() + "]";
        return list;
        
    }
    
    /**
     * Return arraylist to manage (mostly Defrag).
     * @return the ArrayListholding free blocks.
     */
    public ArrayList<T> getList() {
        return this.freeBlocks;
    }
}
