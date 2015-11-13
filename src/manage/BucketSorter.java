package manage;

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
public class BucketSorter<T extends Comparable<T>> {
   
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
    public BucketSorter(int size) {
        // create data array with inputted size
        this.data = (LinkedList<T>[]) new Object[size / TEN];
    }
    
    /**
     * Read data into HashMap.
     * @param blocks the collection of info to be sorted
     * @param comparator comparator for type of data being sorted
     */
    public void read(Collection<T> blocks, Comparator<? super T> comparator) {
        // get an iterator to go through all values
        Iterator<T> blockIter = blocks.iterator();
        while (blockIter.hasNext()) {
            // grab value of size
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
                if (comparator.compare(listIterator.next(), toInsert) < 0) {
                    // need to go back one with listiterator
                    listIterator.previous();
                    break;
                }
            }
            // add when either end condition is reached
            listIterator.add(toInsert);
        }
    }

}
