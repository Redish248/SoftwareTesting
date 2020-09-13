package task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TgTest {

    private final double DELTA = 1E-6;

    @Test(expected = IllegalArgumentException.class)
    public void testTgPiAndHalf() {
        assertEquals(Math.tan(Math.PI/2), new FunctionTg().calculateTg(Math.PI/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTg3PiAndHalf() {
        assertEquals(Math.tan(3*Math.PI/2), new FunctionTg().calculateTg(3*Math.PI/2), DELTA);
    }

    @Test
    public void testTgZero() {
        assertEquals(Math.tan(0), new FunctionTg().calculateTg(0), DELTA);
    }

    @Test
    public void testTgPiAndQuote() {
        assertEquals(Math.tan(Math.PI/4), new FunctionTg().calculateTg(Math.PI/4), DELTA);
    }

    @Test
    public void testTgPiDel3() {
        assertEquals(Math.tan(Math.PI/3), new FunctionTg().calculateTg(Math.PI/3), DELTA);
    }

    @Test
    public void testTgPiDel6() {
        assertEquals(Math.tan(Math.PI/6), new FunctionTg().calculateTg(Math.PI/6), DELTA);
    }

    @Test
    public void testTgMinusPiDel6() {
        assertEquals(Math.tan(-Math.PI/6), new FunctionTg().calculateTg(-Math.PI/6), DELTA);
    }


    @Test
    public void testTg2Pi() {
        assertEquals(Math.tan(2*Math.PI), new FunctionTg().calculateTg(2*Math.PI), DELTA);
    }


}
