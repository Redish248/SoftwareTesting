package task3;

import java.util.List;

public class Main {

    public static void guardActions(Human guard) {
        guard.getLocation().getPeople().forEach(man -> {
            if (man.getType() == Human.Type.MAN) {
                guard.embrace(man);
            }
        });
        guard.moveFromLocation();
    }

    public static void captainActions(Human captain) {
        captain.say(new Phrase("что-то", Phrase.Intonation.THOUGHTFUL));
        captain.readBook();
    }

    public static void main(String[] args) {
        Bridge bridge = new Bridge(new Door(Door.Material.STEEL));

        Human captain = new Human(Human.Type.CAPTAIN, bridge);
        captain.takeInHand(new Notebook(Notebook.Content.POEMS));

        Human guard = new Human(Human.Type.GUARD, bridge);
        Human man1 = new Human(Human.Type.MAN, bridge);
        Human man2 = new Human(Human.Type.MAN, bridge);

        guardActions(guard);
        bridge.changeDoorState();
        bridge.isOnlyCaptain();
        captainActions(captain);
    }
}
