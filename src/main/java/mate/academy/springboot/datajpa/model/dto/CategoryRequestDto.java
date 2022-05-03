package mate.academy.springboot.datajpa.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    @NotEmpty
    private String name;
}
