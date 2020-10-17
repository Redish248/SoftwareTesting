package unit;

import functions.Sinus;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class SinTest {
    private final Sinus sinus;

    public SinTest() {
        sinus = new Sinus();
    }

    @Test
    public void test() {
        assertEquals(0, sinus.sin(0), 10E-6);
    }
}
