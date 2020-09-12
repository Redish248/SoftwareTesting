package task3;

import lombok.Data;

@Data
public class Human {

    private Type type = Type.MAN;

    /**
     * If you want to ignore someone when dragging
     */
    private boolean isIgnore;

    /**
     * If you want to resist when someone drags you
     */
    private boolean isResisting;

    private Notebook notebook;

    private Hands hands;

    public Human(Type type) {
        this.type = type;
        hands = new Hands();
    }

    /**
     * Move somewhere when dragging
     */
    public void move() {

    }

    /**
     * Move from bridge. Change content in Bridge human list
     */
    public void moveFromBridge() {

    }

    /**
     * Read book if exists
     */
    public void readBook() {

    }

    /**
     * Say something with intonation
     */
    public void say(Phrase intonation) {

    }

    /**
     * Drag someone by neck
     */
    public void drag(Human human) {

    }

    //обхватить
    public void embrace(Human human) {

    }

    public enum Type {
        CAPTAIN, GUARD, MAN
    }

}
