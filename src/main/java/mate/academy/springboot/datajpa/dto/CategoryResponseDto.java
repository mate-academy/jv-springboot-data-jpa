package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
}
