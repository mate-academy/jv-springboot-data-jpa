package mate.academy.springboot.datajpa.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CategoryRequestDto {
    @Size(max = 20)
    private String name;
}
