package task3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bridge {

    private List<Human> people;

    private Door door;

    public Bridge(Door door) {
        this.door = door;
        people = new ArrayList<>();
    }

    /**
     * Close or open door
     */
    public void changeDoorState() {
        if (door == null) {
            throw new NullPointerException();
        } else {
            System.out.println(getDoor().getMaterial().name + " дверь " + (getDoor().isClosed() ? "открылась." : "закрылась."));
            getDoor().setClosed(!getDoor().isClosed());
        }
    }
}
