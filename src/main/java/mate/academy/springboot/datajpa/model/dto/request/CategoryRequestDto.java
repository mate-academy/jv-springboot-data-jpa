package mate.academy.springboot.datajpa.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CategoryRequestDto {
    private Long id;
    @NotBlank
    private String name;
}
