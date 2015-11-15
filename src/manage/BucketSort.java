package manage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Generic sorter through bucket implementation.
 * @author Nitin
 *
 * @param <T> Type of data being sorted
 */
public class BucketSort<T> {
    
    /**
     * Bucket array representation of the data.
     */
    private T[] data;
   
    
    /**
     * Constructor for defrag.
     * @param size max size of elements coming in
     */
    @SuppressWarnings("unchecked")
    public BucketSort(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0"); 
        }
        // create data array with inputted size
        this.data = (T[]) new Object[size];
    }
    
    /**
     * Sort data into array.
     * 
     * @param blocks the collection of info to be sorted
     */
    public void sort(Collection<T> blocks) {

        // get an iterator to go through all values
        Iterator<T> blockIter = blocks.iterator();
        while (blockIter.hasNext()) {
            // grab next value
            T toInsert = blockIter.next();
            // use hash code to find out
            // which bucket it should be placed in
            int index = toInsert.hashCode();
            this.data[index] = toInsert;
        }
    }
    
    /**
     * Return sorted data function.
     * @return sorted data
     */
    public ArrayList<T> getData() {
        ArrayList<T> output = new ArrayList<T>();
        // need to convert data to array list
        for (int i = 0; i < this.data.length; i++) {
            // check if there is a linked list in this index
            if (this.data[i] != null) {
                // if so, go through linked list and move all data to arraylist
                output.add(this.data[i]);
            }
        }
        return output;
    }

}
