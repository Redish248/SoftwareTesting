public class LnFunction implements AbstractFunction{

    public double ln(double x) {
        x-=1;
        if (x > 1 || x <=-1) {
            throw new IllegalArgumentException();
        }

        return countFunction(x);

    }

    public double nextTailor(double x, double n) {
        return Math.pow(-1, n) * Math.pow(x, n+1) / (n+1);
    }


}
