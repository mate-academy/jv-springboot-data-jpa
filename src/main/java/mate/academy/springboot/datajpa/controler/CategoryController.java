package mate.academy.springboot.datajpa.controler;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    @PostMapping
    public CategoryResponseDto create(@Valid @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryRequestDtoMapper.toModel(requestDto);
        return categoryResponseDtoMapper.toDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @Valid @RequestBody CategoryRequestDto requestDto) {
        Category category = categoryRequestDtoMapper.toModel(requestDto);
        category.setId(id);
        return categoryResponseDtoMapper.toDto(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return categoryResponseDtoMapper.toDto(categoryService.get(id));
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll().stream()
                .map(categoryResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
