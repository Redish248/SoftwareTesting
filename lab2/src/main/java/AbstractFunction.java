public interface AbstractFunction {

    double EPS = 1E-7;

    double nextTailor(double x, double n);

    default double countFactorial(double n) {
        if (n == 0) {
            return 1;
        } else {
            return n * countFactorial(n-1);
        }
    }

    default double countFunction(double x) {
        double prevResult = 0;
        double result = nextTailor(x,0);
        double i = 1;

        while (result - prevResult > EPS) {
            prevResult = result;
            result += nextTailor(x, i);
            i++;
        }

        return result;
    }

}
