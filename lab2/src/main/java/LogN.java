public class LogN {

    LnFunction lnFunction;

    LogN() {
        lnFunction = new LnFunction();
    }

    public double logN(double x, int s) {
        return lnFunction.ln(x)/lnFunction.ln(s);
    }

}
