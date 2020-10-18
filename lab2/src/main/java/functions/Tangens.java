package functions;

public class Tangens implements SystemModule{

    Sinus sinus;
    Cosine cosine;

    public Tangens(Sinus sinus, Cosine cosine) {
        this.sinus = sinus;
        this.cosine = cosine;
    }

    @Override
    public String getModuleName() {
        return "Tg";
    }

    public double calc(double x) {

        double cosResult = cosine.calc(x);

        if (cosResult == 0) {
            throw new IllegalArgumentException();
        }

        return sinus.calc(x)/cosResult;
    }


}
