public class Log5 {

    LnFunction lnFunction;

    Log5(LnFunction function) {
        lnFunction = function;
    }

    public double log5(double x) {
        return lnFunction.ln(x)/lnFunction.ln(5);
    }

}
