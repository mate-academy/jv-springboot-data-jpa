package mate.academy.springboot.dto.request;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
    private Long id;
    private String name;
}
