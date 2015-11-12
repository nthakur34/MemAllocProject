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

public class QuickDefragTest {

    static QuickDefrag<MemBlock> e4;  
    static QuickDefrag<MemBlock> all;

    // note - Integer hashCode() returns the int value
    static MemBlock[] iray = {null, new MemBlock(0, 10, true), new MemBlock(
            10, 7, true), new MemBlock(17, 3, true), new MemBlock(25, 6, true),
            new MemBlock(33, 5, true), new MemBlock(39, 3, true), new MemBlock
            (42, 8, true)};
    static ArrayList<MemBlock> svals;

    @BeforeClass
    public static void init() {
        svals = new ArrayList<MemBlock>();
        for (MemBlock val: iray) {
            svals.add(val);
        } 
    }
   


    @Before
    public void setup() {
        // these start out empty before each test
        e4 = new QuickDefrag<MemBlock>(svals);  
        all = new QuickDefrag<MemBlock>(null);  
    }

    @Test
    public void testSortedList() {
        //sorted by size
        assertEquals("[0, 10, 17, 25, 33, 39, 42]", e4.toString());
        assertEquals("[]", all.toString());
    }

    @Test
    public void testDefrag() {
        e4.defragBlocks();
        assertEquals("[]", all.toString());
        System.out.println(svals.get(1).getSize());
        System.out.println(svals.get(2).getSize());
        assertEquals("[0, 25, 33, 39]", e4.toString());

    }
  
/*    @Test
    public void testRemove() {
        assertEquals(11, all.size());
        assertEquals((Integer) 10, all.removeMax());
        assertFalse(all.contains((Integer) 10));
        assertEquals(10, all.size());
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
        all.removeMax();
        assertEquals(1, e4.size());
        assertEquals(11, all.size());
        
    }
*/
}
