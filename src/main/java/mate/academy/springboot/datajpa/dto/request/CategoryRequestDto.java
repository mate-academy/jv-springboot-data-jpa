package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    @Size(min = 3)
    private String name;
}
