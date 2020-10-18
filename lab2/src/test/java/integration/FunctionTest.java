package integration;

import functions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    private Function function;
    private double delta;

    @BeforeEach
    public void mockFunctions() {
        delta = 1E-6;
        Sinus sinus = new Sinus();
        Cosine cosine = new Cosine(sinus);
        Tangens tangens = new Tangens(sinus, cosine);
        LnFunction ln = new LnFunction();
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);

        function = new Function(sinus, tangens, ln, log5, log2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -0.8, -2.6, -Math.PI, -3.566, -5.311, -5.478, -2*Math.PI})
    public void testTrigonometry(double x) {
        assertEquals(Math.pow((Math.pow((Math.tan(x) - Math.sin(x)) * Math.tan(x), 3) - Math.sin(x)), 3),
                function.calc(x), delta);
    }
}
