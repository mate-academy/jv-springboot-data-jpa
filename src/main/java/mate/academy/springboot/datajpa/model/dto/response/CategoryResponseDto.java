package mate.academy.springboot.datajpa.model.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode
public class CategoryResponseDto {
    private Long id;
    private String name;
}
