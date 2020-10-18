package unit;

import functions.*;
import org.junit.jupiter.api.BeforeEach;

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

        //todo: моки, определить точки

        function = new Function(sinus, tangens, ln, log5, log2);
    }
}
