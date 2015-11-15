/*
 * Jonathan Liu
 * Nav Thakur
 */

package manage;
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


public class BestFitTest {
    
    static public BestFit bf1;
    static public BestFit bf2;
    static public BestFit bf3;
    static public BestFit bf4;
    static public BestFit bf5;
    
    @Before
    public void setup() {
        
        bf1 = new BestFit(10);
        bf2 = new BestFit(300);
        bf3 = new BestFit(50);
    }
    
    @Test
    public void grabToAllocTest() {
        
    }
    
    @Test
    public void allocTest() {
        // this method tests alloc, getCollection, and deAlloc
        assertEquals(bf1.alloc(1, false), 0);
        assertEquals(bf1.alloc(9, false), 1);
        assertEquals("[]", bf1.getCollection().toString());
        //fails since all full, but retains ID #
        assertEquals(bf1.alloc(1, false), -1);
        //free the large 9 block
        MemBlock temp = new MemBlock(1, 9, true);
        assertEquals(bf1.dealloc(2), temp);
        assertEquals(bf1.alloc(2, false), 1);
        assertEquals(bf1.alloc(5, false), 3);
        assertEquals(bf1.alloc(1, false), 8);
        temp = new MemBlock(0, 1, true);
        assertEquals(bf1.dealloc(1), temp);
        assertEquals("[9, 0]", bf1.getCollection().toString());
        temp = new MemBlock(1, 2, true);
        assertEquals(bf1.dealloc(4), temp);
        assertEquals("[0, 9, 1]", bf1.getCollection().toString());
        assertEquals(bf1.alloc(3, false), 0);
        //since, defrags, and 0 and 1 adjacent, combine, then used
        assertEquals("[9]", bf1.getCollection().toString());
    }
    
    @Test
    public void rebuildTest() {
        MemBlock temp = new MemBlock(0, 15, true);
        ArrayList<MemBlock> list = new ArrayList<>();
        list.add(temp);
        temp = new MemBlock(47, 2, true);
        list.add(temp);
        temp = new MemBlock(50, 4, true);
        list.add(temp);
        temp = new MemBlock(54, 1, true);
        list.add(temp);
        temp = new MemBlock(55, 20, true);
        list.add(temp);
        temp = new MemBlock(78, 24, true);
        list.add(temp);
        
        bf2.rebuild(list);
        assertEquals(bf2.getCollection().toString(), "[50, 47, 54, 55, 0, 78]");


    }
    
    
    
}
