package mate.academy.springboot.datajpa.service.mapper.impl;

import mate.academy.springboot.datajpa.dto.request.RequestCategoryDto;
import mate.academy.springboot.datajpa.dto.response.ResponseCategoryDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper implements
        DtoMapper<RequestCategoryDto, ResponseCategoryDto, Category> {
    @Override
    public Category mapToModel(RequestCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public ResponseCategoryDto mapToDto(Category model) {
        ResponseCategoryDto dto = new ResponseCategoryDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
