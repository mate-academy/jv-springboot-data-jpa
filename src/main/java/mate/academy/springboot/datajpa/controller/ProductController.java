package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
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
    private final DtoResponseMapper<ProductResponseDto, Product> dtoResponseMapper;
    private final DtoRequestMapper<ProductRequestDto, Product> dtoRequestMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             DtoResponseMapper<ProductResponseDto, Product> dtoResponseMapper,
                             DtoRequestMapper<ProductRequestDto, Product> dtoRequestMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dtoResponseMapper = dtoResponseMapper;
        this.dtoRequestMapper = dtoRequestMapper;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(
                dtoRequestMapper.toModel(requestDto));
        return dtoResponseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return dtoResponseMapper.toDto(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto requestDto) {
        Product product = dtoRequestMapper.toModel(requestDto);
        product.setId(id);
        return dtoResponseMapper.toDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal min,
                                           @RequestParam BigDecimal max) {
        return productService.getByPriceBetween(min, max).stream()
                .map(dtoResponseMapper::toDto)
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getByCategories(@RequestParam(name = "categories")
                                                        List<String> categoriesNames) {
        List<Category> categories = categoryService.getByNames(categoriesNames);
        return productService.getByCategories(categories).stream()
                .map(dtoResponseMapper::toDto)
                .toList();
    }
}
