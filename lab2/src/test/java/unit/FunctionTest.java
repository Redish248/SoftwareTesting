package unit;

import functions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionTest {
    private Function function;
    private final double delta = 1E-6;

    @BeforeEach
    public void mockFunctions() {
        Sinus sinus = mock(Sinus.class);
        Tangens tangens = mock(Tangens.class);
        LnFunction ln = mock(LnFunction.class);
        Log2 log2 = mock(Log2.class);
        Log5 log5 = mock(Log5.class);

        //x <= 0
        when(sinus.calc(0)).thenReturn(Math.sin(0));
        when(tangens.calc(0)).thenReturn(Math.tan(0));

        when(sinus.calc(-0.8)).thenReturn(Math.sin(-0.8));
        when(tangens.calc(-0.8)).thenReturn(Math.tan(-0.8));

        when(sinus.calc(-2.6)).thenReturn(Math.sin(-2.6));
        when(tangens.calc(-2.6)).thenReturn(Math.tan(-2.6));

        when(sinus.calc(-Math.PI)).thenReturn(Math.sin(-Math.PI));
        when(tangens.calc(-Math.PI)).thenReturn(Math.tan(-Math.PI));

        when(sinus.calc(-3.566 + delta)).thenReturn(Math.sin(-3.566 + delta));
        when(tangens.calc(-3.566 + delta)).thenReturn(Math.tan(-3.566 + delta));

        when(sinus.calc(-3.566)).thenReturn(Math.sin(-3.566));
        when(tangens.calc(-3.566)).thenReturn(Math.tan(-3.566));

        when(sinus.calc(-3.566 - delta)).thenReturn(Math.sin(-3.566 - delta));
        when(tangens.calc(-3.566 - delta)).thenReturn(Math.tan(-3.566 - delta));

        when(sinus.calc(-3.732)).thenReturn(Math.sin(-3.732));
        when(tangens.calc(-3.732)).thenReturn(Math.tan(-3.732));

        when(sinus.calc(-5.311)).thenReturn(Math.sin(-5.311));
        when(tangens.calc(-5.311)).thenReturn(Math.tan(-5.311));

        when(sinus.calc(-5.478 + delta)).thenReturn(Math.sin(-5.478 + delta));
        when(tangens.calc(-5.478 + delta)).thenReturn(Math.tan(-5.478 + delta));

        when(sinus.calc(-5.478)).thenReturn(Math.sin(-5.478));
        when(tangens.calc(-5.478)).thenReturn(Math.tan(-5.478));

        when(sinus.calc(-5.478 - delta)).thenReturn(Math.sin(-5.478 - delta));
        when(tangens.calc(-5.478 - delta)).thenReturn(Math.tan(-5.478 - delta));

        when(sinus.calc(-2*Math.PI)).thenReturn(Math.sin(-2*Math.PI));
        when(tangens.calc(-2*Math.PI)).thenReturn(Math.tan(-2*Math.PI));

        when(sinus.calc(-Math.PI/2)).thenReturn(Math.sin(-Math.PI/2));
        when(tangens.calc(-Math.PI/2)).thenReturn(Math.tan(-Math.PI/2));

        when(sinus.calc(-3*Math.PI/2)).thenReturn(Math.sin(-3*Math.PI/2));
        when(tangens.calc(-3*Math.PI/2)).thenReturn(Math.tan(-3*Math.PI/2));

        when(sinus.calc(-2)).thenReturn(Math.sin(-2));
        when(tangens.calc(-2)).thenReturn(Math.tan(-2));

        when(sinus.calc(-4)).thenReturn(Math.sin(-4));
        when(tangens.calc(-4)).thenReturn(Math.tan(-4));

        when(sinus.calc(-5)).thenReturn(Math.sin(-5));
        when(tangens.calc(-5)).thenReturn(Math.tan(-5));

        //x > 0
        doThrow(new IllegalArgumentException()).when(ln).ln(0);
        doThrow(new IllegalArgumentException()).when(log2).log2(0);
        doThrow(new IllegalArgumentException()).when(log5).log5(0);

        when(log2.log2(0.21624849)).thenReturn(log2(0.21624849));
        when(log5.log5(0.21624849)).thenReturn(log5(0.21624849));
        when(ln.ln(0.21624849)).thenReturn(Math.log(0.21624849));

        when(log2.log2(0.21624849 + delta)).thenReturn(log2(0.21624849 + delta));
        when(log5.log5(0.21624849 + delta)).thenReturn(log5(0.21624849 + delta));
        when(ln.ln(0.21624849 + delta)).thenReturn(Math.log(0.21624849 + delta));

        when(log2.log2(0.21624849 - delta)).thenReturn(log2(0.21624849 - delta));
        when(log5.log5(0.21624849 - delta)).thenReturn(log5(0.21624849 - delta));
        when(ln.ln(0.21624849 - delta)).thenReturn(Math.log(0.21624849 - delta));

        when(log2.log2(1)).thenReturn(log2(1));
        when(log5.log5(1)).thenReturn(log5(1));
        when(ln.ln(1)).thenReturn(Math.log(1));

        when(log2.log2(1 + delta)).thenReturn(log2(1 + delta));
        when(log5.log5(1 + delta)).thenReturn(log5(1 + delta));
        when(ln.ln(1 + delta)).thenReturn(Math.log(1 + delta));

        when(log2.log2(1 - delta)).thenReturn(log2(1 - delta));
        when(log5.log5(1 - delta)).thenReturn(log5(1 - delta));
        when(ln.ln(1 - delta)).thenReturn(Math.log(1 - delta));

        when(log2.log2(5.179158818534)).thenReturn(log2(5.179158818534));
        when(log5.log5(5.179158818534)).thenReturn(log5(5.179158818534));
        when(ln.ln(5.179158818534)).thenReturn(Math.log(5.179158818534));

        when(log2.log2(5.179158818534 + delta)).thenReturn(log2(5.179158818534 + delta));
        when(log5.log5(5.179158818534 + delta)).thenReturn(log5(5.179158818534 + delta));
        when(ln.ln(5.179158818534 + delta)).thenReturn(Math.log(5.179158818534 + delta));

        when(log2.log2(5.179158818534 - delta)).thenReturn(log2(5.179158818534 - delta));
        when(log5.log5(5.179158818534 - delta)).thenReturn(log5(5.179158818534 - delta));
        when(ln.ln(5.179158818534 - delta)).thenReturn(Math.log(5.179158818534 - delta));

        function = new Function(sinus, tangens, ln, log5, log2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -0.8, -2.6, -Math.PI, -3.566 + 1E-6, -3.566, -3.566 - 1E-6, -3.732, -5.311,
            -5.478 + 1E-6, -5.478, -5.478 - 1E-6, -2*Math.PI, -Math.PI/2, -3*Math.PI/2, -2, -4, -5})
    public void testTrigonometry(double x) {
        assertEquals(Math.pow((Math.pow((Math.tan(x) - Math.sin(x)) * Math.tan(x), 3) - Math.sin(x)), 3),
                function.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.21624849, 0.21624849 - delta, 0.21624849 + delta, 5.179158818534 - delta, 5.179158818534, 5.179158818534 + delta})
    public void testLogs(double x) {
        assertEquals(calcFunctionWithLog(x),
                function.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 - delta, 1, 1 + delta})
    public void testLogsException(double x) {
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
