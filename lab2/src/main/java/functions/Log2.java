package functions;

public class Log2 {

    LnFunction lnFunction;

    public Log2(LnFunction function) {
        lnFunction = function;
    }

    public double log2(double x) {
        return lnFunction.ln(x)/lnFunction.ln(2);
    }

}
