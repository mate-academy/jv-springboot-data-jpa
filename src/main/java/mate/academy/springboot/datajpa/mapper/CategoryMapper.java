package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.request.RequestCategory;
import mate.academy.springboot.datajpa.dto.response.ResponseCategory;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category fromDto(RequestCategory requestCategory) {
        Category category = new Category();
        category.setName(requestCategory.getName());
        return category;
    }

    public ResponseCategory toDto(Category category) {
        ResponseCategory responseCategory = new ResponseCategory();
        responseCategory.setId(category.getId());
        responseCategory.setName(category.getName());
        return responseCategory;
    }
}
