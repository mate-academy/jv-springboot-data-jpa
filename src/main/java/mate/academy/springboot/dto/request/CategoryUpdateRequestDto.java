package mate.academy.springboot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequestDto {
    private Long id;
    private String name;
}
