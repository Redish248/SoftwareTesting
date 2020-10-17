public class Sinus extends AbstractFunction {

    public double sin(double x) {
        if (x < 0) {
            return -sin(Math.abs(x));
        }

        x = checkX(x);

        return countFunction(x, 0);

    }


    public double checkX(double x) {
        if (Double.isNaN(x) || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException();
        }

        double temp = Math.abs(x) % Math.PI;
        x = x >= 0 ? temp : -temp;

        return x;
    }

    public double nextTailor(double x, double n) {
        return Math.pow(-1, n) * Math.pow(x, 2*n+1) / countFactorial(2*n+1);
    }

}
