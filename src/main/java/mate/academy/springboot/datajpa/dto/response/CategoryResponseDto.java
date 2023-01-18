package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryResponseDto {
    private Long id;
    private String name;
}
