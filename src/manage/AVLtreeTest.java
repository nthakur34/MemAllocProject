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

public class AVLtreeTest {

    static AVLtree<Integer> e4; 
    static AVLtree<Integer> e7; 
    static AVLtree<Integer> e10;
    static AVLtree<Integer> t1;

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
        t1 = new AVLtree<Integer>(); 


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
        assertEquals(11, all.size());
        assertTrue(e4.add(5));
        assertTrue(e4.contains(5));
        assertTrue(e4.size() == 1);
        assertTrue(e4.add(3));
        assertTrue(e4.contains(3));
        assertTrue(e4.size() == 2);
        assertEquals(e4.preOrder().toString(), "[5, 3]");
        assertEquals(e4.postOrder().toString(), "[3, 5]");
        assertEquals(e4.inOrder().toString(), "[3, 5]");
        assertTrue(e4.add(10));
        assertTrue(e4.contains(10));
        assertTrue(e4.size() == 3);

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
        
        
        
        AVLtree<Integer> ass = new AVLtree<>();
        
        ass.add(15);
        assertEquals(ass.getBestFit(11), ass.root());
        ass.remove(15);
        assertNull(ass.getBestFit(26));
        assertNull(ass.getBestFit(50));
        ass.add(25);
        assertEquals(ass.getBestFit(25), ass.root());
        assertEquals(ass.getBestFit(24), ass.root());
        ass.remove(25);
        ass.add(20);
        assertEquals(ass.getBestFit(17), ass.root());
        ass.remove(20);
        ass.add(7);
        assertEquals(ass.getBestFit(6), ass.root());
        ass.remove(7);
        ass.add(5);
        assertEquals(ass.getBestFit(4), ass.root());
        ass.remove(5);
        assertEquals(ass.size(), 0);

        t1.add(1);
       
        t1.add(2);
        
        t1.add(3);
        
        
        
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
    
    @Test
    public void testRemove() {
        
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

        assertEquals(e7.preOrder().toString(), "[10, 5, 3, 7, 8, 20, 15, 23, 22, 25]");
        assertEquals(e7.postOrder().toString(), "[3, 8, 7, 5, 15, 22, 25, 23, 20, 10]");
        assertEquals(e7.inOrder().toString(), "[3, 5, 7, 8, 10, 15, 20, 22, 23, 25]");
        
        assertTrue(e7.remove(10));
        assertEquals(e7.preOrder().toString(), "[15, 5, 3, 7, 8, 23, 20, 22, 25]");
        assertEquals(e7.postOrder().toString(), "[3, 8, 7, 5, 22, 20, 25, 23, 15]");
        assertEquals(e7.inOrder().toString(), "[3, 5, 7, 8, 15, 20, 22, 23, 25]");
        assertTrue(e7.remove(23));
        assertTrue(e7.size() == 8);
        assertEquals(e7.preOrder().toString(), "[15, 5, 3, 7, 8, 22, 20, 25]");
        assertEquals(e7.postOrder().toString(), "[3, 8, 7, 5, 20, 25, 22, 15]");
        assertEquals(e7.inOrder().toString(), "[3, 5, 7, 8, 15, 20, 22, 25]");
        assertTrue(e7.remove(3));
        assertTrue(e7.size() == 7);
        assertEquals(e7.preOrder().toString(), "[15, 7, 5, 8, 22, 20, 25]");
        assertEquals(e7.postOrder().toString(), "[5, 8, 7, 20, 25, 22, 15]");
        assertEquals(e7.inOrder().toString(), "[5, 7, 8, 15, 20, 22, 25]");
        assertTrue(e7.remove(15));
        assertTrue(e7.size() == 6);
        assertEquals(e7.preOrder().toString(), "[20, 7, 5, 8, 22, 25]");
        assertEquals(e7.postOrder().toString(), "[5, 8, 7, 25, 22, 20]");
        assertEquals(e7.inOrder().toString(), "[5, 7, 8, 20, 22, 25]");
        assertTrue(e7.remove(25));
        assertTrue(e7.size() == 5);
        assertEquals(e7.preOrder().toString(), "[20, 7, 5, 8, 22]");
        assertEquals(e7.postOrder().toString(), "[5, 8, 7, 22, 20]");
        assertEquals(e7.inOrder().toString(), "[5, 7, 8, 20, 22]");
        assertTrue(e7.remove(22));
        assertTrue(e7.size() == 4);
        assertEquals(e7.preOrder().toString(), "[7, 5, 20, 8]");
        assertEquals(e7.postOrder().toString(), "[5, 8, 20, 7]");
        assertEquals(e7.inOrder().toString(), "[5, 7, 8, 20]");
        assertTrue(e7.remove(20));
        assertTrue(e7.size() == 3);
        assertEquals(e7.preOrder().toString(), "[7, 5, 8]");
        assertEquals(e7.postOrder().toString(), "[5, 8, 7]");
        assertEquals(e7.inOrder().toString(), "[5, 7, 8]");
        assertFalse(e7.remove(20));
        assertTrue(e7.remove(7));
        assertTrue(e7.size() == 2);
        assertEquals(e7.preOrder().toString(), "[8, 5]");
        assertEquals(e7.postOrder().toString(), "[5, 8]");
        assertEquals(e7.inOrder().toString(), "[5, 8]");
        assertTrue(e7.remove(8));
        assertTrue(e7.size() == 1);
        assertEquals(e7.preOrder().toString(), "[5]");
        assertEquals(e7.postOrder().toString(), "[5]");
        assertEquals(e7.inOrder().toString(), "[5]");
        assertTrue(e7.remove(5));
        assertTrue(e7.size() == 0);
        assertEquals(e7.preOrder().toString(), "[]");
        assertEquals(e7.postOrder().toString(), "[]");
        assertEquals(e7.inOrder().toString(), "[]");
        assertTrue(e7.isEmpty());
        
        
        
        
        
        
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

    }
    
    @Test
    public void testGetBestFit() {
        e10.add(13);
        e10.add(7);
        e10.add(15);
        e10.add(5);
        e10.add(10);
        e10.add(20);
        e10.add(3);
        e10.add(11);
        
        assertEquals(e10.preOrder().toString(), "[13, 7, 5, 3, 10, 11, 15, 20]");
        assertEquals(e10.postOrder().toString(), "[3, 5, 11, 10, 7, 20, 15, 13]");
        assertEquals(e10.inOrder().toString(), "[3, 5, 7, 10, 11, 13, 15, 20]");
        
        assertFalse(e10.remove(1));
        assertTrue(e10.remove(20));
        assertEquals(e10.preOrder().toString(), "[7, 5, 3, 13, 10, 11, 15]");
        assertEquals(e10.postOrder().toString(), "[3, 5, 11, 10, 15, 13, 7]");
        assertEquals(e10.inOrder().toString(), "[3, 5, 7, 10, 11, 13, 15]");
        AVLtree<Integer> e11 = new AVLtree<>();
        e11.add(13);
        assertEquals(e10.getBestFit(13), e11.root());
        e11.remove(13);
        e11.add(7);
        assertEquals(e10.getBestFit(7), e11.root());
        e11.remove(7);
        e11.add(5);
        assertEquals(e10.getBestFit(4), e11.root());
        e11.remove(5);
        e11.add(3);
        assertEquals(e10.getBestFit(1), e11.root());
        e11.remove(3);
        e11.add(5);
        assertEquals(e10.getBestFit(4), e11.root());
        e11.remove(5);
        e11.add(10);
        assertEquals(e10.getBestFit(9), e11.root());
        e11.remove(10);
        e11.add(13);
        assertEquals(e10.getBestFit(12), e11.root());
        e11.remove(13);
        e11.add(15);
        assertEquals(e10.getBestFit(14), e11.root());
    }
    
    @Test
    public void testSmallMethods() {
        // this method tests isEmpty, root, getMaxVal, getBestFit, and contains
        assertTrue(t1.isEmpty());
        t1.add(50);
        assertTrue(t1.root() == 50);
        assertEquals((int) t1.getMaxVal(), 50);
        assertFalse(t1.isEmpty());
        assertEquals((int) t1.getBestFit(40), 50);
        assertNull(t1.getBestFit(60));
        assertEquals((int) t1.getBestFit(50), 50);
        assertTrue(t1.contains(50));
        assertFalse(t1.contains(49));
        t1.add(72);
        assertTrue(t1.root() == 50);
        assertEquals((int) t1.getMaxVal(), 72);
        assertFalse(t1.isEmpty());
        assertEquals((int) t1.getBestFit(50), 50);
        assertEquals((int) t1.getBestFit(72), 72);
        assertNull(t1.getBestFit(73));
        assertEquals((int) t1.getBestFit(1), 50);
        assertTrue(t1.contains(72));
        t1.add(23);
        t1.add(54);
        t1.add(17);
        t1.add(12);
        assertEquals(t1.preOrder().toString(), "[50, 17, 12, 23, 72, 54]");
        assertEquals(t1.postOrder().toString(), "[12, 23, 17, 54, 72, 50]");
        assertEquals(t1.inOrder().toString(), "[12, 17, 23, 50, 54, 72]");
        assertEquals((int) t1.getMaxVal(), 72);
        assertEquals((int) t1.getBestFit(24), 50);
        assertEquals((int) t1.getBestFit(2), 12);
        assertEquals((int) t1.getBestFit(52), 54);
        assertEquals((int) t1.getBestFit(16), 17);
        assertEquals((int) t1.getBestFit(20), 23);
        assertNull(t1.getBestFit(80));
    }

}




