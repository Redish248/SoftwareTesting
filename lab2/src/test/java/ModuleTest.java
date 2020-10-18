import functions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    private final Sinus sinus = new Sinus();
    private final Cosine cosine = new Cosine(sinus);
    private final LnFunction lnFunction = new LnFunction();
    private final Tangens function = new Tangens(sinus, cosine);
    private final Log2 log2 = new Log2(lnFunction);
    private final Log5 log5 = new Log5(lnFunction);

    @Test
    public void testSinus() {
        assertEquals(Math.sin(Math.PI/2), sinus.calc(Math.PI/2), 10E-6);
    }

    //тест валится, но так и надо
    @Test
    public void testCosine() {
        assertEquals(Math.cos(Math.PI/2), cosine.calc(Math.PI/2), 10E-6);
    }

    @Test
    public void testTg() {
        assertEquals(Math.tan(Math.PI/4), function.calc(Math.PI/4), 10E-6);
    }

    @Test
    public void testLn() {
        assertEquals(Math.log(2), lnFunction.ln(2), 10E-6);
    }


    @Test
    public void testLn2() {
        assertEquals(Math.log(1)/Math.log(2), log2.log2(1), 10E-6);
    }

}
