package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank
    private String name;
}
