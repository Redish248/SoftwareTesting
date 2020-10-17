import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModuleTest {

    Sinus sinus;
    LnFunction lnFunction;
    TrigonometricFunction function;
    LogN logN;

    @Before
    public void init() {
        sinus = new Sinus();
        lnFunction = new LnFunction();
        function = new TrigonometricFunction();
        logN = new LogN();
    }

    @Test
    public void testSinus() {
        assertEquals(Math.sin(Math.PI/2), sinus.sin(Math.PI/2), 10E-6);
    }

    //тест валиться, но так и надо
    @Test
    public void testCosinus() {
        assertEquals(Math.cos(Math.PI/2), function.cos(Math.PI/2), 10E-6);
    }

    @Test
    public void testTg() {
        assertEquals(Math.tan(Math.PI/4), function.tg(Math.PI/4), 10E-6);
    }

    @Test
    public void testLn() {
        assertEquals(Math.log(2), lnFunction.ln(2), 10E-6);
    }


    @Test
    public void testLn2() {
        assertEquals(Math.log(4)/Math.log(2), logN.log2(4), 10E-6);
    }

}
