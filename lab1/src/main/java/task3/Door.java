package task3;

import lombok.Data;

@Data
public class Door {

    private Material material;

    private boolean isClosed;

    public Door(Material material) {
        this.material = material;
    }

    public enum Material {
        WOOD, STEEL, METAL, PLASTIC, IRON, GLASS
    }

}
