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
        CALM("Спокойно"), ANGRY("Злобно"), THOUGHTFUL("Задумчиво"), EXCITED("Радостно"), SAD("Грустно");

        String name;

        Intonation(String s) {
            this.name = s;
        }
    }
}
