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

public class BucketSorterTest {

    BucketSorter<Integer> testSort;
    
    @Test
    public void linkedListTest() {
        // test 
        List<Integer> array = new ArrayList<Integer>();
        // {0, 5, 3, 2, 6, 1}
        int arr[] = {0, 1, 2, 3, 5, 6};
        // {0, 1, 2, 3, 5, 6}
        // size = 6
        array.add(0);
        array.add(5);
        array.add(3);
        array.add(2);
        array.add(6);
        array.add(1);
        testSort = new BucketSorter<Integer>(6);
        LinkedList<Integer>[] output = testSort.sort(array, new Comparator<Integer>(){
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        assertTrue(output.length == 1);
        assertFalse(output[0].isEmpty());
        for (int i = 0; i < 6; i++) {
            assertEquals((Integer) output[0].get(i), (Integer) arr[i]);
        }
    }
    
    @Test
    public void indexTest() {
        // test 
        List<Integer> array = new ArrayList<Integer>();
        // {4, 23, 22, 1, 0, 12, 2, 10}
        int arr0[] = {0, 1, 2, 4};
        int arr1[] = {10, 12};
        int arr2[] = {22, 23};
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
        testSort = new BucketSorter<Integer>(30);
        LinkedList<Integer>[] output = testSort.sort(array, new Comparator<Integer>(){
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        assertTrue(output.length == 4);
        assertFalse(output[0].isEmpty());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < output[i].size(); j++) {
                if (i == 0) {
                    assertEquals((Integer) output[i].get(j), (Integer) arr0[j]);
                } else if (i == 1) {
                    assertEquals((Integer) output[i].get(j), (Integer) arr1[j]);
                } else if (i == 2) {
                    assertEquals((Integer) output[i].get(j), (Integer) arr2[j]);
                }
            }
        }
    }
    
}
