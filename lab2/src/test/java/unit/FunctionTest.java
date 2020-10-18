package unit;

import functions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        doThrow(new IllegalArgumentException()).when(ln).calc(0);
        doThrow(new IllegalArgumentException()).when(log2).calc(0);
        doThrow(new IllegalArgumentException()).when(log5).calc(0);

        when(log2.calc(0.21624849)).thenReturn(-2.209238036);
        when(log5.calc(0.21624849)).thenReturn(-0.951467033);
        when(ln.calc(0.21624849)).thenReturn(-1.5313271);

        when(log2.calc(0.21624849 + delta)).thenReturn(-2.2092314);
        when(log5.calc(0.21624849 + delta)).thenReturn(-0.9514642);
        when(ln.calc(0.21624849 + delta)).thenReturn(-1.53132249);

        when(log2.calc(0.21624849 - delta)).thenReturn(-2.2092447);
        when(log5.calc(0.21624849 - delta)).thenReturn(-0.9514699);
        when(ln.calc(0.21624849 - delta)).thenReturn(-1.5313317);

        when(log2.calc(1)).thenReturn(0.0);
        when(log5.calc(1)).thenReturn(0.0);
        when(ln.calc(1)).thenReturn(0.0);

        when(log2.calc(1 + delta)).thenReturn(0.0000014469432);
        when(log5.calc(1 + delta)).thenReturn(0.0000006213352);
        when(ln.calc(1 + delta)).thenReturn(0.0000009999995);

        when(log2.calc(1 - delta)).thenReturn(-0.0000014469432);
        when(log5.calc(1 - delta)).thenReturn(-0.0000006213352);
        when(ln.calc(1 - delta)).thenReturn(-0.0000009999995);

        when(log2.calc(5.179158818534)).thenReturn(2.3727178);
        when(log5.calc(5.179158818534)).thenReturn(1.0218739);
        when(ln.calc(5.179158818534)).thenReturn(1.6446427);

        when(log2.calc(5.179158818534 + delta)).thenReturn(2.3727181);
        when(log5.calc(5.179158818534 + delta)).thenReturn(1.0218741);
        when(ln.calc(5.179158818534 + delta)).thenReturn(1.6446428);

        when(log2.calc(5.179158818534 - delta)).thenReturn(2.3727175);
        when(log5.calc(5.179158818534 - delta)).thenReturn(1.0218738);
        when(ln.calc(5.179158818534 - delta)).thenReturn(1.6446425);

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
