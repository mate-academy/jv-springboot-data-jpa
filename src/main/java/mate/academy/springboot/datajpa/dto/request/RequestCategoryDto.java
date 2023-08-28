package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestCategoryDto {
    @NotNull
    private String name;
}
