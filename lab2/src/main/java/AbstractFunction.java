public abstract class AbstractFunction {

    double EPS = 1E-7;

    abstract double nextTailor(double x, double n);

    double countFactorial(double n) {
        if (n == 0) {
            return 1;
        } else {
            return n * countFactorial(n-1);
        }
    }

    double countFunction(double x, int i) {
        double prevResult = 0;
        double result = nextTailor(x,i);
        i++;

        while (Math.abs(result - prevResult) > EPS) {
            prevResult = result;
            result += nextTailor(x, i);
            i++;
        }

        return result;
    }

}
