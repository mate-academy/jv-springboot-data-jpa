package mate.academy.springboot.datajpa.model.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryResponseDto {
    private Long id;
    private String name;
}
