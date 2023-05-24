package mate.academy.springboot.datajpa.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CategoryRequestDto {
    private String name;
}
