public class Sinus extends AbstractFunction {

    public double sin(double x) {
        x = checkX(x);

        if (x < 0) {
            return -sin(Math.abs(x));
        }

        return countFunction(x);

    }


    public double checkX(double x) {
        if (Double.isNaN(x) || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException();
        }

        if (x > 0 && (x > Math.PI/2)) {
            do {
                x -= Math.PI;
            } while (x >= Math.PI/2);
        } else if (x < 0 && (x < -Math.PI/2)) {
            do {
                x += Math.PI;
            } while (x <= -Math.PI/2);
        }

        return x;
    }

    public double nextTailor(double x, double n) {
        return Math.pow(-1, n+1) * Math.pow(x, 2*n-1) / countFactorial(2*n-1);
    }

}
