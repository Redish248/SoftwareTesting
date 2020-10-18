package integration;

import functions.*;

import static org.mockito.Mockito.mock;

public class TgTest {

    private final Sinus sinus = mock(Sinus.class);
    private final LnFunction lnFunction = mock(LnFunction.class);
    //FIXME: sin and cos as params
    private final Tangens tangens = new Tangens(sinus, new Cosine(sinus));
    private final Log5 log5 = mock(Log5.class);
    private final Log2 log2 = mock(Log2.class);
    private final Function function = new Function(sinus, tangens, lnFunction, log5, log2);

}
