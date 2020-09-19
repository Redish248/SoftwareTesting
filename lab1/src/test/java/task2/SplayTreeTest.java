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
        assertEquals(2, splayTree.getRoot().getValue());
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
        assertEquals(splayTree.getRoot(), foundElement);
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
        assertEquals(100, splayTree.getRoot().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getValue());
        //left zig zag
        splayTree.add(75);
        assertEquals(75, splayTree.getRoot().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getValue());
        //left zig zig
        splayTree.add(25);
        assertEquals(25, splayTree.getRoot().getValue());
        assertEquals(50, splayTree.getRoot().getRightChild().getValue());
        assertEquals(75, splayTree.getRoot().getRightChild().getRightChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue());
        //left zig
        splayTree.add(10);
        assertEquals(10, splayTree.getRoot().getValue());
        assertEquals(25, splayTree.getRoot().getRightChild().getValue());
        assertEquals(50, splayTree.getRoot().getRightChild().getRightChild().getValue());
        assertEquals(75, splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getRightChild().getRightChild().getRightChild().getValue());
        //right zig zag
        splayTree.add(17);
        assertEquals(17, splayTree.getRoot().getValue());
        assertEquals(10, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(25, splayTree.getRoot().getRightChild().getValue());
        assertEquals(50, splayTree.getRoot().getRightChild().getRightChild().getValue());
        assertEquals(75, splayTree.getRoot().getRightChild().getRightChild().getRightChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getRightChild().getRightChild().getRightChild().getValue());
        //just for getting right zig zig case in next step
        splayTree.add(80);
        assertEquals(80, splayTree.getRoot().getValue());
        assertEquals(17, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getValue());
        assertEquals(10, splayTree.getRoot().getLeftChild().getLeftChild().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getRightChild().getValue());
        assertEquals(25, splayTree.getRoot().getLeftChild().getRightChild().getLeftChild().getValue());
        assertEquals(75, splayTree.getRoot().getLeftChild().getRightChild().getRightChild().getValue());
        //right zig zig
        splayTree.add(150);
        assertEquals(150, splayTree.getRoot().getValue());
        assertEquals(100, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(80, splayTree.getRoot().getLeftChild().getLeftChild().getValue());
        assertEquals(17, splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getValue());
        assertEquals(10, splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getLeftChild().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getValue());
        assertEquals(25, splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getLeftChild().getValue());
        assertEquals(75, splayTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getRightChild().getRightChild().getValue());
    }

    @Test
    public void removeTest() {
        splayTree.add(1);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(4);
        splayTree.add(3);
        splayTree.remove(3);
        assertEquals(2, splayTree.getRoot().getValue());
        assertNull(splayTree.getRoot().getParent());
        assertEquals(4, splayTree.getRoot().getRightChild().getValue());
        assertEquals(1, splayTree.getRoot().getLeftChild().getValue());
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
