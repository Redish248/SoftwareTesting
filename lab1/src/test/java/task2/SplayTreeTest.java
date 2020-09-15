package task2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SplayTreeTest extends Assert {
    private SplayTree splayTree;
    @Before
    public void createTree() {
        splayTree = new SplayTree();
    }

    @Test
    public void addRootTest() {
        splayTree.add(2);
        assertNotNull(splayTree.getRoot());
        assertEquals(splayTree.getRoot().getValue(), 2);
        assertNull(splayTree.getRoot().getParent());
        assertNull(splayTree.getRoot().getLeftChild());
        assertNull(splayTree.getRoot().getRightChild());
    }

    @Test
    public void findTest() {
        splayTree.add(1);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(4);
        splayTree.add(3);
        SplayTreeNode foundElement = splayTree.find(5);
        assertEquals(foundElement, splayTree.getRoot());
    }

    @Test(expected = NoSuchElementException.class)
    public void findFailTest() {
        splayTree.add(3);
        splayTree.add(1);
        splayTree.find(2);
    }

    //test all left and right zigzags
    @Test
    public void addAndSplayTest() {
        //root
        splayTree.add(50);
        //right zig
        splayTree.add(100);
        assertEquals(splayTree.getRoot().getValue(), 100);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 50);
        //left zig zag
        splayTree.add(75);
        assertEquals(splayTree.getRoot().getValue(), 75);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 100);
        //left zig zig
        splayTree.add(25);
        assertEquals(splayTree.getRoot().getValue(), 25);
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getValue(), 75);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue(), 100);
        //left zig
        splayTree.add(10);
        assertEquals(splayTree.getRoot().getValue(), 10);
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 25);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue(), 75);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getRightChild().getRightChild().getValue(), 100);
        //right zig zag
        splayTree.add(17);
        assertEquals(splayTree.getRoot().getValue(), 17);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 10);
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 25);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue(), 75);
        assertEquals(splayTree.getRoot().getRightChild().getRightChild().getRightChild().getRightChild().getValue(), 100);
        //just for getting right zig zig case in next step
        splayTree.add(80);
        assertEquals(splayTree.getRoot().getValue(), 80);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 17);
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 100);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getValue(), 10);
        assertEquals(splayTree.getRoot().getLeftChild().getRightChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getLeftChild().getRightChild().getLeftChild().getValue(), 25);
        assertEquals(splayTree.getRoot().getLeftChild().getRightChild().getRightChild().getValue(), 75);
        //right zig zig
        splayTree.add(150);
        assertEquals(splayTree.getRoot().getValue(), 150);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 100);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getValue(), 80);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getValue(), 17);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getLeftChild().getValue(), 10);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getValue(), 50);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getLeftChild().getValue(), 25);
        assertEquals(splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getRightChild().getValue(), 75);
    }

    @Test
    public void removeTest() {
        splayTree.add(1);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(4);
        splayTree.add(3);
        splayTree.remove(3);
        assertEquals(splayTree.getRoot().getValue(), 2);
        assertNull(splayTree.getRoot().getParent());
        assertEquals(splayTree.getRoot().getRightChild().getValue(), 4);
        assertEquals(splayTree.getRoot().getLeftChild().getValue(), 1);
        assertNull(splayTree.getRoot().getParent());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFailTest() {
        splayTree.add(1);
        splayTree.add(2);
        splayTree.remove(3);
    }

    @After
    public void clearTree() {
        splayTree.clear();
    }
}
