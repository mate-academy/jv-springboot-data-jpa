package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Size;

@Setter @Getter
public class CategoryRequestDto {
    @Size(max = 40)
    private String name;
}
