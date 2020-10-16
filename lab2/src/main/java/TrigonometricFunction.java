public class TrigonometricFunction {

    Sinus sinus;

    TrigonometricFunction() {
        sinus = new Sinus();
    }

    public double cos(double x) {

        x = sinus.checkX(x);

        double result = Math.sqrt(1 - Math.pow(sinus.sin(x), 2));
        if (x >= -Math.PI && x <= Math.PI) {
         return result;
        } else {
            return -result;
        }
    }

    public double tg(double x) {

        double cosResult = cos(x);

        if (cosResult == 0) {
            throw new IllegalArgumentException();
        }

        return sinus.sin(x)/cosResult;
    }

}
