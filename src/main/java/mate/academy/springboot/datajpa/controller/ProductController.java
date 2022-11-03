package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product productById = productService.getById(id);
        return productMapper.toDto(productById);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/update")
    public ProductResponseDto update(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.update(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findByPrice(@RequestParam BigDecimal priceFrom,
                                                @RequestParam BigDecimal priceTo) {
        List<Product> allByPriceBetween = productService.findAllByPriceBetween(priceFrom, priceTo);
        return allByPriceBetween.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findByCategories(@RequestParam List<CategoryRequestDto>
                                                                 categoriesRequestDto) {
        List<Product> productsByCategory = productService.findAllByCategory(categoriesRequestDto
                .stream()
                .map(categoryMapper::toModel)
                .collect(Collectors.toList()));
        return productsByCategory.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
