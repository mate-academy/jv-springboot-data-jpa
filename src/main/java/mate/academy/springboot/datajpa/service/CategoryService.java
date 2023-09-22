package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;

public interface CategoryService extends GeneralService<CategoryResponseDto, CategoryRequestDto> {
    CategoryResponseDto update(Long id, CategoryRequestDto reqDto);
}
