package manage;
/* Navjyoth Thakur
 * 600.226.01
 * nthakur2
 * p4

/** TESTS for 600.226 Fall 2015 Project 3 - HashMapOpen implementation
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class PriorityQueueTest {

    static PriorityQueue<Integer> e4; 
    static PriorityQueue<Integer> e5; 
    static PriorityQueue<MemBlock> e7;  
    static PriorityQueue<Integer> all;

    // note - Integer hashCode() returns the int value
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static ArrayList<Integer> svals;
    static MemBlock[] pray = {new MemBlock(0, 10, true), new MemBlock(
            10, 7, true), new MemBlock(17, 3, true), new MemBlock(25, 6, true),
            new MemBlock(33, 5, true), new MemBlock(39, 3, true), new MemBlock
            (42, 8, true)};
    static ArrayList<MemBlock> pvals;

    @BeforeClass
    public static void init() {
        svals = new ArrayList<Integer>();
        for (Integer val: iray) {
            svals.add(val);
        } 
        
        pvals = new ArrayList<MemBlock>();
        for (MemBlock val: pray) {
            pvals.add(val);
        } 
    }

    @Before
    public void setup() {
        // these start out empty before each test
        e4 = new PriorityQueue<Integer>(); 
        e5 = new PriorityQueue<Integer>(); 
        e7 = new PriorityQueue<MemBlock>();  
        
        e5.add(10);
        e5.add(9);
        e5.add(8);
        e5.add(7);
        e5.add(6);
        e5.add(5);

        // this is full set, assuming put works correctly
        all = new PriorityQueue<Integer>();
        for (int i=0; i < iray.length; i++) {
            all.add(svals.get(i));
        }
        
        for (int i=0; i < pray.length; i++) {
            e7.add(pvals.get(i));
        }
        
    }

    @Test
    public void testInit() {
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", svals.toString());
        // won't necessarily be in this order
        // assertEquals("[3=tre, 6=six, 0=zro, 1=one, 10=ten, 5=fyv, 8=ate, 9=nyn, 4=for, 7=svn, 2=two]", entries.toString());
    }


    @Test
    public void testEmptyQueue() {
        assertFalse(e4.contains((Integer) 1));
        assertTrue(all.contains((Integer) 1));
        assertTrue(e4.isEmpty());
        assertFalse(all.isEmpty());
        assertFalse(e7.isEmpty());
        assertFalse(e7.contains(new MemBlock(4, 6, true)));
        assertTrue(e7.contains(new MemBlock(0, 10, true)));
    }

    @Test
    public void testAdd() {
        assertEquals(11, all.size());
        assertEquals("[]", e4.toString());
        assertTrue(e4.add((Integer) 5));
        assertTrue(e4.contains((Integer) 5));
        assertTrue(e4.size() == 1);
        assertEquals("[5]", e4.toString());
        assertTrue(all.add((Integer) 11));
        assertTrue(all.contains((Integer) 11));
        assertTrue(all.add((Integer) 5));
        assertTrue(all.add((Integer) 5));
        assertTrue(all.add((Integer) 11));
        assertEquals("[11, 9, 11, 6, 8, 5, 10, 0, 3, 2, 7, 1, 5, 4, 5]", all.toString());
        assertEquals("[10, 9, 8, 7, 6, 5]", e5.toString());
        e5.add(13);
        e5.add(14);
        assertEquals("[14, 13, 10, 9, 6, 5, 8, 7]", e5.toString());
        assertEquals((Integer) 14, e5.removeMax());
        e5.add(14);
        assertEquals("[14, 13, 10, 9, 6, 5, 8, 7]", e5.toString());
        assertEquals("[, ")
        System.out.println("");
        assertEquals(11, temp);
        System.out.println(all.toString());
        assertTrue(all.remove((Integer) 7));
        assertFalse(all.contains((Integer) 7)); 
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11]", all.toString());*/

    }
  
    @Test
    public void testRemove() {
        assertEquals(11, all.size());
        assertEquals((Integer) 10, all.removeMax());
        assertFalse(all.contains((Integer) 10));
        assertEquals(10, all.size());
        System.out.println(all.toString());
        assertEquals((Integer) 9, all.removeMax());
        assertFalse(all.contains((Integer) 9));
        assertTrue(e4.add((Integer) 6));
        assertTrue(e4.contains((Integer) 6));
        assertEquals("[6]", e4.toString());
        assertEquals((Integer) 6, e4.removeMax());
        assertTrue(e4.isEmpty());
        assertTrue(all.add((Integer) 11));
        assertEquals("[11, 8, 5, 6, 7, 1, 4, 0, 3, 2]", all.toString());
        assertTrue(all.contains((Integer) 11));
        assertEquals((Integer) 11, all.removeMax());
        assertFalse(all.contains((Integer) 11));
    }
    
    @Test
    public void testSize() {
        assertFalse(e4.contains((Integer) 1));
        assertTrue(all.contains((Integer) 1));
        assertTrue(e4.isEmpty());
        assertFalse(all.isEmpty());
        assertEquals(0, e4.size());
        assertEquals(11, all.size());
        assertTrue(e4.add(12));
        assertTrue(all.add(12));
        System.out.println(all.toString());
        all.removeMax();
        assertEquals(1, e4.size());
        assertEquals(11, all.size());
        
    }

}

