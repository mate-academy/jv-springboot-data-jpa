package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.NotNull;

public class CategoryRequestDto {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
