package functions;

public class Cosine {

    Sinus sinus;

    public Cosine(Sinus sinus) {
        this.sinus = sinus;
    }

    public double cos(double x) {
        double result = Math.sqrt(1 - Math.pow(sinus.sin(x), 2));
        return isPositive(x) ? result : -result;
    }

    private boolean isPositive(double x) {
        double temp = Math.abs(x) % (2*Math.PI);
        x = x >= 0 ? temp : -temp;
        return (Math.abs(x) <= Math.PI/2 || Math.abs(x) >= 3*Math.PI/2);
    }
}
