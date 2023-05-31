package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> dtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             DtoMapper<Product, ProductRequestDto, ProductResponseDto> dtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public Product create(@RequestBody ProductRequestDto requestDto) {
        return productService.save(dtoMapper.toModel(requestDto));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toResponseDto(productService.getProductById(id));

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto requestDto) {
        Product productById = productService.getProductById(id);
        productById.setTitle(requestDto.getTitle());
        productById.setPrice(requestDto.getPrice());
        productById.setCategory(categoryService.getCategoryById(requestDto.getCategoryId()));
        return dtoMapper.toResponseDto(productService.updateProduct(productById));
    }

    @GetMapping("/price-between")
    List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal from,
                                               @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(dtoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category-in")
    List<ProductResponseDto> getAllByCategoryIn(@RequestParam List<Category> categories) {
        return productService.findAllByCategoryIn(categories)
                .stream()
                .map(dtoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
