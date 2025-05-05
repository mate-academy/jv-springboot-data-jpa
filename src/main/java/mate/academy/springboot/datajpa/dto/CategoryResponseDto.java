package mate.academy.springboot.datajpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CategoryResponseDto {
    private Long id;
    private String name;
}
