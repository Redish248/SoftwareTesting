public class Log2 {

    LnFunction lnFunction;

    Log2(LnFunction function) {
        lnFunction = function;
    }

    public double log2(double x) {
        return lnFunction.ln(x)/lnFunction.ln(2);
    }

}
