package weiss;

import static org.junit.Assert.*;
import org.junit.Test;

public class PathTest
{    
    @Test
    public void path()
    {
        Path path = new Path();
        assertTrue(path.isEmpty());
        assertEquals(0, path.size());
        
        path = path.leftOf();
        assertEquals(1, path.size());
        assertEquals(3, path.getCode());
        assertFalse(path.isEmpty());
        assertFalse(path.endsRight());
        
        path = path.leftOf();
        assertEquals(2, path.size());
        assertEquals(7, path.getCode());
        assertFalse(path.endsRight());
        
        path = path.rightOf();
        assertEquals(3, path.size());
        assertEquals(14, path.getCode());
        assertFalse(path.isEmpty());
        assertTrue(path.endsRight());
        
        path = path.back();
        assertEquals(2, path.size());
        assertEquals(7, path.getCode());
        
        path = path.back();
        assertEquals(3, path.getCode());
        
        path = path.back();
        assertTrue(path.isEmpty());
    }        

}