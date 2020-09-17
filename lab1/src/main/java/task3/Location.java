package task3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Location {

    protected List<Human> people;
    protected String name;

    Location() {
        people = new ArrayList<>();
    }

    public void enter(Human human) {
        if(!people.contains(human)) {
            people.add(human);
        }
    }

    public void escape(Human human) {
        people.remove(human);
    }
}
