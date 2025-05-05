package mate.academy.springboot.datajpa.model.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CategoryResponseDto {
    private Long categoryId;
    private String name;
}
