package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.CategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryDto> {

    @Override
    public CategoryDto mapToDto(Category category) {
        return category == null ? null : new CategoryDto().setName(category.getName());
    }

    @Override
    public Category mapToEntity(CategoryDto dto) {
        return dto == null ? null : new Category().setName(dto.getName());
    }

    @Override
    public Category mapUpdate(Category source, Category target) {
        if (source == null) {
            return null;
        }
        if (source.getId() != null) {
            target.setId(source.getId());
        }
        if (source.getDeleted() != null) {
            target.setDeleted(source.getDeleted());
        }
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        return target;
    }
}
