public class LogN {

    LnFunction lnFunction;

    LogN() {
        lnFunction = new LnFunction();
    }

    public double log2(double x) {
        return logN(x, 2);
    }

    public double log5(double x) {
        return logN(x, 5);
    }

    public double logN(double x, int s) {
        return lnFunction.countFunction(x)/lnFunction.countFunction(s);
    }

}
