package mate.academy.springboot.datajpa.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NotNull
    private String title;
    @Positive
    private int price;
    @Positive
    private Long categoryId;
}
