/* Navjyoth Thakur
 * 600.226.01
 * nthakur2
 * p3

/** TESTS for 600.226 Fall 2015 Project 3 - HashMapOpen implementation
 *  Explicit tests for metrics after inserting and insert caused rehashing
 *  Explicit tests for contents and lookup after inserting, with duplicate vals
 *  Explicit test for linear probing when insert, get, contains
 *  Explicit test for clear, probe functions
 *  Explicit test that put of existing key replaces value, no key changes
 *  Explicit test remove all, remove duplicate values - contents and metrics
 *  Explicit tests of tombstones:
 *    get/insert/probing after remove with tombstones
 *    rehashing when ghosts > size
 *
 *  Missing tests: exceptions - you should throw them as specified,
 *    but we are not going to explicitly test/grade for this.
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
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class AVLtreeTest {

    static AVLtree<Integer> e4;  // empty map, max load .4
    static AVLtree<Integer> e7;  // empty map, max load .7
    static AVLtree<Integer> all;  // all in map

    // note - Integer hashCode() returns the int value
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static ArrayList<Integer> svals;

    @BeforeClass
    public static void init() {
        svals = new ArrayList<Integer>();
        for (Integer val: iray) {
            svals.add(val);
        } 
    }
   


    @Before
    public void setup() {
        // these start out empty before each test
        e4 = new AVLtree<Integer>();  
        e7 = new AVLtree<Integer>();  

        // this is full set, assuming put works correctly
        all = new AVLtree<Integer>();
        for (int i=0; i < iray.length; i++) {
            all.add(svals.get(i));
        }
    }

    @Test
    public void testInit() {
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", svals.toString());
        // won't necessarily be in this order
        // assertEquals("[3=tre, 6=six, 0=zro, 1=one, 10=ten, 5=fyv, 8=ate, 9=nyn, 4=for, 7=svn, 2=two]", entries.toString());
    }


    @Test
    public void testEmptyTree() {
        assertFalse(e4.contains(1));
        assertTrue(all.contains((Integer) 1));
        assertTrue(e4.isEmpty());
        assertFalse(all.isEmpty());
    }

    @Test
    public void testAdd() {
        assertEquals(11, all.size());
        assertTrue(e4.add((Integer) 5));
        assertTrue(e4.contains((Integer) 5));
        assertTrue(e4.size() == 1);
        
        assertTrue(all.add((Integer) 11));
        //Use traversal to see if last one is 11. 
        Iterator<Integer> iter = all.preOrder().iterator();
        int temp = 0;
        while(iter.hasNext()) {
        	temp = iter.next();
        	System.out.print(temp + "   ");
        }
        System.out.println("");
        assertEquals(11, temp);
        System.out.println(all.toString());
        assertTrue(all.remove((Integer) 7));
        assertFalse(all.contains((Integer) 7)); 
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11]", all.toString());

    }


}



