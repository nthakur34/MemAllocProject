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
        assertEquals("[25, 33, 0, 39]", e4.toString());

    }
}
