package functions;

public class Tangens {

    Sinus sinus;
    Cosine cosine;

    public Tangens() {
        sinus = new Sinus();
        cosine = new Cosine();
    }

    public double tg(double x) {

        double cosResult = cosine.cos(x);

        if (cosResult == 0) {
            throw new IllegalArgumentException();
        }

        return sinus.sin(x)/cosResult;
    }

}
