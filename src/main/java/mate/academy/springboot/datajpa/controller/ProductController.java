package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final DtoRequestMapper<ProductRequestDto, Product> dtoRequestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> dtoResponseMapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto dto) {
        Product product = productService.save(dtoRequestMapper.toEntity(dto));
        return dtoResponseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return dtoResponseMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto dto) {
        Product product = dtoRequestMapper.toEntity(dto);
        product.setId(id);
        Product productUpdated = productService.update(product);
        return dtoResponseMapper.toDto(productUpdated);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> findAllByCategory(@RequestParam Set<Long> categoryIds) {
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            categories.add(categoryService.findById(categoryId));
        }
        return productService.findProductsByCategoryIn(categories)
                .stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
