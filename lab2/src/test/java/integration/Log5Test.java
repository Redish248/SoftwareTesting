package integration;

import functions.Function;
import functions.LnFunction;
import functions.Log2;
import functions.Log5;
import functions.Sinus;
import functions.Tangens;

import static org.mockito.Mockito.mock;

public class Log5Test {
    private final Sinus sinus = mock(Sinus.class);
    private final LnFunction lnFunction = mock(LnFunction.class);
    private final Tangens tangens = mock(Tangens.class);
    private final Log2 log2 = mock(Log2.class);
    private final Log5 log5 = new Log5(lnFunction);
    private final Function function = new Function(sinus, tangens, lnFunction, log5, log2);


}
