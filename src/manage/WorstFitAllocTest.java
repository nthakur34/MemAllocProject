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
           assertTrue(e4.alloc(1, false));
           assertTrue(e4.alloc(9, false));
           assertFalse(e4.alloc(1, false));
           assertTrue(e4.dealloc(2));
           assertTrue(e4.alloc(2, false));
           assertTrue(e4.alloc(5, false));
           assertTrue(e4.alloc(1, false));
           assertTrue(e4.dealloc(1));
           assertTrue(e4.dealloc(3));
           assertTrue(e4.alloc(3, false));
    }
  
    @Test
    public void testRemove() {
    }
}
    