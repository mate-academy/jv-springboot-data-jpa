package mate.academy.springboot.datajpa.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    private String name;
}
