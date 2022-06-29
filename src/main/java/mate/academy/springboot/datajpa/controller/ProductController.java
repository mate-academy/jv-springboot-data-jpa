package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductDtoMapper;
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
@RequestMapping("/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(CategoryService categoryService,
                             ProductService productService,
                             ProductDtoMapper productDtoMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/inj")
    public void inject() {
        Product testProduct = new Product();
        Category category = new Category();
        category.setName("Test Category");
        testProduct.setCategory(categoryService.save(category));
        testProduct.setPrice(new BigDecimal(1200));
        testProduct.setTitle("Test Product");
        productService.save(testProduct);
        System.out.println("Injected");
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        return productDtoMapper.toProductResponseDto(productService
                .save(productDtoMapper.toProductModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.toProductResponseDto(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.toProductModel(requestDto);
        product.setId(id);
        return productDtoMapper.toProductResponseDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/2")
    public List<ProductResponseDto> getRange(@RequestParam BigDecimal from,
                                             @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productDtoMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping()
    public List<ProductResponseDto> getList(@RequestParam List<Long> ids) {
        return productService.findAllByCategoryIdIn(ids)
                .stream()
                .map(productDtoMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }
}
