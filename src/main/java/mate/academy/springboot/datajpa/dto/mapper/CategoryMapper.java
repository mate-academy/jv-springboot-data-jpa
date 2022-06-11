package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public ResponseCategoryDto toDto(Category category) {
        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();
        responseCategoryDto.setId(category.getId());
        responseCategoryDto.setTitle(category.getTitle());
        return responseCategoryDto;
    }

    public Category toModel(RequestCategoryDto requestCategoryDto) {
        Category category = new Category();
        category.setTitle(requestCategoryDto.getTitle());
        return category;
    }
}
