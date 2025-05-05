package mate.academy.springboot.datajpa.model.dto;

import static mate.academy.springboot.datajpa.model.Category.Name;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private Name name;
}
