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
    }

    @Test
    public void changeOpenDoor() {
        bridge.changeDoorState();
        bridge.changeDoorState();
        assertFalse(door.isClosed());
    }

    @Test
    public void testLeavingBridgePeopleSize() {
        man1.moveFromLocation();
        assertEquals(3, bridge.getPeople().size());
    }

    @Test
    public void testLeavingBridgeWhenCaptainStays() {
        man1.moveFromLocation();
        assertTrue(bridge.getPeople().contains(captain));
    }

    @Test
    public void testLeavingBridgeWhenManStays() {
        man1.moveFromLocation();
        assertFalse(bridge.getPeople().contains(man1));
    }

    @Test
    public void testGoToLocationPeopleSize() {
        captain.moveFromLocation();
        assertEquals(3, bridge.getPeople().size());
    }

    @Test
    public void testCaptainGoToLocation() {
        captain.moveFromLocation();
        assertFalse(bridge.getPeople().contains(captain));
    }

    @Test
    public void testGoToLocationAndCheckPeople() {
        guard.moveFromLocation();
        bridge.enter(guard);
        assertEquals(4, bridge.getPeople().size());
    }

    @Test
    public void testGuardGoToLocationAndCheckCaptainStays() {
        guard.moveFromLocation();
        bridge.enter(guard);
        assertTrue(bridge.getPeople().contains(captain));
    }

    @Test
    public void testGuardGoToLocation() {
        guard.moveFromLocation();
        bridge.enter(guard);
        assertTrue(bridge.getPeople().contains(guard));
    }

    @Test
    public void testCaptainInBridge1() {
        guard.moveFromLocation();
        assertFalse(bridge.isOnlyCaptain());
    }

    @Test
    public void testCaptainInBridge2() {
        guard.moveFromLocation();
        man1.moveFromLocation();
        man2.moveFromLocation();
        assertTrue(bridge.isOnlyCaptain());
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
    }

    @Test
    public void testDropFromHands() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        guard.dropFromHand(man1);
        assertEquals(1, guard.getHands().getThings().size());
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
    public void testDragResisting() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        guard.moveFromLocation();
        assertTrue(man1.isResisting());
    }

    @Test
    public void testDragMan() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        guard.moveFromLocation();
        assertFalse(bridge.getPeople().contains(man1));;
    }

    @Test
    public void testDragGuard() {
        guard.takeInHand(man1);
        guard.takeInHand(man2);
        guard.moveFromLocation();
        assertFalse(bridge.getPeople().contains(guard));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNullDoor() {
        Bridge bridge1 = new Bridge(null);
        bridge1.changeDoorState();
    }


}
