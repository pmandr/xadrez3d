/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Lobosque
 */
public class SquareTest {
    
    public SquareTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    

    @Test
    public void SquareGetXCheck() throws InvalidSquareException {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        try {
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        fail("Expected exception not thrown");
        }
        catch(InvalidSquareException e) {
  
        }
        assertEquals(0, s1.getX());
        assertEquals(7, s2.getX());

    }
    
    @Test
    public void SquareGetYCheck() throws InvalidSquareException {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        try {
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        fail("Expected exception not thrown");
        }
        catch(InvalidSquareException e) {
  
        }
        assertEquals(0, s1.getY());
        assertEquals(4, s2.getY());

    }
    
    @Test
    public void SquareGetPosCheck() throws InvalidSquareException {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        try {
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        fail("Expected exception not thrown");
        }
        catch(InvalidSquareException e) {
  
        }
        assertEquals("a1", s1.getPos());
        assertEquals("h5", s2.getPos());

    }
}
