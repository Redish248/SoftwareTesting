package task3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hands {

    private List<Object> things = new ArrayList<>();

    /**
     *
     */
    public void take(Object thing) {
        things.add(thing);
    }

}
