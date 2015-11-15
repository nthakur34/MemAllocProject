/**
 * Nitin, Jonathan
 */

package manage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemBlockTest {
    
    MemBlock testBlock;
    
    @Before
    public void setup() {
        // make a new large free block, like the one that will start
        // the program
        // address at 0
        // size at 50
        // free is true
        testBlock = new MemBlock(0, 50, true); 
    }
    
    @Test
    public void testGetRightAdjacent() {
        assertEquals(testBlock.getRightAdjacent(), 50);
    }
    
    @Test
    public void testAllocate() {
        // test first good case
        // give an allocation of 10
        MemBlock newBlock = testBlock.allocate(10);
        // result should be 2 blocks:
        // Initial block: 10 start address, 40 size, true free
        assertEquals(testBlock.getStartAddress(), 10);
        assertEquals(testBlock.getSize(), 40);
        assertEquals(testBlock.isFree(), true);
        // Second block: 0 start address, 10 size, false free
        assertEquals(newBlock.getStartAddress(), 0);
        assertEquals(newBlock.getSize(), 10);
        assertEquals(newBlock.isFree(), false);
        
        // now run another allocate
        // on newly formatted old block
        // starts at 10
        // has size 40
        // give an allocation of 10
        newBlock = testBlock.allocate(13);
        // result should be 2 blocks:
        // Initial block: 23 start address, 27 size, true free
        assertEquals(testBlock.getStartAddress(), 23);
        assertEquals(testBlock.getSize(), 27);
        assertEquals(testBlock.isFree(), true);
        // Second block: 10 start address, 23 size, false free
        assertEquals(newBlock.getStartAddress(), 10);
        assertEquals(newBlock.getSize(), 13);
        assertEquals(newBlock.isFree(), false);
        
        // next bad cases
        // = 0
        newBlock = testBlock.allocate(0);
        assertNull(newBlock);
        // < 0
        newBlock = testBlock.allocate(-1);
        assertNull(newBlock);
        // > size
        newBlock = testBlock.allocate(50);
        assertNull(newBlock);
    }
    
    @Test
    public void testCombineData() {
        // new block
        // address 50
        // size 20
        // is free
        MemBlock newBlock = new MemBlock(50, 20, true);
        
        // first check good cases
        testBlock.combineData(newBlock);
        assertEquals(testBlock.getSize(), 70);
        
        // next 2 bad cases
        // first, not adjacent
        this.setup();
        newBlock = new MemBlock(51, 20, true);
        // second, one is not empty
        this.setup();
        newBlock = new MemBlock(50, 20, false);
    }

}
