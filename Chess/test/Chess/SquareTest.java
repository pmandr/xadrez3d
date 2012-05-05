/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

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
    public void SquareGetXCheck() {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        assertEquals(0, s1.getX());
        assertEquals(7, s2.getX());
        assertEquals(0, s3.getX());
        assertEquals(7, s4.getX());
    }
    
    @Test
    public void SquareGetYCheck() {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        assertEquals(0, s1.getY());
        assertEquals(4, s2.getY());
        assertEquals(5, s3.getY());
        assertEquals(7, s4.getY());

    }
    
    @Test
    public void SquareGetPosCheck() {
        Square s1 = new Square("a1");
        Square s2 = new Square("h5");
        Square s3 = new Square("66");
        Square s4 = new Square("{{");
        assertEquals("a1", s1.getPos());
        assertEquals("h5", s2.getPos());
        assertEquals("a6", s3.getPos());
        assertEquals("h8", s4.getPos());

    }
}
