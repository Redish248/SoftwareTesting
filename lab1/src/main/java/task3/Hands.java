package task3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hands {

    private List<Object> things = new ArrayList<>();

    /**
     * Take someone/something to hands
     */
    public void take(Object thing) {
        if (thing == null) {
            throw new IllegalArgumentException();
        }
        if (!things.contains(thing)) {
            things.add(thing);
        }
    }

    /**
     * Drop someone/something to hands
     */
    public void drop(Object thing) {
        if (thing == null) {
            throw new IllegalArgumentException();
        }
        things.remove(thing);
    }

}
