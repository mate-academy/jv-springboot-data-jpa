package mate.academy.springboot.datajpa.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class CategoryRequestDto {
    @NonNull
    private String name;
}
