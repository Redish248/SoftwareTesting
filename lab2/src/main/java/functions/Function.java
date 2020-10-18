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
            return ((((Math.pow(log5.log5(x), 2)) + log5.log5(x)) - ln.ln(x)) - ((ln.ln(x) / ln.ln(x)) / log2.log2(x)))
                    / log5.log5(x);
        }
    }
}
