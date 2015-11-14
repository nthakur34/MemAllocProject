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
    
    @Before
    public void setup() {
        baseManager = new NextFitAlloc(500);
    }
    
    @Test
    public void testGrabToAlloc() {
        // first test normal constructor
        
    }

}
