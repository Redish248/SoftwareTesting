package functions;

public class Cosine {

    Sinus sinus;

    public Cosine() {
        sinus = new Sinus();
    }

    public double cos(double x) {
        x = sinus.checkX(x);
        double result = Math.sqrt(1 - Math.pow(sinus.sin(x), 2));
        return (x >= -Math.PI/2 && x <= Math.PI/2) ? result : -result;
    }

}
