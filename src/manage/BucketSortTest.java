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

public class BucketSortTest {

    BucketSort<Integer> testSort;
    
    @Test
    public void indexTest() {
        // test with arbitrary values
        List<Integer> array = new ArrayList<Integer>();
        // {4, 23, 22, 1, 0, 12, 2, 10}
        int arr[] = {0, 1, 2, 4, 10, 12, 22, 23};
        // {0, 1, 2, 3, 5, 6}
        // size = 30
        array.add(4);
        array.add(23);
        array.add(22);
        array.add(1);
        array.add(0);
        array.add(12);
        array.add(2);
        array.add(10);
        // fsdsfdsdfdsf
        testSort = new BucketSort<Integer>(30);
        testSort.sort(array);
        ArrayList<Integer> output = testSort.getData(); 
        assertFalse(output.isEmpty());
        for (int i = 0; i < output.size(); i++) {
            assertEquals((Integer) output.get(i), (Integer) arr[i]);
        }
        System.out.println(output);
        
        // test with empty arr 
        array = new ArrayList<Integer>();
        arr = new int[0];
        // sort vals
        testSort = new BucketSort<Integer>(30);
        testSort.sort(array);
        output = testSort.getData(); 
        assertTrue(output.isEmpty());
        System.out.println(output);
        
        // test with memblocks!
        // test genericness
        List<MemBlock> memArray = new ArrayList<MemBlock>();
        memArray.add(new MemBlock(0, 10, true));
        memArray.add(new MemBlock(22, 5, true));
        memArray.add(new MemBlock(10, 12, true));
        MemBlock memArr[] = {new MemBlock(0, 10, true), new MemBlock(10, 12, true), new MemBlock(22, 5, true)};
        // sort vals
        BucketSort<MemBlock> memTestSort = new BucketSort<MemBlock>(30);
        memTestSort.sort(memArray);
        ArrayList<MemBlock> memOutput = memTestSort.getData(); 
        assertFalse(memOutput.isEmpty());
        for (int i = 0; i < memOutput.size(); i++) {
            assertEquals(memOutput.get(i), (MemBlock) memArr[i]);
        }
        System.out.println(output);
    }
    
}
