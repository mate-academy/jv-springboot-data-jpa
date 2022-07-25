package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.RequestCategory;
import mate.academy.springboot.datajpa.dto.ResponseCategory;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toModel(RequestCategory req) {
        Category category = new Category();
        category.setName(req.getName());
        return category;
    }

    public ResponseCategory toResponseCategory(Category category) {
        ResponseCategory responseCategory = new ResponseCategory();
        responseCategory.setId(category.getId());
        responseCategory.setName(category.getName());
        return responseCategory;
    }
}
