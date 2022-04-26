package mate.academy.springboot.datajpa.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    @NotEmpty(message = "Enter category name, 3 or more symbols")
    @Size(min = 3)
    private String name;
}
