package task2;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SplayTree {

    private SplayTreeNode root;

    public SplayTreeNode find(int value) {
        SplayTreeNode currentNode = root;
        while (currentNode != null) {
            if (value == currentNode.getValue()) {
                splay(currentNode);
                return currentNode;
            } else {
                if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }
        }
        return null;
    }

    public void add(int value) {
        SplayTreeNode currentNode = root;
        SplayTreeNode currentNodeParent = null;
        SplayTreeNode node = new SplayTreeNode(value);
        boolean isLeft = false;
        while (currentNode != null) {
            currentNodeParent = currentNode;
            if (value <= currentNode.getValue()) {
                isLeft = true;
                currentNode = currentNode.getLeftChild();
            } else {
                isLeft = false;
                currentNode = currentNode.getRightChild();
            }
        }
        node.setParent(currentNodeParent);
        if (root == null) {
            root = node;
        } else {
            if (isLeft) {
                currentNodeParent.setLeftChild(node);
            } else {
                currentNodeParent.setRightChild(node);
            }
        }
        splay(node);
    }

    private SplayTreeNode join(SplayTreeNode leftTree, SplayTreeNode rightTree) {
        if (leftTree != null) {
            SplayTreeNode largestNode = leftTree;
            while (largestNode.getRightChild() != null) {
                largestNode = largestNode.getRightChild();
            }
            splay(largestNode);
            largestNode.setRightChild(rightTree);
            return largestNode;
        } else {
            return rightTree;
        }
    }

    public void remove(int value) {
        SplayTreeNode node = find(value);
        if (node != null) {
            splay(node);
            SplayTreeNode leftTree = node.getLeftChild();
            SplayTreeNode rightTree = node.getRightChild();
            if (leftTree != null) {
                leftTree.setParent(null);
            }
            if (rightTree != null) {
                rightTree.setParent(null);
            }
            root = join(leftTree, rightTree);
        }
    }

    public void splay(SplayTreeNode node) {
        while (node.getParent() != null) {
            if (node.equals(node.getParent().getLeftChild())) {
                if (node.getParent().getParent() == null) {
                    rotateRight(node.getParent());
                } else {
                    if (node.getParent().equals(node.getParent().getParent().getLeftChild())) {
                        rotateRight(node.getParent().getParent());
                        rotateRight(node.getParent());
                    } else {
                        rotateRight(node.getParent());
                        rotateLeft(node.getParent());
                    }
                }
            } else {
                if (node.getParent().getParent() == null) {
                    rotateLeft(node.getParent());
                } else {
                    if (node.getParent().equals(node.getParent().getParent().getRightChild())) {
                        rotateLeft(node.getParent().getParent());
                        rotateLeft(node.getParent());
                    } else {
                        rotateLeft(node.getParent());
                        rotateRight(node.getParent());
                    }
                }
            }
        }
        root = node;
    }

    private void rotateLeft(SplayTreeNode node) {
        SplayTreeNode rightChild = node.getRightChild();
        SplayTreeNode parent = node.getParent();
        if (parent != null) {
            if (node.equals(parent.getLeftChild())) {
                parent.setLeftChild(rightChild);
            } else {
                parent.setRightChild(rightChild);
            }
        }
        SplayTreeNode rightChildLeftChild = rightChild.getLeftChild();
        rightChild.setLeftChild(node);
        node.setRightChild(rightChildLeftChild);
        rightChild.setParent(parent);
        node.setParent(rightChild);
        if (rightChildLeftChild != null) {
            rightChildLeftChild.setParent(node);
        }
    }

    private void rotateRight(SplayTreeNode node) {
        SplayTreeNode leftChild = node.getLeftChild();
        SplayTreeNode parent = node.getParent();
        if (parent != null) {
            if (node.equals(parent.getLeftChild())) {
                parent.setLeftChild(leftChild);
            } else {
                parent.setRightChild(leftChild);
            }
        }
        SplayTreeNode leftChildRightChild = leftChild.getRightChild();
        leftChild.setRightChild(node);
        node.setLeftChild(leftChildRightChild);
        leftChild.setParent(parent);
        node.setParent(leftChild);
        if (leftChildRightChild != null) {
            leftChildRightChild.setParent(node);
        }
    }
}
