package integration;

import functions.Function;
import functions.LnFunction;
import functions.Log2;
import functions.Log5;
import functions.Sinus;
import functions.Tangens;

import static org.mockito.Mockito.mock;

public class SinTest {

    private final Sinus sinus = new Sinus();
    private final LnFunction lnFunction = mock(LnFunction.class);
    private final Tangens tangens = mock(Tangens.class);
    private final Log5 log5 = mock(Log5.class);
    private final Log2 log2 = mock(Log2.class);
    private final Function function = new Function(sinus, tangens, lnFunction, log5, log2);

}
