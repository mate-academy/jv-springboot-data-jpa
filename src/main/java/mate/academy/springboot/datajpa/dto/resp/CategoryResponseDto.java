package mate.academy.springboot.datajpa.dto.resp;

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
