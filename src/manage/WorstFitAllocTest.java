package manage;
/* Navjyoth Thakur
 * 600.226.01
 * nthakur2
 * p4

/** TESTS for 600.226 Fall 2015 Project 4
 */


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;


public class WorstFitAllocTest {

    static WorstFitAlloc e4; 
    static WorstFitAlloc e5; 
    static WorstFitAlloc e6;  
    
    static MemBlock[] pray = {new MemBlock(0, 10, true), new MemBlock(
            10, 7, true), new MemBlock(17, 3, true), new MemBlock(25, 6, true),
            new MemBlock(33, 5, true), new MemBlock(39, 3, true), new MemBlock
            (42, 8, true)};
    
    static ArrayList<MemBlock> pvals;
    
    @BeforeClass
    public static void init() {
       
        pvals = new ArrayList<MemBlock>();
        for (MemBlock val: pray) {
            pvals.add(val);
        } 

    }

    @Before
    public void setup() {
        
        e4 = new WorstFitAlloc(10);
        e5 = new WorstFitAlloc(300);
        e6 = new WorstFitAlloc(500);
        
        e6.alloc(500, false);
    }

    @Test
    public void testAlloc() {
       //allocates 1 and 9 to a block of size 10
       assertEquals(0, e4.alloc(1, false));
       assertEquals(1, e4.alloc(9, false));
       assertEquals("[]", e4.getCollection().toString());
       //fails since all filles, but retains ID #
       assertEquals(e4.alloc(1, false));
       //free the large 9 block
       assertTrue(e4.dealloc(2));
       assertTrue(e4.alloc(2, false));
       assertTrue(e4.alloc(5, false));
       assertTrue(e4.alloc(1, false));
       assertTrue(e4.dealloc(1));
       assertEquals("[9, 0]", e4.getCollection().toString());
       assertTrue(e4.dealloc(4));
       assertEquals("[1, 0, 9]", e4.getCollection().toString());
       assertTrue(e4.alloc(3, false));
       //since, defrags, and 0 and 1 adjacent, combine, then used
       assertEquals("[9]", e4.getCollection().toString());
       
       //try with larger block size for more defrags
       assertTrue(e5.alloc(33, false)); //id # 1
       assertTrue(e5.alloc(67, false)); //id # 2
       assertTrue(e5.alloc(20, false)); //id # 3
       assertTrue(e5.alloc(50, false)); //id # 4
       assertTrue(e5.alloc(30, false)); //id # 5
       assertTrue(e5.alloc(10, false)); //id # 6
       assertTrue(e5.alloc(17, false)); //id # 7
       assertTrue(e5.alloc(13, false)); //id # 8
       assertTrue(e5.alloc(35, false)); //id # 9
       assertTrue(e5.alloc(25, false)); //id # 10
       assertFalse(e5.alloc(20, false)); //id # 11 Fails
       assertTrue(e5.dealloc(2));
       assertTrue(e5.dealloc(5));
       assertTrue(e5.dealloc(7));
       assertTrue(e5.dealloc(9));
       assertEquals("[33, 240, 210, 170]", e5.getCollection().toString());
       assertFalse(e5.alloc(69, false)); //id # 12 Fails DeFrag
       assertTrue(e5.dealloc(6));
       assertTrue(e5.dealloc(8));
       assertTrue(e5.dealloc(1));
       assertEquals("[33, 240, 0, 170, 200, 227, 210]", e5.getCollection().toString());
       assertTrue(e5.alloc(69, false)); //id # 13 Defrag Success
       //Chose the larger one than the smaller one at address zero
       assertEquals("[0, 239]", e5.getCollection().toString());
       
    }
  
    @Test
    public void testGrabToAlloc() {
        assertTrue(e5.alloc(33, false)); //id # 1
        assertTrue(e5.alloc(67, false)); //id # 2
        assertTrue(e5.alloc(20, false)); //id # 3
        assertTrue(e5.alloc(50, false)); //id # 4
        assertTrue(e5.alloc(30, false)); //id # 5
        assertTrue(e5.alloc(10, false)); //id # 6
        assertTrue(e5.alloc(17, false)); //id # 7
        assertTrue(e5.alloc(13, false)); //id # 8
        assertTrue(e5.alloc(35, false)); //id # 9
        assertTrue(e5.alloc(25, false)); //id # 10
        assertFalse(e5.alloc(20, false)); //id # 11 Fails
        assertTrue(e5.dealloc(2));
        assertTrue(e5.dealloc(5));
        assertTrue(e5.dealloc(7));
        assertTrue(e5.dealloc(9));
        assertEquals("[33, 240, 210, 170]", e5.getCollection().toString());
        assertFalse(e5.alloc(69, false)); //id # 12 Fails DeFrag
        assertTrue(e5.dealloc(6));
        assertTrue(e5.dealloc(8));
        assertTrue(e5.dealloc(1));
        assertEquals("[33, 240, 0, 170, 200, 227, 210]", e5.getCollection().toString());
        
        assertNull(e5.grabToAlloc(100));
        assertEquals(new MemBlock(33, 67, true), e5.grabToAlloc(45));
        assertNull(e5.grabToAlloc(39));
        assertEquals(new MemBlock(240, 35, true), e5.grabToAlloc(13));
        assertEquals(new MemBlock(0, 33, true), e5.grabToAlloc(27));
    }
    
    @Test
    public void testAddUnalloc() {
        
        for (int i=0; i < pray.length; i++) {
            e6.addUnalloc(pvals.get(i));
        }
        
        e6.addUnalloc(new MemBlock(55, 13, true));
        assertEquals("[55, 0, 42, 10, 33, 39, 17, 25]", e6.getCollection().toString());
        assertEquals(new MemBlock(55, 13, true), e6.grabToAlloc(13));
        e6.addUnalloc(new MemBlock(74, 8, true));
        assertEquals("[0, 74, 42, 10, 33, 39, 17, 25]", e6.getCollection().toString());
        
    }
    
    @Test
    public void testGetCollection() {
        
        for (int i=0; i < pray.length; i++) {
            e6.addUnalloc(pvals.get(i));
        }
        
        e6.addUnalloc(new MemBlock(55, 13, true));
        assertEquals("[55, 0, 42, 10, 33, 39, 17, 25]", e6.getCollection().toString());
        assertEquals(new MemBlock(55, 13, true), e6.grabToAlloc(13));
        e6.addUnalloc(new MemBlock(74, 8, true));
        assertEquals("[0, 74, 42, 10, 33, 39, 17, 25]", e6.getCollection().toString());
        assertEquals("[0]", e5.getCollection().toString());
        e5.addUnalloc(new MemBlock(300, 35, true));
        assertEquals("[0, 300]", e5.getCollection().toString());
        assertEquals(new MemBlock(0, 300, true), e5.grabToAlloc(13));
        assertEquals("[300]", e5.getCollection().toString());
    }
    
    @Test
    public void testRebuild() {
        
        assertEquals("[0]", e5.getCollection().toString());
        e5.addUnalloc(new MemBlock(300, 35, true));
        assertEquals("[0, 300]", e5.getCollection().toString());
        assertEquals(new MemBlock(0, 300, true), e5.grabToAlloc(13));
        assertEquals("[300]", e5.getCollection().toString());
        e5.addUnalloc(new MemBlock(335, 35, true));
        e5.addUnalloc(new MemBlock(370, 95, true));
        assertEquals("[370, 335, 300]", e5.getCollection().toString());
        e5.rebuild(pvals);
        assertEquals("[0, 10, 42, 25, 33, 39, 17]", e5.getCollection().toString());
    }
}




























    