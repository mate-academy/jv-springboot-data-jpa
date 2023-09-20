package mate.academy.springboot.datajpa.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    private String name;
}
