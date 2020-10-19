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
        when(sinus.calc(0)).thenReturn(0.0);
        when(tangens.calc(0)).thenReturn(0.0);

        when(sinus.calc(-0.8)).thenReturn(-0.7173560908995228);
        when(tangens.calc(-0.8)).thenReturn(-1.0296385570503641);

        when(sinus.calc(-2.6)).thenReturn(-0.5155013718214642);
        when(tangens.calc(-2.6)).thenReturn(0.6015966130897586);

        when(sinus.calc(-3.141592653589793)).thenReturn(-1.2246467991473532e-16);
        when(tangens.calc(-3.141592653589793)).thenReturn(1.2246467991473532e-16);

        when(sinus.calc(-3.5659989999999997)).thenReturn(0.4117798677049453);
        when(tangens.calc(-3.5659989999999997)).thenReturn(-0.45186809191281263);

        when(sinus.calc(-3.566)).thenReturn(0.4117807789880876);
        when(tangens.calc(-3.566)).thenReturn(-0.45186929609812937);

        when(sinus.calc(-3.566001)).thenReturn(0.4117816902708182);
        when(tangens.calc(-3.566001)).thenReturn(-0.45187050028453446);

        when(sinus.calc(-3.732)).thenReturn(0.5566994574473524);
        when(tangens.calc(-3.732)).thenReturn(-0.6701457684046108);

        when(sinus.calc(-5.311)).thenReturn(0.8261190958313546);
        when(tangens.calc(-5.311)).thenReturn(1.466061426402528);

        when(sinus.calc(-5.477999)).thenReturn(0.720959762087541);
        when(tangens.calc(-5.477999)).thenReturn(1.0403806157535207);

        when(sinus.calc(-5.478)).thenReturn(0.7209590691102533);
        when(tangens.calc(-5.478)).thenReturn(1.0403785333638613);

        when(sinus.calc(-5.478001)).thenReturn(0.7209583761322448);
        when(tangens.calc(-5.478001)).thenReturn(1.040376450978535);

        when(sinus.calc(-6.283185307179586)).thenReturn(2.4492935982947064e-16);
        when(tangens.calc(-6.283185307179586)).thenReturn(2.4492935982947064e-16);

        when(sinus.calc(-1.5707953267948966)).thenReturn(-0.9999999999995);
        when(tangens.calc(-1.5707953267948966)).thenReturn(-1000000.000020701);

        when(sinus.calc(-1.5707973267948965)).thenReturn(-0.9999999999995);
        when(tangens.calc(-1.5707973267948965)).thenReturn(1000000.0001431657);

        when(sinus.calc(-4.7123879803846895)).thenReturn(0.9999999999995);
        when(tangens.calc(-4.7123879803846895)).thenReturn(-999999.9996761917);

        when(sinus.calc(-4.71238998038469)).thenReturn(0.9999999999995);
        when(tangens.calc(-4.71238998038469)).thenReturn(1000000.0000435857);

        when(sinus.calc(-2)).thenReturn(-0.9092974268256817);
        when(tangens.calc(-2)).thenReturn(2.185039863261519);

        when(sinus.calc(-4)).thenReturn(0.7568024953079282);
        when(tangens.calc(-4)).thenReturn(-1.1578212823495777);

        when(sinus.calc(-5)).thenReturn(0.9589242746631385);
        when(tangens.calc(-5)).thenReturn(3.380515006246586);

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
    @ValueSource(doubles = {0, -0.8, -2.6, -3.141592653589793, -3.5659989999999997, -3.566, -3.566001, -3.732, -5.311,
            -5.477999, -5.478, -5.478001, -6.283185307179586, -1.5707953267948966, -1.5707973267948965,
            -4.7123879803846895, -4.71238998038469, -2, -4, -5})
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
    @ValueSource(doubles = {1 - 0.1, 1, 1 + 0.1})
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
