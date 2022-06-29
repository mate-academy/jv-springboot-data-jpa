package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
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
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
                             ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productDtoMapper.mapToDto(product);
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productDtoMapper.mapToEntity(productRequestDto));
        return productDtoMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal lowerBound,
                                                 @RequestParam BigDecimal upperBound) {
        return productService.getByPrice(lowerBound, upperBound)
                .stream()
                .map(productDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam(value = "id")
                                                           List<Long> categoryIds) {
        return productService.getByCategory(categoryIds)
                .stream()
                .map(productDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectData() {
        Category category1 = new Category();
        category1.setName(1 + " inch timber");
        categoryService.save(category1);
        Category category2 = new Category();
        category2.setName(2 + " inch timber");
        categoryService.save(category2);
        for (int i = 1; i < 30; i++) {
            Product product = new Product();
            product.setCategory(i % 2 == 0 ? category1 : category2);
            product.setPrice(BigDecimal.valueOf(i * 2));
            product.setTitle("Product " + i);
            productService.save(product);
        }
        return "Injected some data";
    }
}
