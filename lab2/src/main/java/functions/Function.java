package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Function implements SystemModule {
    private final Sinus sin;
    private final Tangens tan;
    private final LnFunction ln;
    private final Log5 log5;
    private final Log2 log2;

    @Override
    public String getModuleName() {
        return "Function";
    }

    public double calc(double x) {
        if (x <= 0) {
            return Math.pow((Math.pow((tan.calc(x) - sin.calc(x)) * tan.calc(x), 3) - sin.calc(x)), 3);
        } else {
            return ((((Math.pow(log5.calc(x), 2)) + log5.calc(x)) - ln.calc(x)) - ((ln.calc(x) / ln.calc(x)) / log2.calc(x)))
                    / log5.calc(x);
        }
    }
}
