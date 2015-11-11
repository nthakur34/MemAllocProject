package manage;

import java.util.ArrayList;

/**
 * @author navthakur
 *
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<? super T>> {

    /**
     * array of free blocks.
     */
    private ArrayList<T> freeBlocks;
    
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
    //@SuppressWarnings("unchecked")
    public PriorityQueue() {
        final int sizeToSet = 5;
        this.size = sizeToSet;
        this.numBlocks = 0;
        this.freeBlocks = new ArrayList<T>();
        this.freeBlocks.add(null);
                //(T[]) new Object[this.size];
    }
    
    /**
     * @return the size
     */
    public int size() {
        return this.numBlocks;
    }
    
    /**
     * Resize the array.
     */
   // @SuppressWarnings("unchecked")
    public void resize() {
        ArrayList<T> tempArray = new ArrayList<T>();
                //(T[]) new Object[this.size * 2];
        for (int i = 1; i < this.size; i++) {
            tempArray.add(this.freeBlocks.get(i));
        }
        this.freeBlocks = tempArray;
    }
    
    /**
     * @param value the T to add
     * @return ture if added, else false
     */
    public boolean add(T value) {
      //  if (this.numBlocks > this.size) {
        //    this.resize(); //call function to resize
        //}
        this.freeBlocks.add(value);//[this.numBlocks] = value;
        this.numBlocks++;
        int currVal = this.numBlocks;
        //System.out.println(this.freeBlocks.toString());
        //System.out.println("----" + this.freeBlocks.size());
       // System.out.println(currVal + "----" + this.freeBlocks.get(currVal) +
         //       "------" + this.freeBlocks.get(this.parent(currVal)));
        while ((currVal > 1) && (this.freeBlocks.get(currVal).compareTo(
                this.freeBlocks.get(this.parent(currVal))) > 0)) {
           // System.out.println("----" + this.freeBlocks.size());
            T temp = this.freeBlocks.get(this.parent(currVal));
            this.freeBlocks.set(this.parent(currVal), this.freeBlocks.get(currVal));
            this.freeBlocks.set(currVal, temp);
            currVal = this.parent(currVal);
            if (currVal == 1) {
                return true;
            }
            //System.out.println(currVal);
           // System.out.println("----" + this.freeBlocks.size());
            //for (int i = 0; i < this.freeBlocks.size(); i++) {
              //  System.out.println("****" + this.freeBlocks.get(i));
            //}
        }
    //this.balance();
        return true;
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
        T temp = this.freeBlocks.get(1);
        this.freeBlocks.set(1, this.freeBlocks.get(this.numBlocks));
        this.freeBlocks.remove(this.numBlocks);
        this.numBlocks--;
        
        if (this.numBlocks != 1) {      // Not on last element
            this.swap(1);
        }
        return temp;
    }
    
    /**
     * @param curr the current value to swap up
     */
    public void swap(int curr) {
        
        if ((this.leftChild(curr) == 0 && this.rightChild(curr) == 0)) {
            return;
        } else if (this.leftChild(curr) == 0) {
            if (this.freeBlocks.get(curr).
                compareTo(this.freeBlocks.get(this.rightChild(curr))) < 0) {
            T temp = this.freeBlocks.get(this.rightChild(curr));
            this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                    .get(curr));
            this.freeBlocks.set(curr, temp);
            curr = this.rightChild(curr);
            swap(curr);
            }
        } else if (this.rightChild(curr) == 0) {
            if(this.freeBlocks.get(curr).compareTo(this.freeBlocks.get(
                this.leftChild(curr))) < 0) {
            T temp = this.freeBlocks.get(this.leftChild(curr));
            this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.get
                    (curr));
            this.freeBlocks.set(curr, temp);
            curr = this.leftChild(curr);
            swap(curr);
            }
        } else {
            int temp = curr;
            curr = swapLeft(curr);
            if (temp == curr) {
                curr = swapRight(curr);
            } 
            if (temp != curr) {
                swap(curr);
            }
        }
        /*
        while (this.freeBlocks.get(curr).compareTo(this.freeBlocks.get(
                this.leftChild(curr))) < 0 || this.freeBlocks.get(curr).
                compareTo(this.freeBlocks.get(this.rightChild(curr))) < 0) {
            
          /*  if (this.freeBlocks.get(this.leftChild(curr)).compareTo(this
                    .freeBlocks.get(this.rightChild(curr))) > 0) {
                T temp = this.freeBlocks.get(this.leftChild(curr));
                this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.get
                        (curr));
                this.freeBlocks.set(curr, temp);
                curr = this.leftChild(curr);
            } else if (this.freeBlocks.get(this.rightChild(curr)).compareTo(
                    this.freeBlocks.get(this.leftChild(curr))) >= 0) {
                T temp = this.freeBlocks.get(this.rightChild(curr));
                this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                        .get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.rightChild(curr);
            }
            if (this.leftChild(curr) == 0) {
                T temp = this.freeBlocks.get(this.rightChild(curr));
                this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                        .get(curr));
                this.freeBlocks.set(curr, temp);
                curr = this.rightChild(curr);
            } else if (this.rightChild(curr) == 0) {
                T temp = this.freeBlocks.get(this.leftChild(curr));
                this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.get
                        (curr));
                this.freeBlocks.set(curr, temp);
                curr = this.leftChild(curr);
            } else {
                int temp = curr;
                curr = swapLeft(curr);
                if (temp != curr) {
                    curr = swapRight(curr);
                }
            }
        } */
    }
    
    /**
     * @param curr the current value to swap up
     */
    private int swapLeft(int curr) {

        if(this.freeBlocks.get(curr).compareTo(this.freeBlocks.get(
                this.leftChild(curr))) < 0) {
         //   System.out.println(this.freeBlocks.get(this.leftChild(curr)) + "   &&&& " + this.freeBlocks.get(this.rightChild(curr)));
        if (this.freeBlocks.get(this.leftChild(curr)).compareTo(this
                .freeBlocks.get(this.rightChild(curr))) > 0) {
            T temp = this.freeBlocks.get(this.leftChild(curr));
            this.freeBlocks.set(this.leftChild(curr), this.freeBlocks.get
                    (curr));
            this.freeBlocks.set(curr, temp);
            curr = this.leftChild(curr);
        }
        }
        return curr;
    }
    
    /**
     * @param curr the current value to swap up
     */
    private int swapRight(int curr) {

        if (this.freeBlocks.get(curr).
                compareTo(this.freeBlocks.get(this.rightChild(curr))) < 0) {
        if (this.freeBlocks.get(this.rightChild(curr)).compareTo(
                this.freeBlocks.get(this.leftChild(curr))) >= 0) {
            T temp = this.freeBlocks.get(this.rightChild(curr));
            this.freeBlocks.set(this.rightChild(curr), this.freeBlocks
                    .get(curr));
            this.freeBlocks.set(curr, temp);
            curr = this.rightChild(curr);
        }
        }
        return curr;
    }
    
    /**
     * @return the if list is empty
     */
    public boolean isEmpty() {
        if (this.numBlocks == 0)  {
            return true;
        }
        return false;
    }
    
    /**
     * @return if priority queue contains value
     */
    public boolean contains(T value) {
        if (this.numBlocks == 0)  {
            return false;
        }
        boolean contains = false;
        for (int i = 1; i <= this.numBlocks; i++) {
            if (this.freeBlocks.get(i) == value) {
                contains = true;
            }
        }
        return contains;
    }
    
    /**
     * @return string form "[1, 2, 4]"
     */
    public String toString() {
        if (this.isEmpty())  {
            return "[]";
        }
        String list = "[";
        for (int i = 1; i < this.numBlocks; i++) {
            list += this.freeBlocks.get(i) + ", ";
        }
        list += this.freeBlocks.get(this.numBlocks) + "]";
        return list;
        
    }
    
}
