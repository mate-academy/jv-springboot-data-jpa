package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryRequestDto {
    @NotNull
    private String name;
}
