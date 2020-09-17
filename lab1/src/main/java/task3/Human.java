package task3;

import lombok.Data;

import java.util.NoSuchElementException;
import java.util.Objects;

@Data
public class Human {

    /**
     * If you want to resist when someone drags you
     */
    private boolean isResisting;

    private Type type = Type.MAN;
    private Hands hands;
    private Location location;


    public Human(Type type, Location location) {
        this.type = type;
        this.location = location;
        hands = new Hands();
        location.enter(this);
    }

    /**
     * Move from location and drag human in hands
     */
    public void moveFromLocation() {
        if (this.location != null) {
            if (hands.getThings().size() != 0) {
                System.out.println("Выволок с "+location.name+"а.");
                boolean resistance = false;
                for (Object thing: hands.getThings()) {
                    if (thing instanceof Human) {
                        Human man = (Human) thing;
                        man.drag();
                        resistance |= man.isResisting;
                    }
                }
                if (resistance) {
                    System.out.println("Не обращая внимания нa их сопротивление.");
                }
            }
            this.location.escape(this);
            this.location = null;

        }
    }

    /**
     * Read book if exists
     */
    public void readBook() {
        Notebook notebook = (Notebook) hands.getThings().stream()
                .filter(thing -> thing instanceof Notebook)
                .findAny().orElse(null);
        if (notebook == null) {
            throw new NullPointerException();
        } else {
            String content = "";
            switch (notebook.getContent()) {
                case POEMS:
                    content = " со стихами"; break;
                case PHONES:
                    content = " с телефонными номерами"; break;
                case STORIES:
                    content = " с рассказами"; break;
                case ADDRESSES:
                    content = " с адресами"; break;
                case TIMETABLE:
                    content = " с расписанием"; break;
                case FUTURE_PLANS:
                    content = " с планами на будущее"; break;
            }
            System.out.println("Полистал свою записную книжку" + content + ".");
        }
    }

    /**
     * Say something with intonation
     */
    public void say(Phrase phrase) {
        if (phrase == null || phrase.getText() == null) {
            throw new NullPointerException();
        } else {
            System.out.println(this.getType().type + " " + phrase.getIntonation().name + " промурлыкал что-то.");
        }
    }

    /**
     * If you want to throw out someone from location who is resisting
     */
    public void drag() {
        moveFromLocation();
        isResisting = true;
    }

    /**
     * Take someone/something to hands
     */
    public void takeInHand(Object thing) {
        getHands().take(thing);
    }

    /**
     * Drop someone/something to hands
     */
    public void dropFromHand(Object thing) {
        getHands().drop(thing);
    }

    //обхватить
    public void embrace(Human human) {
        if (human == null) {
            throw new NullPointerException();
        } else {
            System.out.println(this.getType().type + " обхватил " + human.getType().type + "а за шею.");
            takeInHand(human);
        }
    }

    @Override
    public boolean equals(Object o) {
        return  (this == o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isResisting, type, hands, location);
    }

    public enum Type {
        CAPTAIN("Капитан"), GUARD("Охранник"), MAN("Человек");

        String type;

        Type(String s) {
            this.type = s;
        }
    }

}
