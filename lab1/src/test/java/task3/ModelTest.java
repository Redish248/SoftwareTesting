package task3;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Phaser;

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
        assertEquals(3, bridge.getPeople().size());
        assertTrue(bridge.getPeople().contains(captain));
        assertFalse(bridge.getPeople().contains(man1));
    }

    @Test
    public void testGoToLocation() {
        captain.moveFromLocation();
        guard.moveFromLocation();
        assertEquals(2, bridge.getPeople().size());
        assertFalse(bridge.getPeople().contains(captain));
        bridge.enter(captain);
        assertEquals(3, bridge.getPeople().size());
        assertTrue(bridge.getPeople().contains(captain));
        assertFalse(bridge.getPeople().contains(guard));
        bridge.enter(guard);
        assertEquals(4, bridge.getPeople().size());
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
    public void testHands() {
        captain.getHands().take(guard);
        captain.getHands().take(man1);
        assertEquals(2, captain.getHands().getThings().size());
    }

    @Test
    public void testTakingByHands() {
        captain.takeInHand(man1);
        captain.takeInHand(man2);
        assertEquals(2, captain.getHands().getThings().size());
        captain.dropFromHand(man1);
        assertEquals(1, captain.getHands().getThings().size());
        captain.dropFromHand(man2);
        assertEquals(0, captain.getHands().getThings().size());
    }

    @Test
    public void testEmbrace() {
        captain.embrace(man1);
        assertTrue(captain.getHands().getThings().contains(man1));
    }

    @Test(expected = NullPointerException.class)
    public void testNullNotebook() {
        captain.readBook();
    }

    @Test
    public void testNotebook() {
        captain.takeInHand(notebook);
        captain.readBook();
    }


}
