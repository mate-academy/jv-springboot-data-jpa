package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<RequestCategoryDto, Category>,
        ResponseDtoMapper<ResponseCategoryDto, Category> {

    @Override
    public Category mapToModel(RequestCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public ResponseCategoryDto mapToDto(Category category) {
        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();
        responseCategoryDto.setId(category.getId());
        responseCategoryDto.setName(category.getName());
        return responseCategoryDto;
    }
}
