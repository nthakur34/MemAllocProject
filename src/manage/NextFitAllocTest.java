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
    
    @Test
    public void testAddUnalloc() {
        baseManager = new NextFitAlloc(110);
        // first try with filled array
        baseManager.rebuild(testBlocks);
        baseManager.addUnalloc(new MemBlock(100, 10, true));
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
         */
        
        // Potential fit is in middle of queue
        baseManager.addUnalloc(unAlloc);
        
    }

}
