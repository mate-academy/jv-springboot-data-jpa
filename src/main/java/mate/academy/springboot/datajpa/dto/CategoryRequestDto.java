package mate.academy.springboot.datajpa.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "mustn't be blank")
    private String name;
}
