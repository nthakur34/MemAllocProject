package manage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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
     * Bucket array representation of the data.
     */
    private LinkedList<T>[] data;
   
    
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
        this.data = (LinkedList<T>[]) new LinkedList[(size / TEN) + 1];
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
    public ArrayList<T> sort(Collection<T> blocks,
            Comparator<? super T> comparator) {

        // get an iterator to go through all values
        Iterator<T> blockIter = blocks.iterator();
        while (blockIter.hasNext()) {
            // grab next value
            T toInsert = blockIter.next();
            // use hash code to find out
            // which bucket it should be placed in
            int index = toInsert.hashCode();
            // find linked list index in main array
            int tens = index / TEN;
            // check if no list there
            if (this.data[tens] == null) {
                // then make one
                this.data[tens] = new LinkedList<T>();
            }
            // does contain index
            // get the linked list at the bucket
            LinkedList<T> currList = this.data[tens];
            // make an iterator for the list
            ListIterator<T> listIterator = currList.listIterator();
            // find where either the next no-element position is
            // or where 
            // make comparator to compare memblock addresses
            while (listIterator.hasNext()) {
                if (comparator.compare(listIterator.next(), toInsert) > 0) {
                    // need to go back one with listiterator
                    listIterator.previous();
                    break;
                }
            }
            // add when either end condition is reached
            listIterator.add(toInsert);
        }
        
        ArrayList<T> output = new ArrayList<T>();
        // need to convert data to array list
        for (int i = 0; i < this.data.length; i++) {
            // check if there is a linked list in this index
            if (this.data[i] != null) {
                // if so, go through linked list and move all data to arraylist
                output.addAll(this.data[i]);
            }
        }
        return output;
    }

}
