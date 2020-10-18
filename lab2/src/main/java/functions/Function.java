package functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Function {
    private final Sinus sin;
    private final Tangens tan;
    private final LnFunction ln;
    private final Log5 log5;
    private final Log2 log2;

    public double calc(double x) {
        if (x <= 0) {
            return (((((tan.calc(x) - sin.calc(x)) * Math.pow(tan.calc(x), 3))) - Math.pow(sin.calc(x), 3)));
        } else {
            return ((((Math.pow(log5.log5(x), 2)) + log5.log5(x)) - ln.ln(x)) - ((ln.ln(x) / ln.ln(x)) / log2.log2(x)))
                    / log5.log5(x);
        }
    }
}
