package manage;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Citing from project 2 code:
 * Doubly linked list that uses Sequence.
 * No sentinel nodes.
 * @author Nitin
 * @param <T> type for the data in the list.
 *
 */
public class SequenceDLL<T> implements Sequence<T> {
    
    /**
     * First node in linked list (not sentinel).
     */
    private Node<T> head;
    
    /**
     * Last node in linked list (not sentinel).
     */
    private Node<T> end;
    
    /**
     * Size of list.
     */
    private int size = 0;

    /**
     * 
     */
    private long state = 0;
    
    /**
     * Node class for a doubly linked list.
     * @author Nitin
     *
     * @param <type> type of data in Node (should be same as T)
     */
    private class Node<type> {
    
        /**
         * Pointer to prev node.
         */
        private Node<type> prev;
        
        /**
         * Pointer to next node.
         */
        private Node<type> next;
        
        /**
         * Data based on template.
         */
        private type data;
        
        /**
         * Node constructor using an input of data.
         * @param inData input of data type type.
         */
        public Node(type inData) {
            this.setData(inData);
            this.setPrev(null);
            this.setNext(null);
        }
    
        /**
         * Get previous node.
         * @return previous node
         */
        public Node<type> getPrev() {
            return this.prev;
        }
    
        /**
         * Set the previous node.
         * @param inPrev new previous node.
         */
        public void setPrev(Node<type> inPrev) {
            this.prev = inPrev;
        }
    
        /**
         * Return next node.
         * @return next node
         */
        public Node<type> getNext() {
            return this.next;
        }
    
        /**
         * Set the next node.
         * @param inNext new next node.
         */
        public void setNext(Node<type> inNext) {
            this.next = inNext;
        }
    
        /**
         * Return the data of the node.
         * @return node data.
         */
        public type getData() {
            return this.data;
        }
        
        /**
         * Set the data.
         * @param inData new data value.
         */
        public void setData(type inData) {
            this.data = inData;
        }
    
    }

    /**
     * Iterator for SequenceDLL.
     * @author Nitin
     * @param <type>
     *
     */
    private class SequenceDLLIterator<type> implements Iterator<T> {
        
        /**
         * current node (pointed by iterator).
         * initialize pointing at head of list
         */
        private Node<T> curr;
        
        /**
         * flag indicating if we've had a call to next() since last remove().
         */
        private boolean okToRemove;
        
        /**
         * snapshot value from when iterator was created, used to help
         * detect concurrent modification situation.
         */
        private long myState;  //snapshot of the list when iterator created
        
        /**
         * Constructor for the iterator.
         */
        public SequenceDLLIterator() {
            this.curr = SequenceDLL.this.head;
            this.okToRemove = false;
            this.myState = SequenceDLL.this.state;
        }
        
        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (this.myState != SequenceDLL.this.state) { // check modify
                throw new java.util.ConcurrentModificationException();
            }
            if (this.curr == null) {
                return false;
            }
            return this.curr.getNext() != null;
        }
    

        @Override
        public T next() throws ConcurrentModificationException {
            if (this.myState != SequenceDLL.this.state) { // check modify
                throw new java.util.ConcurrentModificationException();
            }
            
            if (this.curr == null) {
                System.out.println("End of list reached");
                return null;
            }
            
            T retData = this.curr.getData();
            this.curr = this.curr.getNext();
            this.okToRemove = true;
            return retData;
        }
        
        @Override
        public void remove() throws ConcurrentModificationException,
            IllegalStateException {
            // check states
            if (this.myState != SequenceDLL.this.state) { // check modify
                throw new java.util.ConcurrentModificationException();
            }
            // see if allowed to remove
            if (!this.okToRemove) {
                throw new java.lang.IllegalStateException();
            }
            // see if at end of list
            if (this.curr == null) {
                if (SequenceDLL.this.end.getPrev() == null) {
                    SequenceDLL.this.head = null;
                } else {
                    SequenceDLL.this.end.getPrev().setNext(null);
                }
                SequenceDLL.this.end = SequenceDLL.this.end.getPrev();
                // otherwise...
            } else if (this.curr.getPrev() != null) {
                this.curr.getPrev().setNext(this.curr);
                if (this.curr.getPrev() == SequenceDLL.this.head) {
                    SequenceDLL.this.head = this.curr;
                }
                this.curr.setPrev(this.curr.getPrev().getPrev());
            }
            this.okToRemove = false;
            SequenceDLL.this.size--;
        }
        
        
        
    }

    /**
     * Constructor for SequenceDLL, just sets first node.
     * @param firstData first node in list
     */
    public SequenceDLL(T firstData) {
        Node<T> firstNode = new Node<T>(firstData);
        this.head = firstNode;
        this.end = firstNode;
        this.size = 1;
    }
    
    /**
     * Constructor for SequenceDLL, with no nodes.
     */
    public SequenceDLL() {
        this.head = null;
        this.end = null;
        this.size = 0;
    }
    
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new SequenceDLLIterator<T>();
        return it;
    }

    @Override
    public void addAt(T item, int pos) {
        // cursor to iterate through
        Node<T> curr = this.head;
        // 
        if (pos <= 0) {
            // If the position is the head,
            // add node to the head
            this.head = new Node<T>(item);
            this.head.setNext(curr);
            if (curr != null) {
                curr.setPrev(this.head);
            } else {
                this.end = this.head;
            }
        } else if (pos >= this.size) {
            // otherwise add to the end
            curr = this.end;
            this.end = new Node<T>(item);
            this.end.setPrev(curr);
            curr.setNext(this.end);
        } else if (pos <= this.size / 2) {
            // iterate through list, once you reach pos stop
            for (int i = 0; i < pos; i++) {
                curr = curr.getNext();
            }
            // make new node with inputted data
            Node<T> newNode = new Node<T>(item);
            // push new node where the curr is right now
            newNode.setPrev(curr.getPrev());
            curr.getPrev().setNext(newNode);
            newNode.setNext(curr);
            curr.setPrev(newNode);
        } else {
            curr = this.end;
            for (int i = 0; i < this.size - pos - 1; i++) {
                curr = curr.getPrev();
            }
            // make new node with inputted data
            Node<T> newNode = new Node<T>(item);
            // push new node where the curr is right now
            newNode.setPrev(curr.getPrev());
            curr.getPrev().setNext(newNode);
            newNode.setNext(curr);
            curr.setPrev(newNode);
        }
        this.state++;
        this.size++;
    }

    @Override
    public T getAt(int pos) {
        // cursor to iterate through
        Node<T> curr = this.head;
        // invalid pos catch
        if (pos < 0 || pos >= this.size) {
            System.out.println("Index lies outside of list.");
            return null;
        }
        if (pos <= this.size / 2) {
            // iterate through list, once you reach pos stop
            for (int i = 0; i < pos; i++) {
                curr = curr.getNext();
            }
        } else {
            curr = this.end;
            // iterate through list, once you reach pos stop
            for (int i = 0; i < this.size - pos - 1; i++) {
                curr = curr.getPrev();
            }
        }
        // return data
        return curr.getData();
    }

    @Override
    public T removeAt(int pos) {
        // cursor to iterate through
        Node<T> curr = this.head;
        // invalid pos catch
        if (pos < 0 || pos >= this.size) {
            System.out.println("Index lies outside of list.");
            return null;
        }
        if (pos <= this.size / 2) {
            // iterate through list, once you reach pos stop
            for (int i = 0; i < pos; i++) {
                curr = curr.getNext();
            }
        } else {
            curr = this.end;
            // iterate through list, once you reach pos stop
            for (int i = 0; i <= this.size - pos - 1; i++) {
                curr = curr.getPrev();
            }
        }
        
        T toRemove = curr.getData();
        
        if (curr.getNext() != null) {
            curr.getNext().setPrev(curr.getPrev());
        }
        if (curr.getPrev() != null) {
            curr.getPrev().setNext(curr.getNext());
        }
        if (curr == this.head) {
            this.head = curr.getNext();
        }
        if (curr == this.end) {
            this.end = curr.getPrev();
        }
        // update size
        this.size--;
        this.state++;
        // return data
        return toRemove;
    }

    @Override
    public T setAt(T val, int pos) {
        // cursor to iterate through
        Node<T> curr = this.head;
        // invalid pos catch
        if (pos < 0 || pos >= this.size) {
            System.out.println("Index lies outside of list.");
            return null;
        }
        if (pos <= this.size / 2) {
            // iterate through list, once you reach pos stop
            for (int i = 0; i < pos; i++) {
                curr = curr.getNext();
            }
        } else {
            curr = this.end;
            // iterate through list, once you reach pos stop
            for (int i = 0; i <= this.size - pos - 1; i++) {
                curr = curr.getPrev();
            }
        }
        // save return data before changing val
        T toReturn = curr.getData();
        // change val
        this.state++;
        curr.setData(val);
        return toReturn;
    }

    @Override
    public void clear() {
        this.head = null;
        this.end = null;
        this.size = 0;
        this.state++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T val) {
        for (Node<T> curr = this.head; curr != null; curr = curr.getNext()) {
            if (curr.getData() == val) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int position(T val) {
        int counter = 0;
        for (Node<T> curr = this.head; curr != null; curr = curr.getNext()) {
            if (curr.getData() == val) {
                return counter;
            }
            counter++;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        String retString = "[";
        for (Node<T> n = this.head; n != null; n = n.getNext()) {
            retString = retString + n.getData();
            if (n.getNext() != null) {
                retString = retString + ", ";
            }
        }
        retString = retString + "]";
        return retString;
    }

    
    
}
