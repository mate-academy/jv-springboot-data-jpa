package mate.academy.springboot.datajpa.resourse;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryResource {
    private final CategoryService categoryService;
    private final RequestMapper<CategoryRequestDto, Category> requestMapper;
    private final ResponseMapper<CategoryResponseDto, Category> responseMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto requestDto) {
        Category category = requestMapper.toEntity(requestDto);
        CategoryResponseDto dto = responseMapper.toDto(categoryService.save(category));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        CategoryResponseDto dto = responseMapper.toDto(categoryService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto requestDto
    ) {
        Category category = requestMapper.toEntity(requestDto);
        category.setId(id);
        CategoryResponseDto dto = responseMapper.toDto(categoryService.update(category));
        return ResponseEntity.ok(dto);
    }
}
