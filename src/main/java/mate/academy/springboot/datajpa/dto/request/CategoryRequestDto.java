package mate.academy.springboot.datajpa.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CategoryRequestDto {
    private String name;
}
