package task3;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Bridge extends Location{

    private Door door;

    public Bridge(Door door) {
        this.door = door;
        this.people = new ArrayList<>();
        this.name = "мостик";
    }

    /**
     * Close or open door
     */
    public void changeDoorState() {
        if (door == null) {
            throw new NullPointerException();
        } else {
            System.out.println(getDoor().getMaterial().name + " дверь " + (getDoor().isClosed() ? "открылась" : "закрылась") + ".");
            getDoor().setClosed(!getDoor().isClosed());
        }
    }

    public boolean isOnlyCaptain() {
        if (people.size() == 1 && people.get(0).getType() == Human.Type.CAPTAIN) {
            System.out.println(Human.Type.CAPTAIN.type + " снова остался один.");
            return true;
        }
        return false;
    }
}
