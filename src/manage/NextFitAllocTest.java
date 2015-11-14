package manage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NextFitAllocTest {

    public NextFitAllocTest() {
        // TODO Auto-generated constructor stub
    }
    
    BaseManager baseManager;
    ArrayList<MemBlock> testBlocks;
    
    @Before
    public void setup() {
        baseManager = new NextFitAlloc(100);
        testBlocks = new ArrayList<MemBlock>();
        for (int i = 0; i < 100; i += 10) {
            testBlocks.add(new MemBlock(i, 10, true));
        }
    }
    
    @Test
    public void testRebuild() {
        // first try with filled array
        baseManager.rebuild(testBlocks);
        assertEquals(testBlocks, baseManager.getCollection());
        
        // next try with an empty one
        testBlocks = new ArrayList<MemBlock>();
        baseManager.rebuild(testBlocks);
        assertTrue(baseManager.getCollection().isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddUnalloc() {
        // first try with filled array
        baseManager = new NextFitAlloc(110);
        baseManager.rebuild(testBlocks);
        baseManager.addUnalloc(new MemBlock(100, 10, true));
        testBlocks.add(new MemBlock(100, 10, true));
        assertEquals(testBlocks, baseManager.getCollection());
        
        // try with empty array
        baseManager = new NextFitAlloc(10);
        testBlocks = new ArrayList<MemBlock>();
        baseManager.rebuild(testBlocks);
        baseManager.addUnalloc(new MemBlock(0, 10, true));
        testBlocks.add(new MemBlock(0, 10, true));
        assertEquals(testBlocks, baseManager.getCollection());
        
        // try with adding block that lies out of range
        // and with block that is null
        // look for fail lines in stderr printed below
        // and exception thrown
        // first null
        baseManager.addUnalloc(null);
        // above is good
        baseManager.addUnalloc(new MemBlock(0, 11, true));
        // above is too, exception was caught
        
    }
    
    @Test
    public void testGrabToAlloc() {
        /* should never be passed bad size param
         * because alloc should handle
         * tests to run:
         * - Potential fit is in middle of queue
         * - Exact fit is in middle of queue
         * - Potential fit is last possible check
         * - there is no potential fit
         * - grab from empty list
         */
        
        // Potential fit is in middle of queue
        baseManager.grabToAlloc(38);
        // see if basemanager no longer has 40 block and instead
        // 
        testBlocks = new ArrayList<MemBlock>();
        for (int i = 0; i < 50; i += 10) {
            testBlocks.add(new MemBlock(i, 10, true));
        }        
        for (int i = 50; i < 74; i += 12) {
            testBlocks.add(new MemBlock(i, 12, true));
        }
        for (int i = 74; i < 94; i += 20) {
            testBlocks.add(new MemBlock(i, 20, true));
        }
        baseManager.rebuild(testBlocks);
        // grab a block of 11 size
        MemBlock grabbed = baseManager.grabToAlloc(11);
        assertEquals(grabbed.getStartAddress(), 50);
        assertEquals(grabbed.getSize(), 12);
        System.out.println(baseManager.getCollection());
        // test case 2- exact size
        grabbed = baseManager.grabToAlloc(12);
        assertEquals(grabbed.getStartAddress(), 62);
        assertEquals(grabbed.getSize(), 12);
        System.out.println(baseManager.getCollection());
        // test case 4- no potential matches
        grabbed = baseManager.grabToAlloc(25);
        assertNull(grabbed);
        // test case 3- potential match at end of list
        // re-initialize list
        baseManager.rebuild(testBlocks);
        grabbed = baseManager.grabToAlloc(19);
        assertEquals(grabbed.getStartAddress(), 74);
        assertEquals(grabbed.getSize(), 20);
        // check empty list returns null
        baseManager.rebuild(new ArrayList<MemBlock>());
        assertNull(baseManager.grabToAlloc(1));
        
    }

}
