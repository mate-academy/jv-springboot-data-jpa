package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class CategoryResponseDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
}
