package mate.academy.springboot.datajpa.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoryResponseDto {
    private Long id;
    private String name;
}
