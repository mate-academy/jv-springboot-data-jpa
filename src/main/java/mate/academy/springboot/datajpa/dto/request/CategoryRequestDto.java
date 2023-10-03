package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    private String name;
}
