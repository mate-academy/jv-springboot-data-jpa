package mate.academy.springboot.datajpa.model.dto.response.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
}
