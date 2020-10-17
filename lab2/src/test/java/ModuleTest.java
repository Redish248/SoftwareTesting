import functions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    private final Sinus sinus = new Sinus();
    private final Cosine cosine = new Cosine();
    private final LnFunction lnFunction = new LnFunction();
    private final Tangens function = new Tangens();
    private final Log2 log2 = new Log2(lnFunction);
    private final Log5 log5 = new Log5(lnFunction);

    @Test
    public void testSinus() {
        assertEquals(Math.sin(Math.PI/2), sinus.sin(Math.PI/2), 10E-6);
    }

    //тест валится, но так и надо
    @Test
    public void testCosine() {
        assertEquals(Math.cos(Math.PI/2), cosine.cos(Math.PI/2), 10E-6);
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
        assertEquals(Math.log(4)/Math.log(2), log2.log2(4), 10E-6);
    }

}
