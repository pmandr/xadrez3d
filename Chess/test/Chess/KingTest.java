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
public class KingTest {
    
    public KingTest() {
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
     * Test of possibleMoves method, of class King.
     */
    @Test
    public void testPossibleMoves1() throws InvalidSquareException {
        System.out.println("possibleMoves: King in the middle");
        Square pos = new Square("d4");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("c3"));
        expResult.add(new Square("c4"));
        expResult.add(new Square("c5"));
        expResult.add(new Square("d3"));
        expResult.add(new Square("d5"));
        expResult.add(new Square("e3"));
        expResult.add(new Square("e4"));
        expResult.add(new Square("e5"));
        List<Square> result = King.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.get(2).getPos(), result.get(2).getPos());
        assertEquals(expResult.get(3).getPos(), result.get(3).getPos());
        assertEquals(expResult.get(4).getPos(), result.get(4).getPos());
        assertEquals(expResult.get(5).getPos(), result.get(5).getPos());
        assertEquals(expResult.get(6).getPos(), result.get(6).getPos());
        assertEquals(expResult.size(), result.size());

    }
    
    
    /**
     * Test of possibleMoves method, of class King.
     */
    @Test
    public void testPossibleMoves2() throws InvalidSquareException {
        System.out.println("possibleMoves: King in the corner");
        Square pos = new Square("h1");
        List<Square> expResult = new ArrayList<Square>();
        expResult.add(new Square("g1"));
        expResult.add(new Square("g2"));
        expResult.add(new Square("h2"));
        List<Square> result = King.possibleMoves(pos);
        assertEquals(expResult.get(0).getPos(), result.get(0).getPos());
        assertEquals(expResult.get(1).getPos(), result.get(1).getPos());
        assertEquals(expResult.get(2).getPos(), result.get(2).getPos());
        assertEquals(expResult.size(), result.size());

    }
}
