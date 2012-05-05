/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.exception.InvalidSquareException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Lobosque
 */
public class KnightTest {
    
    public KnightTest() {
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

    /**
     * Test of possibleMoves method, of class Knight.
     */
    @Test
    public void testPossibleMoves1() throws InvalidSquareException {
        System.out.println("possibleMoves: Knight in the center");
        Square pos = new Square("d4");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("b3"));
        expResult.add(new Square("b5"));
        expResult.add(new Square("c2"));
        expResult.add(new Square("c6"));
        expResult.add(new Square("e2"));
        expResult.add(new Square("e6"));
        expResult.add(new Square("f3"));
        expResult.add(new Square("f5"));
        List<Square> result = Knight.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.get(2).getPos(), result.get(2).getPos());
        assertEquals(expResult.get(3).getPos(), result.get(3).getPos());
        assertEquals(expResult.get(4).getPos(), result.get(4).getPos());
        assertEquals(expResult.get(5).getPos(), result.get(5).getPos());
        assertEquals(expResult.get(6).getPos(), result.get(6).getPos());
        assertEquals(expResult.get(7).getPos(), result.get(7).getPos());
        assertEquals(expResult.size(), result.size());
    }
    
    /**
     * Test of possibleMoves method, of class Knight.
     */
    @Test
    public void testPossibleMoves2() throws InvalidSquareException {
        System.out.println("possibleMoves: Knight in the corner");
        Square pos = new Square("h1");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("f2"));
        expResult.add(new Square("g3"));
        List<Square> result = Knight.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.size(), result.size());

    }
}
