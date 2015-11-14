package manage;

import java.util.ArrayList;

/** 
 * Generic queue interface.
 *
 * Citing using code from project 2:
 * 600.226 Data Structures, Fall 2015, Project 2
 *
 * @param <T> the underlying type stored in the list
 */

public interface Queue<T> {

    /**
     * Add a new item to the end (back) of the queue.
     * @param item the object to add
     */
    void enqueue(T item);


    /** 
     * Return (not remove) the item at the front of the queue.
     * @return the item, or null if the queue is empty
     */
    T peek();


    /** 
     * Remove the item at the front of the queue.
     * @return the item removed, or null if the queue is empty
     */
    T dequeue();


    /**
     * Remove all existing elements from the queue.
     */
    void clear();


    /**
     * Report whether the queue is empty or not.
     * @return true if the queue is empty; false otherwise
     */
    boolean isEmpty();


    /**
     * Return the number of items present in the queue.
     * @return the number of items
     */
    int size();

    /**
     * Get ArrayList form of current queue.
     * @return ArrayList of values in queue.
     */
    ArrayList<T> getArrayList();
    

    /**
     * Return a representation of the items in the queue, in order
     * from front to back, separated by ' __ ', including 'FRONT' and
     * 'BACK' labels.  For example, if the queue contains three items,
     * with item1 at the front, the returned value will be
     * "FRONT __ item1 __ item2 __ item3 __ BACK"
     * @return the formatted string
     */
    String toString();

}
