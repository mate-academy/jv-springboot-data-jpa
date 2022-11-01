package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryRequestDto {
    @NotNull
    @Size(max = 40)
    private String name;
}
