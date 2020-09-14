package task2;

import lombok.Data;

@Data
public class SplayTreeNode {
    private SplayTreeNode parent = null, leftChild = null, rightChild = null;
    private int value;

    SplayTreeNode(int value) {
        this.value = value;
    }
}
