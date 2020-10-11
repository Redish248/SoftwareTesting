import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModuleTest {

    Sinus sinus;
    LnFunction lnFunction;

    @Before
    public void init() {
        sinus = new Sinus();
        lnFunction = new LnFunction();
    }

    @Test
    public void testSinus() {
        assertEquals(Math.sin(Math.PI/3), sinus.sin(Math.PI/3), 10E-6);
    }

    @Test
    public void testLn() {
        assertEquals(Math.log(0.4), lnFunction.ln(0.4), 10E-6);
    }

}
