package mate.academy.springboot.datajpa.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryResponseDto {
    private Long id;
    private String name;
}
