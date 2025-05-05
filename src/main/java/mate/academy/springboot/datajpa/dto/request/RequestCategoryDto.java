package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCategoryDto {
    @NotBlank
    private String name;
}
