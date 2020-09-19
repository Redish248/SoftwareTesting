package task1;

public class FunctionTg {

    private final double EPS = 1E-7;

    public static void main(String[] args) {
        new FunctionTg().calculateTg(Math.PI);
    }

    public double calculateTg(double x) {

        x = x % Math.PI;

        if (Math.abs(x) >= Math.PI/2 - 1E-6) {
            throw new IllegalArgumentException();
        }

        if (x < 0) {
            return -calculateTg(Math.abs(x));
        }

        double prevResult = 0;
        double result = getNextTailor(x,1);
        double i = 2;

        while (result - prevResult > EPS) {
            prevResult = result;
            result += getNextTailor(x, i);
            i++;
            System.out.println(i);
        }

        return result;
    }

    /**
     * Count next term in Taylor series
     * @param x value
     * @param n number of iteration
     * @return result
     */
    private double getNextTailor(double x, double n) {
        return countBernoulli(2*n)*Math.pow(-4,n)*(1-Math.pow(4,n))*Math.pow(x,2*n-1)/countFactorial(2*n);
    }

    /**
     * Count next Bernoulli value
     * @param n number of iteration
     * @return result
     */
    private double countBernoulli(double n) {

        if (n == 0) return 1;
        if (n != 1 && n % 2 != 0) return 0;

        double result = countBernoulli(n-1)*countC(2, n+1);

        for (long i = 2; i <= n; i++) {
            result += countBernoulli(n-i)*countC(i+1, n+1);
        }

        result *= 1.0/(-n-1);

        return result;
    }

    /**
     * Count C(m, n) - Combinations
     * @param m count of object
     * @param n all variants
     * @return result
     */
    private double countC(double m, double n) {
        return countFactorial(n)/(countFactorial(n-m)*countFactorial(m));
    }

    /**
     * Count factorial
     * @param n number
     * @return result
     */
    private double countFactorial(double n) {
        if (n == 0) {
            return 1;
        }
        else {
            return n * countFactorial(n-1);
        }
    }

}
