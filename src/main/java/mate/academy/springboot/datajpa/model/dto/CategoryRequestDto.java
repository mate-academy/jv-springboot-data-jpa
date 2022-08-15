package mate.academy.springboot.datajpa.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CategoryRequestDto {
    @NonNull
    private String name;
}
