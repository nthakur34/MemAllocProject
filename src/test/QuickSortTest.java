package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.Defrag;
import manage.MemBlock;
import manage.QuickSort;

public class QuickSortTest {
    
    static QuickSort<Integer> e4;  
    static QuickSort<Integer> all;
    private Comparator<MemBlock> comparator;

    // note - Integer hashCode() returns the int value
    static Integer[] iray = {11, 3, 7, 9, 5, 3, 9, 10, 1, 2};
    static ArrayList<Integer> svals;

    @BeforeClass
    public static void init() {
        svals = new ArrayList<Integer>();
        for (Integer val: iray) {
            svals.add(val);
        } 
    }
    
    @Before
    public void setup() {
        // these start out empty before each test
        this.comparator = new Me.MemBlockComparator();
        e4 = new QuickSort<Integer>(svals, comparator);  
        all = new QuickSort<Integer>(null, comparator);  
    }
    
    @Test
    public void testSortedList() {
        //sorted by size
        assertEquals("[0, 10, 17, 25, 33, 39, 42]", e4.toString());
        assertEquals("[]", all.toString());
    }

}
