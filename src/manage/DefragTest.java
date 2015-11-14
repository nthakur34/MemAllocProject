package manage;
/* Navjyoth Thakur - nthakur2 -  600.226.01 - P4


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

public class DefragTest {

    static Defrag e4;  
    static Defrag e5;  
    static Defrag all;

    // note - Integer hashCode() returns the int value
    static MemBlock[] iray = {
        new MemBlock(0, 10, true), 
        new MemBlock(10, 7, true),
        new MemBlock(17, 3, true),
        new MemBlock(25, 6, true),
        new MemBlock(33, 5, true),
        new MemBlock(39, 3, true),
        new MemBlock(42, 8, true)};
    // post defrag should look like:
    // [0,25,33,39]
    static MemBlock[] crayarray = {
        new MemBlock(0, 10, true),
        new MemBlock(25, 6, true),
        new MemBlock(10, 7, true),
        new MemBlock(17, 3, true),
        new MemBlock(39, 3, true),
        new MemBlock(42, 8, true),
        new MemBlock(33, 5, true)};
    // [0, 25, 10, 17, 39, 42, 33]
    
    static ArrayList<MemBlock> svals;
    static ArrayList<MemBlock> crayvals;

    @Before
    public void setup() {
        svals = new ArrayList<MemBlock>();
        for (MemBlock val: iray) {
            svals.add(val);
        }
        crayvals = new ArrayList<MemBlock>();
        for (MemBlock val: crayarray) {
            crayvals.add(val);
        } 
        // these start out empty before each test
        e4 = new Defrag(svals, 50);
        e5 = new Defrag(crayvals, 50);
        all = new Defrag(null, 0);  
    }

    @Test
    public void testToString() {
        //sorted by size
        // check to string before defrag
        assertEquals("[0, 10, 17, 25, 33, 39, 42]", e4.toString());
        assertEquals("[]", all.toString());
    }

    @Test
    public void testQuickSort() {
        assertEquals("[0, 25, 10, 17, 39, 42, 33]", e5.toString());
        e5.quickSort();
        assertEquals("[0, 10, 17, 25, 33, 39, 42]", e5.toString());
        assertEquals("[]", all.toString());
    }
    
    @Test
    public void testBucketSort() {
        assertEquals("[0, 25, 10, 17, 39, 42, 33]", e5.toString());
        e5.quickSort();
        assertEquals("[0, 10, 17, 25, 33, 39, 42]", e5.toString());
        assertEquals("[]", all.toString());
    }
    
    @Test
    public void testDefrag() {
        e4.defragBlocks();
        assertEquals("[0, 25, 33, 39]", e4.toString());
        assertEquals("[]", all.toString());
    }
    
}
