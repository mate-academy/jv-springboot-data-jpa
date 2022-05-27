package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryResponse {

    private String name;
}
