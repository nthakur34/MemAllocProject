package manage;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Generic sorter through bucket implementation.
 * @author Nitin
 *
 * @param <T> Type of data being sorted
 */
public class BucketSorter<T extends Comparable<? super T>> {
    
    /**
     * HashMap representation of the data.
     */
    private HashMap<Integer, LinkedList<T>> data;
    
    /**
     * Constructor for defrag.
     */
    public BucketSorter() {
        this.data = new HashMap<Integer, LinkedList<T>>();
    }
    
    /**
     * Read data into HashMap.
     * @param blocks the collection of info to be sorted
     */
    public void read(Collection<T> blocks) {
        // get an iterator to go through all values
        Iterator<T> blockIter = blocks.iterator();
        while (blockIter.hasNext()) {
            // grab value of size
            T toInsert = blockIter.next();
            // check if hash code already exists
            // because generic, not
            if (this.data.containsKey(toInsert)) {
                // does contain key
                // get the linked list at the bucket
                LinkedList<T> currList = this.data.get(blockIter.hashCode());
                // make an iterator for the list
                ListIterator<T> listIterator = 
                        (ListIterator<T>) currList.iterator();
                // find where either the next no-element position is
                // or where 
                // make comparator to compare memblock addresses
                Comparator<MemBlock> comparator = 
                        new MemBlock.MemBlockComparator();
                while (listIterator.hasNext()) {
                    if (comparator.compare((MemBlock) listIterator.next(),
                            (MemBlock) toInsert) < 0) {
                        break;
                    }
                }
            } else {
                System.out.println("filler");
            }
        }
    }

}
