public class LnFunction extends AbstractFunction{

    public double ln(double x) {
        x-=1;
        if (x > 1 || x <=-1) {
            throw new IllegalArgumentException();
        }

        return countFunction(x);

    }

    public double nextTailor(double x, double n) {
        return Math.pow(-1, n+1) * Math.pow(x, n) / countFactorial(n);
    }

}
