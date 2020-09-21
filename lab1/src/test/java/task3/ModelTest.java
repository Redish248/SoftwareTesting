package task3;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

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
    public void testGoToLocation1() {
        captain.moveFromLocation();
        assertEquals(3, bridge.getPeople().size());
        assertFalse(bridge.getPeople().contains(captain));
    }

    @Test
    public void testGoToLocation2() {
        guard.moveFromLocation();
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
        guard.getHands().take(guard);
        guard.getHands().take(man1);
        assertEquals(2, guard.getHands().getThings().size());
    }

    @Test
    public void testTakingByHands() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        assertEquals(2, guard.getHands().getThings().size());
        guard.dropFromHand(man1);
        assertEquals(1, guard.getHands().getThings().size());
        guard.dropFromHand(man2);
        assertEquals(0, guard.getHands().getThings().size());
    }

    @Test
    public void testEmbrace() {
        captain.embrace(man1);
        assertTrue(captain.getHands().getThings().contains(man1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNullNotebook() {
        captain.readBook();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEmbrace() {
        captain.embrace(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSay() {
        captain.say(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDrop() {
        captain.dropFromHand(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTake() {
        captain.takeInHand(null);
    }

    @Test
    public void testNotebook() {
        guard.takeInHand(notebook);
        guard.readBook();
    }

    @Test
    public void testDrag() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        guard.moveFromLocation();
        assertTrue(man1.isResisting());
        assertFalse(bridge.getPeople().contains(man1));;
        assertFalse(bridge.getPeople().contains(guard));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNullDoor() {
        Bridge bridge1 = new Bridge(null);
        bridge1.changeDoorState();
    }


}
