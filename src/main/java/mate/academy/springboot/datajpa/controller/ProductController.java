package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<ProductRequestDto, ProductResponseDto, Product> productMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Category categoryById = categoryService.getById(productRequestDto.getCategoryId());
        Product product = productMapper.mapToEntity(productRequestDto);
        product.setCategory(categoryById);
        return productMapper.mapToDto(productService.save(product));
    }

    @GetMapping("{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll()
                .stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @GetMapping("/between")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                   @RequestParam BigDecimal to) {
        return productService.getAllByPricePriceBetween(from, to)
                .stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @PostMapping("{id}")
    public ProductResponseDto updateById(@PathVariable Long id,
                                         @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToEntity(productRequestDto);
        product.setId(id);
        Category categoryById = categoryService.getById(productRequestDto.getCategoryId());
        product.setCategory(categoryById);
        return productMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategoryIn(
            @RequestParam Set<String> categories) {
        return productService.getAllByCategoryNameIn(categories)
                .stream()
                .map(productMapper::mapToDto)
                .toList();
    }
}
