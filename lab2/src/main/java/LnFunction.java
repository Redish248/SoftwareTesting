public class LnFunction extends AbstractFunction{

    public double ln(double x) {
        x-=1;
        if (x < 0) {
            throw new IllegalArgumentException();
        }

        return countFunction(x, 1);

    }

    public double nextTailor(double x, double n) {
        return Math.pow(-1, n-1) * Math.pow(x, n) / n;
    }

}
