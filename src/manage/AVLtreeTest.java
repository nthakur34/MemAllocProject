package manage;
/* Navjyoth Thakur
 * 600.226.01
 * nthakur2
 * p4
 */


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

public class AVLtreeTest {

    static AVLtree<Integer> e4; 
    static AVLtree<Integer> e7; 
    static AVLtree<Integer> e10;
    static AVLtree<Integer> all;  // all in map

    // note - Integer hashCode() returns the int value
    static Integer[] iray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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
        e4 = new AVLtree<Integer>();  
        e7 = new AVLtree<Integer>(); 
        e10 = new AVLtree<Integer>(); 

        // this is full set, assuming put works correctly
        all = new AVLtree<Integer>();
        for (int i=0; i < iray.length; i++) {
            all.add(svals.get(i));
        }
    }

    @Test
    public void testInit() {
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", svals.toString());
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", all.toString());
        // won't necessarily be in this order
        // assertEquals("[3=tre, 6=six, 0=zro, 1=one, 10=ten, 5=fyv, 8=ate, 9=nyn, 4=for, 7=svn, 2=two]", entries.toString());
    }


    @Test
    public void testEmptyTree() {
        assertFalse(e4.contains(1));
        assertTrue(all.contains(1));
        assertTrue(e4.isEmpty());
        assertFalse(all.isEmpty());
    }

    @Test
    public void testAdd() {
        System.out.println("\nINSIDE TESTADD");
        assertEquals(11, all.size());
        assertTrue(e4.add(5));
        assertTrue(e4.contains(5));
        System.out.println("root: " + e4.root());
        assertTrue(e4.size() == 1);
        assertTrue(e4.add(3));
        assertTrue(e4.contains(3));
        assertTrue(e4.size() == 2);
        System.out.println("pre order: " + e4.preOrder().toString());
        assertEquals(e4.preOrder().toString(), "[5, 3]");
        System.out.println("pre order: " + e4.postOrder().toString());
        assertEquals(e4.postOrder().toString(), "[3, 5]");
        assertEquals(e4.inOrder().toString(), "[3, 5]");
        assertTrue(e4.add(10));
        assertTrue(e4.contains(10));
        assertTrue(e4.size() == 3);
        System.out.println("preorder: " + e4.preOrder().toString());

        assertEquals(e4.preOrder().toString(), "[5, 3, 10]");
        assertEquals(e4.postOrder().toString(), "[3, 10, 5]");
        assertEquals(e4.inOrder().toString(), "[3, 5, 10]");
        e4.add(7);

        e4.add(13);
        assertEquals(e4.preOrder().toString(), "[5, 3, 10, 7, 13]");
        assertEquals(e4.postOrder().toString(), "[3, 7, 13, 10, 5]");
        assertEquals(e4.inOrder().toString(), "[3, 5, 7, 10, 13]");
        e4.add(15);  // right-right path too long
        assertTrue(e4.size() == 6);
        System.out.println("preorder: " + e4.preOrder().toString());
        System.out.println("inorder: " + e4.inOrder().toString());

        assertEquals(e4.preOrder().toString(), "[10, 5, 3, 7, 13, 15]");
        assertEquals(e4.postOrder().toString(), "[3, 7, 5, 15, 13, 10]");
        assertEquals(e4.inOrder().toString(), "[3, 5, 7, 10, 13, 15]");
        
        assertTrue(e4.remove(15));
        assertTrue(e4.size() == 5);
        assertEquals(e4.preOrder().toString(), "[10, 5, 3, 7, 13]");
        assertEquals(e4.postOrder().toString(), "[3, 7, 5, 13, 10]");
        assertEquals(e4.inOrder().toString(), "[3, 5, 7, 10, 13]");
        
        assertTrue(e4.remove(13));  // 
        assertTrue(e4.size() == 4);
        
        e7.add(10);
        e7.add(5);
        e7.add(20);
        e7.add(3);
        e7.add(7);
        e7.add(15);
        e7.add(25);
        e7.add(8);
        e7.add(22);
        e7.add(23);
        // ooooh weee 
        System.out.println("e7.preOrder: " + e7.preOrder().toString());

        System.out.println("e7.inOrder: " + e7.inOrder().toString());
        assertEquals(e7.preOrder().toString(), "[10, 5, 3, 7, 8, 20, 15, 23, 22, 25]");
        assertEquals(e7.postOrder().toString(), "[3, 8, 7, 5, 15, 22, 25, 23, 20, 10]");
        assertEquals(e7.inOrder().toString(), "[3, 5, 7, 8, 10, 15, 20, 22, 23, 25]");
        System.out.println("RIGHT HERE");
        assertTrue(e7.remove(10));
//        assertEquals(e7.preOrder().toString(), "[10, 5, 3, 7, 8, 20, 15, 23, 22, 25]");
//        assertEquals(e7.postOrder().toString(), "[3, 8, 7, 5, 15, 22, 25, 23, 20, 10]");
//        assertEquals(e7.inOrder().toString(), "[3, 5, 7, 8, 10, 15, 20, 22, 23, 25]");
//        
//        e10.add(13);
//        e10.add(7);
//        e10.add(15);
//        e10.add(5);
//        e10.add(10);
//        e10.add(20);
//        e10.add(3);
//        e10.add(11);
//        
//        System.out.println("e10 root: " + e10.root());
//        System.out.println("preorder: " + e10.preOrder().toString());
//        assertEquals(e10.preOrder().toString(), "[13, 7, 5, 3, 10, 11, 15, 20]");
//        assertEquals(e10.postOrder().toString(), "[3, 5, 11, 10, 7, 20, 15, 13]");
//        assertEquals(e10.inOrder().toString(), "[3, 5, 7, 10, 11, 13, 15, 20]");
//        
//        assertFalse(e10.remove(1));
//        assertTrue(e10.remove(20));
//        assertEquals(e10.preOrder().toString(), "[7, 5, 3, 13, 10, 11, 15]");
//        assertEquals(e10.postOrder().toString(), "[3, 5, 11, 10, 15, 13, 7]");
//        assertEquals(e10.inOrder().toString(), "[3, 5, 7, 10, 11, 13, 15]");
//        
        
        
//        
//        System.out.println("e4: " + e4.toString());
//        
        
//        assertTrue(all.add(11));
//        //Use traversal to see if last one is 11. 
//        Iterator<Integer> iter = all.preOrder().iterator();
//        int temp = 0;
//        while(iter.hasNext()) {
//        	temp = iter.next();
//        	System.out.print(temp + "   ");
//        }
//        System.out.println("");
//        assertEquals(11, temp);
//        System.out.println("before remove 7: " + all.toString());
//        assertTrue(all.remove(7));
//        System.out.println("after remove 7: " + all.toString());
//        assertFalse(all.contains(7)); 
//        assertEquals("[0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11]", all.toString());

    }
    
//    @Test
//    public void testRemove() {
//        assertEquals(11, all.size());
//        assertTrue(all.remove((Integer) 5));
//        assertFalse(all.contains((Integer) 5));
//        assertTrue(all.size() == 10);
//        assertTrue(e4.add((Integer) 6));
//        assertTrue(e4.contains((Integer) 6));
//        assertTrue(e4.remove((Integer) 6));
//        assertTrue(e4.isEmpty());
//        assertTrue(all.add((Integer) 11));
//        //Use traversal to see if last one is 11. 
//        Iterator<Integer> iter = all.preOrder().iterator();
//        int temp = 0;
//        while(iter.hasNext()) {
//        	temp = iter.next();
//        	System.out.print(temp + "   ");
//        }
//        System.out.println("");
//        assertEquals(11, temp);
//        System.out.println(all.toString());
//        assertTrue(all.remove((Integer) 7));
//        assertFalse(all.contains((Integer) 7)); 
//        assertEquals("[0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11]", all.toString());
//
//    }

}




