package manage;
/* Navjyoth Thakur
 * 600.226.01
 * nthakur2
 * p4

/** TESTS for 600.226 Fall 2015 Project 4
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;


public class WorstFitAllocTest {

    static WorstFitAlloc e4; 
    static WorstFitAlloc e5; 
    static WorstFitAlloc e6;  

    @Before
    public void setup() {
        
        e4 = new WorstFitAlloc(10);
        e5 = new WorstFitAlloc(300);
        e6 = new WorstFitAlloc(50);
    }

    @Test
    public void testAlloc() {
        System.out.println(e4.getCollection().toString());
           assertTrue(e4.alloc(1, false));
           System.out.println(e4.getCollection().toString());
           assertTrue(e4.alloc(9, false));
           System.out.println(e4.getCollection().toString());
           assertFalse(e4.alloc(1, false));
           assertTrue(e4.dealloc(2));
           System.out.println(e4.getCollection().toString());
           assertTrue(e4.alloc(2, false));
           System.out.println(e4.getCollection().toString());
           assertTrue(e4.alloc(5, false));
           System.out.println(e4.getCollection().toString());
           assertTrue(e4.alloc(1, false));
           System.out.println(e4.getCollection().toString());
           assertTrue(e4.dealloc(1));
           System.out.println(e4.getCollection().toString() + "   8");
           assertTrue(e4.dealloc(4));
           System.out.println(e4.getCollection().toString() + "   9");
           assertTrue(e4.alloc(3, false));
           System.out.println(e4.getCollection().toString() + "   10");
    }
  
    @Test
    public void testRemove() {
    }
}
    