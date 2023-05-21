package mate.academy.springboot.datajpa.dto.response;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
}
