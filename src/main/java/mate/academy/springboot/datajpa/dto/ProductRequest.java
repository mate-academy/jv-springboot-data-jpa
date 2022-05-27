package mate.academy.springboot.datajpa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductRequest {

    @NotNull
    private Long categoryId;
    @NotBlank
    private String title;
    @NotNull
    private Integer price;

}
