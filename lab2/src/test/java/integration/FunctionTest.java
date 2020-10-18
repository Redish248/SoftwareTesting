package integration;

import functions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionTest {
    private Function function;
    private final double delta = 1E-5;

    @BeforeEach
    public void mockFunctions() {
        Sinus sinus = new Sinus();
        Cosine cosine = new Cosine(sinus);
        Tangens tangens = new Tangens(sinus, cosine);
        LnFunction ln = new LnFunction();
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);

        function = new Function(sinus, tangens, ln, log5, log2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -0.8, -2.6, -Math.PI, -3.566, -3.732, -5.311, -5.478, -2*Math.PI, -Math.PI/2, -3*Math.PI/2, -2, -4, -5})
    public void testTrigonometry(double x) {
        assertEquals(Math.pow((Math.pow((Math.tan(x) - Math.sin(x)) * Math.tan(x), 3) - Math.sin(x)), 3),
                function.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.21624849, 0.21624849 - delta, 0.21624849 + delta, 1 - delta, 1, 1 + delta, 5.179158818534 - delta, 5.179158818534, 5.179158818534 + delta})
    public void testLogs(double x) {
        assertEquals(calcFunctionWithLog(x),
                function.calc(x), delta);
    }

    @Ignore
    private double calcFunctionWithLog(double x) {
        return (((((Math.pow(log5(x),2)) + log5(x)) - Math.log(x)) - ((Math.log(x) / Math.log(x)) / log2(x))) / log5(x));
    }

    @Ignore
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    @Ignore
    private double log5(double x) {
        return Math.log(x) / Math.log(5);
    }
}
