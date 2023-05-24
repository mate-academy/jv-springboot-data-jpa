package mate.academy.springboot.datajpa.dto;

public class CategoryRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryRequestDto{"
                + "name='" + name + '\'' + '}';
    }
}
