package mate.academy.springboot.datajpa.model.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryRequestDto {
    @NotNull
    private String name;
}
