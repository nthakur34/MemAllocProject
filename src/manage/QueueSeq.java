package manage;

/**
 * Citing from project 2 code:
 * QueueSeq is a queue wrapper for a Sequence, using the doubly linked
 * list sequence.
 * @author Nitin
 *
 * @param <T> The type for the data in the sequence.
 */
public class QueueSeq<T> implements Queue<T> {

    /**
     * Data is a doubly linked list that is represented as a queue.
     */
    Sequence<T> data = new SequenceDLL<T>();
    
    @Override
    public void enqueue(T item) {
        this.data.addAt(item, 0);
    }

    @Override
    public T peek() {
        return this.data.getAt(this.size() - 1);
    }

    @Override
    public T dequeue() {
        return this.data.removeAt(this.size() - 1);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public int size() {
        return this.data.size();
    }
    
    @Override
    public String toString() {
        return this.data.toString();
    }

}
