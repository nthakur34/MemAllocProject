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


public class BaseManagerTest {

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
        assertEquals("[null]", e4.getAllocMem().toString());
       assertEquals(0, e4.alloc(1, false));   //ID # 1
       assertEquals(1, e4.alloc(9, false));   //ID # 2
       //check allocated
       assertEquals("[null, 0, 1]", e4.getAllocMem().toString());
       assertEquals("[]", e4.getCollection().toString());
       //fails since all filles, but retains ID #
       assertEquals(-1, e4.alloc(1, false));  //ID # 3
       assertEquals("[null, 0, 1, null]", e4.getAllocMem().toString());
       //free the large 9 block
       assertEquals(new MemBlock(1, 9, true), e4.dealloc(2));
       assertEquals(1, e4.alloc(2, false));   //ID # 4
       assertEquals(3, e4.alloc(5, false));   //ID # 5
       assertEquals(8, e4.alloc(1, false));   //ID # 6
       
       assertEquals(new MemBlock(0, 1, true), e4.dealloc(1));
       assertEquals("[9, 0]", e4.getCollection().toString());
       assertEquals(new MemBlock(1, 2, true), e4.dealloc(4));
       assertEquals("[1, 0, 9]", e4.getCollection().toString());
       assertEquals("[null, null, null, null, null, 3, 8]", e4.getAllocMem().toString());
       assertEquals(0, e4.alloc(3, false));
       //since, defrags, and 0 and 1 adjacent, combine, then used
       assertEquals("[9]", e4.getCollection().toString());
       
       //try with larger block size for more defrags
       assertEquals(0, e5.alloc(33, false)); //id # 1
       assertEquals(33, e5.alloc(67, false)); //id # 2
       assertEquals(100, e5.alloc(20, false)); //id # 3
       assertEquals(120, e5.alloc(50, false)); //id # 4
       assertEquals(170, e5.alloc(30, false)); //id # 5
       assertEquals(200, e5.alloc(10, false)); //id # 6
       assertEquals(210, e5.alloc(17, false)); //id # 7
       assertEquals(227, e5.alloc(13, false)); //id # 8
       assertEquals(240, e5.alloc(35, false)); //id # 9
       assertEquals(275, e5.alloc(25, false)); //id # 10
       assertEquals(-1, e5.alloc(20, false)); //id # 11 Fails
       assertEquals(new MemBlock(33, 67, true), 
               e5.dealloc(2));
       assertEquals(new MemBlock(170, 30, true), e5.dealloc(5));
       assertEquals(new MemBlock(210, 17, true), e5.dealloc(7));
       assertEquals(new MemBlock(240, 35, true), e5.dealloc(9));
       assertEquals("[33, 240, 210, 170]", e5.getCollection().toString());
       //check in allocated array
       assertEquals("[null, 0, null, 100, 120, null, 200, null, 227, null, 275, null]", e5.getAllocMem().toString());
       assertEquals(-1, e5.alloc(69, false)); //id # 12 Fails DeFrag
       assertEquals("[null, 0, null, 100, 120, null, 200, null, 227, null, 275, null, null]", e5.getAllocMem().toString());
       assertEquals(new MemBlock(200, 10, true), e5.dealloc(6));
       assertEquals(new MemBlock(227, 13, true), e5.dealloc(8));
       assertEquals(new MemBlock(0, 33, true), e5.dealloc(1));
       assertEquals("[33, 240, 0, 170, 200, 227, 210]", e5.getCollection().toString());
       assertEquals(170, e5.alloc(69, false)); //id # 13 Defrag Success
       //Chose the larger one than the smaller one at address zero
       assertEquals("[0, 239]", e5.getCollection().toString());
       assertEquals(-1, e5.alloc(0, false));
       
    }
  
    @Test
    public void testDefrag() {
        assertEquals(null, e5.dealloc(1));
        assertEquals(0, e5.alloc(33, false)); //id # 1
        assertEquals(33, e5.alloc(67, false)); //id # 2
        assertEquals(100, e5.alloc(20, false)); //id # 3
        assertEquals(120, e5.alloc(50, false)); //id # 4
        assertEquals(170, e5.alloc(30, false)); //id # 5
        assertEquals(200, e5.alloc(10, false)); //id # 6
        assertEquals(210, e5.alloc(17, false)); //id # 7
        assertEquals(227, e5.alloc(13, false)); //id # 8
        assertEquals(240, e5.alloc(35, false)); //id # 9
        assertEquals(275, e5.alloc(25, false)); //id # 10
        assertEquals(-1, e5.alloc(20, false)); //id # 11 Fails
        //dealloc when full
        assertEquals(new MemBlock(33, 67, true), e5.dealloc(2));
        //dealloc when not full
        assertEquals(new MemBlock(120, 50, true), e5.dealloc(4));
        assertEquals(new MemBlock(200, 10, true), e5.dealloc(6));
        assertEquals(new MemBlock(240, 35, true), e5.dealloc(9));
        assertEquals("[33, 120, 200, 240]", e5.getCollection().toString());
        e5.defrag();
        assertEquals("[33, 120, 200, 240]", e5.getCollection().toString());
        assertEquals(new MemBlock(100, 20, true), e5.dealloc(3));
        assertEquals(new MemBlock(227, 13, true), e5.dealloc(8));
        assertEquals("[33, 120, 227, 240, 100, 200]", e5.getCollection().toString());
        e5.defrag();
        assertEquals("[33, 200, 227]", e5.getCollection().toString());
        e5.defrag();
        assertEquals("[33, 200, 227]", e5.getCollection().toString());
    }
    
    @Test
    public void testdealloc() {
        //dealloc when empty
        assertEquals(null, e5.dealloc(1));
        assertEquals("[null]", e5.getAllocMem().toString());
        assertEquals(0, e5.alloc(33, false)); //id # 1
        assertEquals(33, e5.alloc(67, false)); //id # 2
        assertEquals(100, e5.alloc(20, false)); //id # 3
        assertEquals(120, e5.alloc(50, false)); //id # 4
        assertEquals(170, e5.alloc(30, false)); //id # 5
        assertEquals(200, e5.alloc(10, false)); //id # 6
        assertEquals(210, e5.alloc(17, false)); //id # 7
        assertEquals(227, e5.alloc(13, false)); //id # 8
        assertEquals(240, e5.alloc(35, false)); //id # 9
        assertEquals(275, e5.alloc(25, false)); //id # 10
        assertEquals(-1, e5.alloc(20, false)); //id # 11 Fails
        //allocatted values
        assertEquals("[null, 0, 33, 100, 120, 170, 200, 210, 227, 240, 275, null]", e5.getAllocMem().toString());
        //dealloc when full
        assertEquals(new MemBlock(33, 67, true), e5.dealloc(2));
        //dealloc when not full
        assertEquals(new MemBlock(120, 50, true), e5.dealloc(4));
        assertEquals(new MemBlock(200, 10, true), e5.dealloc(6));
        assertEquals(new MemBlock(240, 35, true), e5.dealloc(9));
        assertEquals("[33, 120, 200, 240]", e5.getCollection().toString());
        //allocated values
        assertEquals("[null, 0, null, 100, null, 170, null, 210, 227, null, 275, null]", e5.getAllocMem().toString());

    }
    

}
