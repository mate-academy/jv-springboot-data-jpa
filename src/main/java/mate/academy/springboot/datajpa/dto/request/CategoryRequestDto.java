package mate.academy.springboot.datajpa.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class CategoryRequestDto {
    @NonNull
    private String name;
}
