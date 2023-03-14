package mate.academy.springboot.datajpa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryRequestDto {
    @NotNull
    private String name;
}
