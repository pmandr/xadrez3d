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
public class RookTest {
    
    public RookTest() {
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
     * Test of possibleMoves method, of class Rook.
     */
    @Test
    public void testPossibleMoves1() throws InvalidSquareException {
        System.out.println("possibleMoves: Rook in the center");
        Square pos = new Square("d4");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("a4"));
        expResult.add(new Square("b4"));
        expResult.add(new Square("c4"));
        expResult.add(new Square("e4"));
        expResult.add(new Square("f4"));
        expResult.add(new Square("g4"));
        expResult.add(new Square("h4"));
        expResult.add(new Square("d1"));
        expResult.add(new Square("d2"));
        expResult.add(new Square("d3"));
        expResult.add(new Square("d5"));
        expResult.add(new Square("d6"));
        expResult.add(new Square("d7"));
        expResult.add(new Square("d8"));
        List<Square> result = Rook.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.get(2).getPos(), result.get(2).getPos());
        assertEquals(expResult.get(3).getPos(), result.get(3).getPos());
        assertEquals(expResult.get(4).getPos(), result.get(4).getPos());
        assertEquals(expResult.get(5).getPos(), result.get(5).getPos());
        assertEquals(expResult.get(6).getPos(), result.get(6).getPos());
        assertEquals(expResult.get(7).getPos(), result.get(7).getPos());
        assertEquals(expResult.get(8).getPos(), result.get(8).getPos());
        assertEquals(expResult.get(9).getPos(), result.get(9).getPos());
        assertEquals(expResult.get(10).getPos(), result.get(10).getPos());
        assertEquals(expResult.get(11).getPos(), result.get(11).getPos());
        assertEquals(expResult.get(12).getPos(), result.get(12).getPos());
        assertEquals(expResult.get(13).getPos(), result.get(13).getPos());
        assertEquals(expResult.size(), result.size());

    }
    
    /**
     * Test of possibleMoves method, of class Rook.
     */
    @Test
    public void testPossibleMoves2() throws InvalidSquareException {
        System.out.println("possibleMoves: Rook in the corner");
        Square pos = new Square("h1");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("a1"));
        expResult.add(new Square("b1"));
        expResult.add(new Square("c1"));
        expResult.add(new Square("d1"));
        expResult.add(new Square("e1"));
        expResult.add(new Square("f1"));
        expResult.add(new Square("g1"));
        expResult.add(new Square("h2"));
        expResult.add(new Square("h3"));
        expResult.add(new Square("h4"));
        expResult.add(new Square("h5"));
        expResult.add(new Square("h6"));
        expResult.add(new Square("h7"));
        expResult.add(new Square("h8"));
        List<Square> result = Rook.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.get(2).getPos(), result.get(2).getPos());
        assertEquals(expResult.get(3).getPos(), result.get(3).getPos());
        assertEquals(expResult.get(4).getPos(), result.get(4).getPos());
        assertEquals(expResult.get(5).getPos(), result.get(5).getPos());
        assertEquals(expResult.get(6).getPos(), result.get(6).getPos());
        assertEquals(expResult.get(7).getPos(), result.get(7).getPos());
        assertEquals(expResult.get(8).getPos(), result.get(8).getPos());
        assertEquals(expResult.get(9).getPos(), result.get(9).getPos());
        assertEquals(expResult.get(10).getPos(), result.get(10).getPos());
        assertEquals(expResult.get(11).getPos(), result.get(11).getPos());
        assertEquals(expResult.get(12).getPos(), result.get(12).getPos());
        assertEquals(expResult.get(13).getPos(), result.get(13).getPos());
        assertEquals(expResult.size(), result.size());

    }
}
