package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.models.Product;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mappers.ProductMapper;
import mate.academy.springboot.datajpa.service.mappers.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mappers.ResponseDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper,
                             ProductService productService,
                             ProductMapper productMapper,
                             CategoryService categoryService) {
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        return productMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        responseDtoMapper.mapToDto(productService.get(id));
        return responseDtoMapper.mapToDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/byPrice")
    public List<ProductResponseDto> getProductsByPriceRange(@RequestParam BigDecimal minPrice,
                                                            @RequestParam BigDecimal maxPrice) {
        List<Product> allProductsByPriceBetween = productService.getAllByPriceBetween(minPrice, maxPrice);
        return allProductsByPriceBetween.stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("byCategories")
    public List<ProductResponseDto> getProductsByCategory(@RequestParam List<Long> categories) {
        List<Category> categoriesFromDb = categories.stream().map(categoryService::get).toList();
        List<Product> byCategories = productService.getByCategories(categoriesFromDb);
        return byCategories.stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
