package task3;

import lombok.Data;

@Data
public class Human {

    private Type type = Type.MAN;

    private Intonation intonation = Intonation.CALM;

    private boolean isIgnore;

    private boolean isKeeping;

    private boolean isResisting;

    private Notebook notebook;

    public Human(Type type) {
        this.type = type;
    }

    public void move() {

    }

    public void readBook() {

    }

    public void say() {

    }

    public void drag() {

    }

    public void bowDown() {

    }

    //обхватить
    public void embrace() {

    }

    public enum Type {
        CAPTAIN, GUARD, MAN
    }

    public enum Intonation {
        CALM, ANGRY, THOUGHTFUL, EXCITED, SAD
    }

}
