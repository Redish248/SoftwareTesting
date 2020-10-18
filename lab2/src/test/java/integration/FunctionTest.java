package integration;

import functions.*;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

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
}
