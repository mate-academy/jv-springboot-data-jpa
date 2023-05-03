package mate.academy.springboot.datajpa.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
}
