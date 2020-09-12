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
        WOOD("Деревянная"), STEEL("Стальная"), METAL("Металлическая"), PLASTIC("Пластиковая"), IRON("Железная"), GLASS("Стеклянная");

        String name;

        Material(String s) {
            this.name = s;
        }
    }

}
