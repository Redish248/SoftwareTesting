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
        CALM("спокойно"), ANGRY("злобно"), THOUGHTFUL("задумчиво"), EXCITED("радостно"), SAD("грустно");

        String name;

        Intonation(String s) {
            this.name = s;
        }
    }
}
