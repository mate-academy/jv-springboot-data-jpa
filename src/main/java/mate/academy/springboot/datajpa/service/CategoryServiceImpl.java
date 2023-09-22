package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.category.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto save(CategoryRequestDto dto) {
        Category category = categoryRepo.save(categoryMapper.toModel(dto));
        return categoryMapper.toRespDto(category);
    }

    @Override
    public CategoryResponseDto get(Long id) {
        Category category = categoryRepo.getReferenceById(id);
        return categoryMapper.toRespDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepo.findAll().stream()
                .map(categoryMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto reqDto) {
        Category category = categoryRepo.getReferenceById(id);
        category.setName(reqDto.getName());
        Category categoryUpd = categoryRepo.save(category);
        return categoryMapper.toRespDto(categoryUpd);
    }
}
