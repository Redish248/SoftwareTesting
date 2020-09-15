package task3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModelTest {

    Human captain;
    Human guard;
    Human man1;
    Human man2;
    Bridge bridge;
    Door door;
    Notebook notebook;

    @Before
    public void beforeExecution() {
        door = new Door(Door.Material.STEEL);
        bridge = new Bridge(door);
        captain = new Human(Human.Type.CAPTAIN, bridge);
        guard = new Human(Human.Type.GUARD, bridge);
        man1 = new Human(Human.Type.MAN, bridge);
        man2 = new Human(Human.Type.MAN, bridge);
        notebook = new Notebook(Notebook.Content.POEMS);
    }

    @Test
    public void testCloseDoor() {
        bridge.changeDoorState();
        assertTrue(door.isClosed());
        bridge.changeDoorState();
        assertFalse(door.isClosed());
    }

    @Test
    public void testLeavingBridge() {
        man1.moveFromLocation();
        assertEquals(bridge.getPeople().size(), 3);
        assertTrue(bridge.getPeople().contains(captain));
        assertFalse(bridge.getPeople().contains(man1));
    }

    @Test
    //FIXME: сделать так, чтобы одного и того же человека нельзя было на мост два раза поставить!!!
    //типа Set можно вставить, но тогда сравнение как-то переписать
    public void testGoToLocation() {
        captain.moveFromLocation();
        guard.moveFromLocation();
        assertEquals(bridge.getPeople().size(), 2);
        assertFalse(bridge.getPeople().contains(captain));
        bridge.enter(captain);
        assertEquals(bridge.getPeople().size(), 3);
        assertTrue(bridge.getPeople().contains(captain));
        assertFalse(bridge.getPeople().contains(guard));
        bridge.enter(guard);
        assertEquals(bridge.getPeople().size(), 4);
        assertTrue(bridge.getPeople().contains(captain));
        assertTrue(bridge.getPeople().contains(guard));
    }

    @Test
    public void testCaptainInBridge() {
        guard.moveFromLocation();
        assertFalse(bridge.isOnlyCaptain());
        man1.moveFromLocation();
        man2.moveFromLocation();
        assertTrue(bridge.isOnlyCaptain());
        captain.moveFromLocation();
        assertFalse(bridge.isOnlyCaptain());
    }

    @Test
    //FIXME: у человека две руки, он должен уметь держать только две вещи
    //тогда либо наугад выкидывать, либо сделать тип правой и левой руки, чтобы выбирать, кого выкинуть из руки
    //добавить вообще drop() надо
    public void testHands() {
        captain.getHands().take(guard);
        captain.getHands().take(man1);
        assertEquals(captain.getHands().getThings().size(), 2);
    }

}
