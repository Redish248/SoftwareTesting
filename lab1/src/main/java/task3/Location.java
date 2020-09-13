package task3;

import lombok.Data;

import java.util.List;

@Data
public class Location {

    protected List<Human> people;
    protected String name;

    public void enter(Human human) {
        people.add(human);
    }

    public void escape(Human human) {
        people.remove(human);
    }
}
