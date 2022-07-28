package mate.academy.springboot.datajpa.model.dto;

import lombok.ToString;

@ToString
public class CategoryRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
