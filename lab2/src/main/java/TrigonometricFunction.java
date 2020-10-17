public class TrigonometricFunction {

    Sinus sinus;

    TrigonometricFunction() {
        sinus = new Sinus();
    }

    public double cos(double x) {

        x = sinus.checkX(x);
        double result = Math.sqrt(1 - Math.pow(sinus.sin(x), 2));
        return (x >= -Math.PI/2 && x <= Math.PI/2) ? result : -result;
    }

    public double tg(double x) {

        double cosResult = cos(x);

        if (cosResult == 0) {
            throw new IllegalArgumentException();
        }

        return sinus.sin(x)/cosResult;
    }

}
