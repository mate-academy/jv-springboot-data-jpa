package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.requestdto.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.responsedto.CategoryResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CategoryMapper {
    public Category mapToModel(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    public CategoryResponseDto mapToDto(@RequestBody Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
