package mate.academy.springboot.datajpa.service.mapper;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.model.dto.response.ResponseCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper
        implements DtoMapper<Category, RequestCategoryDto, ResponseCategoryDto> {
    @Override
    public Category toModel(RequestCategoryDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public ResponseCategoryDto toDto(Category element) {
        ResponseCategoryDto responseDto = new ResponseCategoryDto();
        responseDto.setId(element.getId());
        responseDto.setName(element.getName());
        return responseDto;
    }
}
