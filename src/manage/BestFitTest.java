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
    public void allocTest() {
        assertTrue(bf1.alloc(1, false));
        System.out.println(bf1.getCollection().toString());
        System.out.println("RIGHT FUCKING HERE");
        assertTrue(bf1.alloc(9, false));
        assertEquals("[]", bf1.getCollection().toString());
        //fails since all full, but retains ID #
        assertFalse(bf1.alloc(1, false));
        //free the large 9 block
        assertTrue(bf1.dealloc(2));
        assertTrue(bf1.alloc(2, false));
        assertTrue(bf1.alloc(5, false));
        assertTrue(bf1.alloc(1, false));
        assertTrue(bf1.dealloc(1));
        assertEquals("[9, 0]", bf1.getCollection().toString());
        assertTrue(bf1.dealloc(4));
        assertEquals("[1, 0, 9]", bf1.getCollection().toString());
        assertTrue(bf1.alloc(3, false));
        //since, defrags, and 0 and 1 adjacent, combine, then used
        assertEquals("[9]", bf1.getCollection().toString());
    }
}
