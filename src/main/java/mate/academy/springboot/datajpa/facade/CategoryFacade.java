package mate.academy.springboot.datajpa.facade;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryServiceImp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryFacade extends BaseFacade<CategoryDto> {

    private final CategoryMapper mapper;
    private final CategoryServiceImp service;

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category category = mapper.mapToEntity(dto);
        Category savedCategory = service.create(category);
        return mapper.mapToDto(savedCategory);
    }

    @Override
    public CategoryDto findById(Long id) {
        return service.findById(id).map(mapper::mapToDto).orElse(null);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category category = mapper.mapToEntity(dto);
        Category updatedCategory = service.update(id, category);
        return mapper.mapToDto(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
