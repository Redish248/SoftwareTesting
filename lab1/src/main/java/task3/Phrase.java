package task3;

import lombok.Data;

@Data
public class Phrase {

    private String text;

    private Intonation intonation = Intonation.CALM;

    public Phrase(String text, Intonation intonation) {
        this.text = text;
        this.intonation = intonation;
    }

    public enum Intonation {
        CALM, ANGRY, THOUGHTFUL, EXCITED, SAD
    }
}
