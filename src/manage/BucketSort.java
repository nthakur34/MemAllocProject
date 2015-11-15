package manage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Generic sorter through bucket implementation.
 * @author Nitin
 *
 * @param <T> Type of data being sorted
 */
public class BucketSort<T extends Comparable<T>> {
   
    /**
     * 10 is the max length of the linked list, and the array
     * will be separated into tens.
     */
    private static final int TEN = 10;
   
    /**
     * Length of the list based on the inputted max size.
     */
    private int listLen;    
    
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
        if (size / TEN < TEN) {
            this.listLen = size / TEN;
        } else {
            this.listLen = TEN;
        }
        // create data array with inputted size
        this.data = (T[]) new Comparable[size];
    }
    
    /**
     * Sort data into HashMap.
     * 
     * Exceptions to throw:
     *      blocks is messed up/null
     *      bad comparator?
     * 
     * @param blocks the collection of info to be sorted
     * @param comparator comparator for type of data being sorted
     * @return array of all values sorted
     */
    public void sort(Collection<T> blocks,
            Comparator<? super T> comparator) {

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
