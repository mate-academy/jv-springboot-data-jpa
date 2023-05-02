package mate.academy.springboot.datajpa.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
}
