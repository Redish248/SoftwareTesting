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
    //TODO: а тут надо просто всех кроме капитана выгнать? пока так и написала
    public void moveFromBridge(Bridge bridge) {
        if (bridge == null) {
            throw new NullPointerException();
        } else {
            bridge.getPeople().removeIf(val -> !val.getType().equals(Type.CAPTAIN));
        }
    }

    /**
     * Read book if exists
     */
    public void readBook() {
        if (getNotebook() == null) {
            throw new NullPointerException();
        } else {
            switch (getNotebook().getContent()) {
                case POEMS:
                    System.out.println("Полистал свою записную книжку со стихами."); break;
                case PHONES:
                    System.out.println("Полистал свою записную книжку с телефонными номерами."); break;
                case STORIES:
                    System.out.println("Полистал свою записную книжку с рассказами."); break;
                case ADDRESSES:
                    System.out.println("Полистал свою записную книжку с адресами."); break;
                case TIMETABLE:
                    System.out.println("Полистал свою записную книжку с расписанием."); break;
                case FUTURE_PLANS:
                    System.out.println("Полистал свою записную книжку с планами на будущее."); break;
            }
        }
    }

    /**
     * Say something with intonation
     */
    public void say(Phrase phrase) {
        if (phrase == null || phrase.getText() == null) {
            throw new NullPointerException();
        } else {
            System.out.println(phrase.getIntonation().name + " промурлыкал что-то.");
        }
    }

    /**
     * Drag someone somewhere
     */
    public void drag(Human human) {

    }

    //обхватить
    public void embrace(Human human) {
        if (human == null) {
            throw new NullPointerException();
        } else {
            System.out.println("Он обхватил " + human.getType() + "а за шею.");
            getHands().getThings().add(human);
        }
    }

    public enum Type {
        CAPTAIN("Капитан"), GUARD("Охранник"), MAN("Человек");

        String type;

        Type(String s) {
            this.type = s;
        }
    }

}
