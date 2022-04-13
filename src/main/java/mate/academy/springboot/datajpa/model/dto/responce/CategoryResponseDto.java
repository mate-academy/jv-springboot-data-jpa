package mate.academy.springboot.datajpa.model.dto.responce;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryResponseDto {
    private Long id;
    @NotNull
    private String name;
}
