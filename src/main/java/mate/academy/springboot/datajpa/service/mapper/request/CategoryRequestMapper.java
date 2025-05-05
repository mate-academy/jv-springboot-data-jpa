package mate.academy.springboot.datajpa.service.mapper.request;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper implements RequestDtoMapper<CategoryRequestDto, Category> {

    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
