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

    @Test(expected = NoSuchElementException.class)
    public void testRoot() {
        splayTree.find(1);
    }

    @Test
    public void splayRightZigTest() {
        splayTree.add(50);
        splayTree.add(100);
        assertEquals(100, splayTree.getRoot().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getValue());
    }

    @Test
    public void splayLeftZigTest() {
        splayTree.add(100);
        splayTree.add(50);
        assertEquals(50, splayTree.getRoot().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getValue());
    }

    @Test
    public void splayRightZigZigTest() {
        splayTree.add(100);
        splayTree.add(50);
        splayTree.add(200);
        assertEquals(200, splayTree.getRoot().getValue());
        assertEquals(100, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getLeftChild().getValue());
    }

    @Test
    public void addRepeatingValue() {
        splayTree.add(2);
        splayTree.add(3);
        splayTree.add(2);
        assertEquals(2, splayTree.getRoot().getValue());
        assertEquals(2, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(3, splayTree.getRoot().getRightChild().getValue());
    }

    @Test
    public void splayLeftZigZigTest() {
        splayTree.add(100);
        splayTree.add(200);
        splayTree.add(50);
        assertEquals(50, splayTree.getRoot().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getValue());
        assertEquals(200, splayTree.getRoot().getRightChild().getRightChild().getValue());
    }

    @Test
    public void splayLeftZigZagTest() {
        splayTree.add(100);
        splayTree.add(200);
        splayTree.add(150);
        assertEquals(150, splayTree.getRoot().getValue());
        assertEquals(100, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(200, splayTree.getRoot().getRightChild().getValue());
    }

    @Test
    public void splayRightZigZagTest() {
        splayTree.add(100);
        splayTree.add(50);
        splayTree.add(75);
        assertEquals(75, splayTree.getRoot().getValue());
        assertEquals(50, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(100, splayTree.getRoot().getRightChild().getValue());
    }

    @Test
    public void removeTest() {
        splayTree.add(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(1);
        splayTree.add(4);
        splayTree.remove(4);
        assertFalse(splayTree.contains(4));
        assertEquals(3, splayTree.getRoot().getValue());
        assertNull(splayTree.getRoot().getParent());
        assertEquals(5, splayTree.getRoot().getRightChild().getValue());
        assertEquals(2, splayTree.getRoot().getLeftChild().getValue());
        assertEquals(1, splayTree.getRoot().getLeftChild().getLeftChild().getValue());
    }

    @Test
    public void containsTest() {
        assertFalse(splayTree.contains(4));
        splayTree.add(4);
        assertTrue(splayTree.contains(4));
        assertFalse(splayTree.contains(3));
        splayTree.add(3);
        assertTrue(splayTree.contains(4));
        assertTrue(splayTree.contains(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFailTest() {
        splayTree.add(1);
        splayTree.add(2);
        splayTree.remove(3);
    }

    @Test
    public void joinLeftTreeEmptyTest() {
        SplayTree leftTree = new SplayTree();
        SplayTree rightTree = new SplayTree();
        rightTree.add(1);
        rightTree.add(3);
        rightTree.add(2);
        SplayTreeNode newRoot = splayTree.join(leftTree.getRoot(), rightTree.getRoot());
        assertEquals(2, newRoot.getValue());
        assertEquals(1, newRoot.getLeftChild().getValue());
        assertEquals(3, newRoot.getRightChild().getValue());
    }

    @Test
    public void joinRightTreeEmptyTest() {
        SplayTree leftTree = new SplayTree();
        SplayTree rightTree = new SplayTree();
        leftTree.add(1);
        leftTree.add(3);
        leftTree.add(2);
        SplayTreeNode newRoot = splayTree.join(leftTree.getRoot(), rightTree.getRoot());
        assertEquals(3, newRoot.getValue());
        assertEquals(2, newRoot.getLeftChild().getValue());
        assertNull(newRoot.getRightChild());
        assertEquals(1, newRoot.getLeftChild().getLeftChild().getValue());
    }

    @Test
    public void joinTest() {
        SplayTree leftTree = new SplayTree();
        SplayTree rightTree = new SplayTree();
        leftTree.add(1);
        leftTree.add(3);
        leftTree.add(2);
        rightTree.add(4);
        rightTree.add(5);
        SplayTreeNode newRoot = splayTree.join(leftTree.getRoot(), rightTree.getRoot());
        assertEquals(3, newRoot.getValue());
        assertEquals(2, newRoot.getLeftChild().getValue());
        assertEquals(1, newRoot.getLeftChild().getLeftChild().getValue());
        assertEquals(5, newRoot.getRightChild().getValue());
        assertEquals(4, newRoot.getRightChild().getLeftChild().getValue());
    }

    @After
    public void clearTree() {
        splayTree.clear();
    }
}
