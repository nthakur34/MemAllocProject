package manage;

/** 
 * Generic sequence interface (positional list).
 *
 * 600.226 Data Structures, Fall 2015, Project 2
 *
 * @param <T> the underlying type stored in the list
 */

public interface Sequence<T> extends Iterable<T> {

    /**
     * Insert a new item at a specified position in the sequence.
     * Valid positions are 0 (inclusive) to size() (inclusive).
     * @param item the object to insert
     * @param pos the position in which to insert it. If pos < 0,
     * insert at the beginning of the sequence.  If pos > size(),
     * insert at the end.
     */
    void addAt(T item, int pos);


    /** 
     * Return (not remove) the item at a particular position in the sequence.
     * Valid positions are 0 (inclusive) to size() (exclusive).
     * @param pos the position of the item to retrieve
     * @return the item, or null if the position is invalid.
      */
    T getAt(int pos);


    /** 
     * Remove the item at a particular position in the sequence.
     * Valid positions are 0 (inclusive) to size() (exclusive).
     * @param pos the position of the item to remove
     * @return the item removed, or null if the position is invalid.
     */
    T removeAt(int pos);


    /**
     * Set the value stored at a particular position to a new value.
     * Valid positions are 0 (inclusive) to size() (exclusive).
     * @param val the new value to be stored
     * @param pos the position of the item to be replaced
     * @return the original value at that position, or null if pos is invalid.
     */
    T setAt(T val, int pos);


    /**
     * Remove all existing elements from the sequence.
     */
    void clear();

    /**
     * Report whether the sequence is empty or not.
     * @return true if the sequence is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Return the number of items present in the sequence.
     * @return the number of items
     */
    int size();


    /**
     * Determine whether a particular value is in the sequence.
     * @param val the item to be found
     * @return true if there, false otherwise
     */
    boolean contains(T val);

    /**
     * Find the position of the first occurrence of a value in the sequence.
     * @param val the item to be found
     * @return the position [0,size()), or -1 if not found
     */
    int position(T val);


    /**
     * Return a representation of the items in the sequence,
     * in order from first item to last item.  For example,
     * if the sequence contains three items,  the returned value
     * will be "[item1, item2, item3]"
     * @return the comma-separated items, in square brackets
     */
    String toString();


    /* Inherited from Iterable: Iterator<T> iterator();  */
    /* Your sequence iterator must implement the optional remove operation. */
    
}
