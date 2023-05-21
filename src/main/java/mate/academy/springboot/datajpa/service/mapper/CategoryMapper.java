package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
        DtoMapper<Category, RequestCategoryDto, ResponseCategoryDto> {
    @Override
    public Category toModel(RequestCategoryDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public ResponseCategoryDto toDto(Category category) {
        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();
        responseCategoryDto.setId(category.getId());
        responseCategoryDto.setName(category.getName());
        return responseCategoryDto;
    }
}
