package functions;

public class LnFunction extends AbstractFunction implements SystemModule {

    public double calc(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException();
        }

        return countFunction(x, 1);

    }

    public double nextTailor(double x, double n) {
        if (x <= 2) {
            x-=1;
            return Math.pow(-1, n - 1) * Math.pow(x, n) / n;
        } else {
            x = (x-1)/(x+1);
            return 2*Math.pow(x, 2*n-1)/(2*n-1);
        }
    }

    @Override
    public String getModuleName() {
        return "Ln";
    }

}
