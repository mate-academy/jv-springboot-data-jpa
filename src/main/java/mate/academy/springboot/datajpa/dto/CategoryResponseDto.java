package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryResponseDto {
    private Long id;
    private String name;
}
