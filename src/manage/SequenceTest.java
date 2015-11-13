package manage;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.junit.Before;


/**
 * Written for Project 2, but updated extensively based on TA comments.
 * @author Nitin
 *
 */
public class SequenceTest {
    
    Sequence<Integer> testSeq;
    Sequence<String> test2Seq;
    
    @Before
    public void setup() {
        testSeq = new SequenceDLL<Integer>(0);
        testSeq.addAt(5, 0);
        testSeq.addAt(2, 5);
        // 5-0-2
        test2Seq = new SequenceDLL<String>("swag");
        test2Seq.addAt("sick", 0);
        test2Seq.addAt("baller", 5);
        // test constructor with other type
    }

    @Test
    public void testAddAt() {
        // implicitly tested by before and getAt
        // but can add one more
        testSeq.addAt(4, 1);
        // 5-4-0-2
        int ret = testSeq.getAt(1);
        assertEquals(ret, 4);
        assertEquals(testSeq.size(), 4);
        // implicitly tested by before and getAt
        // but can add one more
        testSeq.addAt(6, 4);
        // 5-4-0-6-2
        assertEquals(testSeq.size(), 5);
        ret = testSeq.getAt(4);
        assertEquals(ret, 6);
        
        // test weird cases
        // before 0, should add to beginning
        testSeq.addAt(3, -1);        
        // 3-5-4-0-6-2
        ret = testSeq.getAt(0);
        assertEquals(ret, 3);
        // after this, size should be 6
        assertEquals(testSeq.size(), 6);
        // add at greater than size
        testSeq.addAt(3, 15);        
        // 3-5-4-0-6-2-3
        ret = testSeq.getAt(6);
        assertEquals(ret, 3);
    }

    @Test
    public void testGetAt() {
        // test success
        int ret = testSeq.getAt(0);
        assertEquals(ret, 5);
        ret = testSeq.getAt(1);
        assertEquals(ret, 0);
        ret = testSeq.getAt(2);
        assertEquals(ret, 2);
        // test fails
        assertNull(testSeq.getAt(-1));
        assertNull(testSeq.getAt(3));
    }

    @Test
    public void testRemoveAt() {
        // success cases
        int ret = testSeq.removeAt(0);
        assertEquals(ret, 5);
        assertEquals(testSeq.size(), 2);
        assertNull(testSeq.removeAt(2));
        ret = testSeq.removeAt(1);
        assertEquals(ret, 2);
        // test fail cases
        // < 0
        assertNull(testSeq.removeAt(-1));
        // > size
        assertNull(testSeq.removeAt(100));
    }

    @Test
    public void testSetAt() {
        // success
        int ret = testSeq.setAt(2, 1);
        assertEquals(ret, 0);
        assertEquals(testSeq.size(), 3);
        // fails
        assertNull(testSeq.setAt(2, -1));
        assertNull(testSeq.setAt(2, 13));
    }

    @Test
    public void testClear() {
        testSeq.clear();
        assertEquals(testSeq.size(), 0);
        assertTrue(testSeq.isEmpty());
        // now check with partial list
        this.setup();
        testSeq.removeAt(0);
        testSeq.removeAt(1);
        assertFalse(testSeq.isEmpty());
        testSeq.clear();
        assertEquals(testSeq.size(), 0);
        assertTrue(testSeq.isEmpty());
        // try clearing an empty list
        testSeq.clear();
        assertEquals(testSeq.size(), 0);
        assertTrue(testSeq.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        // full list
        assertFalse(testSeq.isEmpty());
        testSeq.removeAt(0);
        // partial list
        assertFalse(testSeq.isEmpty());
        // empty list
        testSeq.clear();
        assertTrue(testSeq.isEmpty());
        assertEquals(testSeq.size(), 0);
    }

    @Test
    public void testSize() {
        assertEquals(testSeq.size(), 3);
    }

    @Test
    public void testContains() {
        // success
        assertTrue(testSeq.contains(5));
        assertTrue(testSeq.contains(0));
        assertTrue(testSeq.contains(2));
        // repeating element
        // add at end
        testSeq.addAt(5, 100);
        assertTrue(testSeq.contains(5));
        // fails
        assertFalse(testSeq.contains(4));
        assertFalse(testSeq.contains(1));
        // check on empty sequence
        testSeq.clear();
        assertFalse(testSeq.contains(5));
        assertFalse(testSeq.contains(0));
        assertFalse(testSeq.contains(2));
    }

    @Test
    public void testPosition() {
        // success
        assertEquals(testSeq.position(5), 0);
        assertEquals(testSeq.position(0), 1);
        assertEquals(testSeq.position(2), 2);
        // add a repeat
        // see if it only returns the first occurrence
        testSeq.addAt(5, 100);
        assertEquals(testSeq.position(5), 0);
        // fail
        assertEquals(testSeq.position(4), -1);
        assertEquals(testSeq.position(1), -1);
        // check empty sequence
        testSeq.clear();
        assertEquals(testSeq.position(5), -1);
        assertEquals(testSeq.position(0), -1);
        assertEquals(testSeq.position(2), -1);
        assertEquals(testSeq.position(4), -1);
        assertEquals(testSeq.position(1), -1);
    }

    @Test
    public void testToString() {
        assertEquals(testSeq.toString(), "[5, 0, 2]");
        System.out.println(testSeq.toString());
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void testIteratorConcurrent() {
        // test construction
        Iterator<Integer> it = testSeq.iterator();
        assertFalse(it == null);
        assertTrue(it.hasNext());
        // test next
        assertEquals((int) it.next(),(int) 5);
        // test remove
        it.remove();
        assertEquals(testSeq.size(), 2);
        // test next
        assertEquals((int) it.next(),(int) 0);
        // test remove
        it.remove();
        // check okToRemove
        //it.remove();
        assertEquals(testSeq.size(), 1);
        // test next()
        assertEquals((int) it.next(), 2);
        // check next will be null
        assertNull(it.next());
        // test remove
        it.remove();
        assertTrue(testSeq.isEmpty());
        // check states
        testSeq.addAt(5,0);
        testSeq.addAt(5,0);
        testSeq.addAt(5,0);
        //it.hasNext();
        //it.next();
        it.remove();
    }
    
    @Test(expected = java.lang.IllegalStateException.class)
    public void testIteratorIllegal() {
        // test construction
        Iterator<Integer> it = testSeq.iterator();
        assertFalse(it == null);
        assertTrue(it.hasNext());
        // test next
        assertEquals((int) it.next(),(int) 5);
        // test remove
        it.remove();
        assertEquals(testSeq.size(), 2);
        // test next
        assertEquals((int) it.next(),(int) 0);
        // test remove
        it.remove();
        // check okToRemove
        it.remove();
        assertEquals(testSeq.size(), 1);
        // test next()
        assertEquals((int) it.next(), 2);
        // check next will be null
        assertNull(it.next());
        // test remove
        it.remove();
        assertTrue(testSeq.isEmpty());
        // check states
        testSeq.addAt(5,0);
        testSeq.addAt(5,0);
        testSeq.addAt(5,0);
        //it.hasNext();
        //it.next();
        //it.remove();
    }
    
    


}
