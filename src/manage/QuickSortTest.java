package manage;

/*
 * Navjyoth Thakur - nthakur2 -  600.226.01 - P4
 * Nitin Kumar
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickSortTest {
    
    static QuickSort<Integer> e4;  
    static QuickSort<Integer> e5; 
    static QuickSort<Integer> e6;
    static QuickSort<Integer> e7; 
    static QuickSort<Integer> all;

    // note - Integer hashCode() returns the int value
    static Integer[] iray = {11, 3, 7, 9, 5, 3, 9, 10, 1, 2};
    static Integer[] pray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static Integer[] tray = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static ArrayList<Integer> svals;
    static ArrayList<Integer> pvals;
    static ArrayList<Integer> tvals;
    static ArrayList<Integer> evals;

    @BeforeClass
    public static void init() {
        svals = new ArrayList<Integer>();
        for (Integer val: iray) {
            svals.add(val);
        } 
        pvals = new ArrayList<Integer>();
        for (Integer val: pray) {
            pvals.add(val);
        } 
        tvals = new ArrayList<Integer>();
        for (Integer val: tray) {
            tvals.add(val);
        }
        evals = new ArrayList<Integer>();
    }

    
    @Before
    public void setup() {
        // these start out empty before each test
        e4 = new QuickSort<Integer>(svals);
        e4.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        e5 = new QuickSort<Integer>(pvals);
        e5.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        e6 = new QuickSort<Integer>(tvals);
        e6.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        e7 = new QuickSort<Integer>(evals);
        e7.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
        all = new QuickSort<Integer>(null); 
        all.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s1.compareTo(s2);
            }
        });
    }
    
    @Test
    public void testSortedList() {
        //sorted by size
        
        assertEquals("[1, 2, 3, 3, 5, 7, 9, 9, 10, 11]", e4.getList().toString());
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", e5.getList().toString());
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", e6.getList().toString());
        assertEquals("[]", all.getList().toString());
        assertEquals("[]", e7.getList().toString());
    }

}
