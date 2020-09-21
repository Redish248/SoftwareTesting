package task1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TgTest {

    private final double DELTA = 1E-6;
    FunctionTg functionTg;

    @Before
    public void testInit() {
        functionTg = new FunctionTg();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgPiAndHalf() {
        assertEquals(Math.tan(Math.PI/2), functionTg.calculateTg(Math.PI/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgMinusPiAndHalf() {
        assertEquals(Math.tan(-Math.PI/2), functionTg.calculateTg(-Math.PI/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgPiAndHalfEps2() {
        assertEquals(Math.tan(Math.PI/2 + DELTA/2), functionTg.calculateTg(Math.PI/2+DELTA/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgMinusPiAndHalfEps2() {
        assertEquals(Math.tan(-Math.PI/2 + DELTA/2), functionTg.calculateTg(-Math.PI/2+DELTA/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgPiAndEps() {
        assertEquals(Math.tan(Math.PI/2 - 1E-7), functionTg.calculateTg(Math.PI/2 - 1E-7), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgPiAndHalfEps() {
        assertEquals(Math.tan(Math.PI/2 + DELTA), functionTg.calculateTg(Math.PI/2 + DELTA), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgMinusPiAndHalfEps() {
        assertEquals(Math.tan(-Math.PI/2 + DELTA), functionTg.calculateTg(-Math.PI/2 + DELTA), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgMinusPiMinusHalfEps() {
        assertEquals(Math.tan(-Math.PI/2 - DELTA), functionTg.calculateTg(-Math.PI/2 - DELTA), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTg3PiAndHalf() {
        assertEquals(Math.tan(3*Math.PI/2), functionTg.calculateTg(3*Math.PI/2), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinusTg3PiAndHalf() {
        assertEquals(Math.tan(-3*Math.PI/2), functionTg.calculateTg(-3*Math.PI/2), DELTA);
    }

    @Test
    public void testTgZero() {
        assertEquals(Math.tan(0), functionTg.calculateTg(0), DELTA);
    }

    @Test
    public void testTgPi() {
        assertEquals(Math.tan(Math.PI), functionTg.calculateTg(Math.PI), DELTA);
    }

    @Test
    public void testTgMinusPi() {
        assertEquals(Math.tan(-Math.PI), functionTg.calculateTg(-Math.PI), DELTA);
    }

    @Test
    public void testTgPiAndQuote() {
        assertEquals(Math.tan(Math.PI/4), functionTg.calculateTg(Math.PI/4), DELTA);
    }

    @Test
    public void testTgMinusPiAndQuote() {
        assertEquals(Math.tan(-Math.PI/4), functionTg.calculateTg(-Math.PI/4), DELTA);
    }

    @Test
    public void testTgPiDel3() {
        assertEquals(Math.tan(Math.PI/3), functionTg.calculateTg(Math.PI/3), DELTA);
    }

    @Test
    public void testTgMinusPiDel3() {
        assertEquals(Math.tan(-Math.PI/3), functionTg.calculateTg(-Math.PI/3), DELTA);
    }

    @Test
    public void testTgPiDel6() {
        assertEquals(Math.tan(Math.PI/6), functionTg.calculateTg(Math.PI/6), DELTA);
    }

    @Test
    public void testTgMinusPiDel6() {
        assertEquals(Math.tan(-Math.PI/6), functionTg.calculateTg(-Math.PI/6), DELTA);
    }

    @Test
    public void testTg2Pi() {
        assertEquals(Math.tan(2*Math.PI), functionTg.calculateTg(2*Math.PI), DELTA);
    }

    @Test
    public void testMinusTg2Pi() {
        assertEquals(Math.tan(-2*Math.PI), functionTg.calculateTg(-2*Math.PI), DELTA);
    }

    @Test
    public void testPeriod() {
        assertEquals(functionTg.calculateTg(Math.PI/3), functionTg.calculateTg(Math.PI/3 + Math.PI), DELTA);
        assertEquals(functionTg.calculateTg(Math.PI/3), functionTg.calculateTg(Math.PI/3 + 2*Math.PI), DELTA);
        assertEquals(functionTg.calculateTg(Math.PI/3), functionTg.calculateTg(Math.PI/3 + 3*Math.PI), DELTA);
        assertEquals(functionTg.calculateTg(-Math.PI/3), functionTg.calculateTg(-Math.PI/3 - Math.PI), DELTA);
        assertEquals(functionTg.calculateTg(Math.PI/4), functionTg.calculateTg(Math.PI/4 - 2*Math.PI), DELTA);
        assertEquals(functionTg.calculateTg(Math.PI/4), functionTg.calculateTg(Math.PI/4 - 3*Math.PI), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgInfinityPlus() {
        assertEquals(Math.tan(Double.POSITIVE_INFINITY), functionTg.calculateTg(Double.POSITIVE_INFINITY), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgInfinityMinus() {
        assertEquals(Math.tan(Double.NEGATIVE_INFINITY), functionTg.calculateTg(Double.NEGATIVE_INFINITY), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTgNan() {
        assertEquals(Math.tan(Double.NaN), functionTg.calculateTg(Double.NaN), DELTA);
    }

}
