package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.impl.ProductMapper;
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
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductMapper mapper,
                             ProductService service,
                             CategoryService categoryService) {
        this.productMapper = mapper;
        this.productService = service;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto requestDto) {
        return productMapper.mapToDto(
                productService.save(
                        productMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapper.mapToDto(
                productService.getById(id));
    }

    @GetMapping("/price_between")
    public List<ProductResponseDto> getProductById(@RequestParam BigDecimal from,
                                                   @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category_in")
    public List<ProductResponseDto> getProductByCategories(@RequestParam String ids) {
        return productService.findAllByCategoryIn(ids)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto requestDto) {
        Product product = productService.getById(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.getById(requestDto.getCategoryId()));
        return productMapper.mapToDto(productService.save(product));
    }
}
