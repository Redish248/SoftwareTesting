package unit;

import functions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionTest {
    private Function function;
    private double delta;

    @BeforeEach
    public void mockFunctions() {
        delta = 1E-6;
        Sinus sinus = mock(Sinus.class);
        Tangens tangens = mock(Tangens.class);
        LnFunction ln = mock(LnFunction.class);
        Log2 log2 = mock(Log2.class);
        Log5 log5 = mock(Log5.class);

        when(sinus.calc(0)).thenReturn(Math.sin(0));
        when(tangens.calc(0)).thenReturn(Math.tan(0));

        when(sinus.calc(-0.8)).thenReturn(Math.sin(-0.8));
        when(tangens.calc(-0.8)).thenReturn(Math.tan(-0.8));

        when(sinus.calc(-2.6)).thenReturn(Math.sin(-2.6));
        when(tangens.calc(-2.6)).thenReturn(Math.tan(-2.6));


        when(sinus.calc(-Math.PI)).thenReturn(Math.sin(-Math.PI));
        when(tangens.calc(-Math.PI)).thenReturn(Math.tan(-Math.PI));


        when(sinus.calc(-3.566)).thenReturn(Math.sin(-3.566));
        when(tangens.calc(-3.566)).thenReturn(Math.tan(-3.566));

        when(sinus.calc(-3.732)).thenReturn(Math.sin(-3.732));
        when(tangens.calc(-3.732)).thenReturn(Math.tan(-3.732));

        when(sinus.calc(-5.311)).thenReturn(Math.sin(-5.311));
        when(tangens.calc(-5.311)).thenReturn(Math.tan(-5.311));

        when(sinus.calc(-5.478)).thenReturn(Math.sin(-5.478));
        when(tangens.calc(-5.478)).thenReturn(Math.tan(-5.478));

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

        function = new Function(sinus, tangens, ln, log5, log2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -0.8, -2.6, -Math.PI, -3.566, -3.732, -5.311, -5.478, -2*Math.PI, -3*Math.PI/2, -2, -4, -5})
    public void testTrigonometry(double x) {
        assertEquals(Math.pow((Math.pow((Math.tan(x) - Math.sin(x)) * Math.tan(x), 3) - Math.sin(x)), 3),
                function.calc(x), delta);
    }
}
