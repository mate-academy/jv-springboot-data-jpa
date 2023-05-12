package mate.academy.springboot.datajpa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CategoryRequestDto {
    @NotNull
    private String name;
}
