package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
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
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        Product product = productMapper.toProductModel(dto);
        Product savedProduct = productService.create(product);
        return productMapper.toProductResponseDto(savedProduct);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.get(id);
        return productMapper.toProductResponseDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto) {
        Product product = productMapper.toProductModel(dto);
        product.setId(id);
        return productMapper.toProductResponseDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllProductsByPriceBetween(@RequestParam BigDecimal from,
                                                                 @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::toProductResponseDto)
                .toList();

    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam List<String> categories) {
        return categories.stream()
                .<ProductResponseDto>mapMulti(
                        (category, consumer) -> productService.getByCategory(
                                        categoryService.findByName(category))
                                .forEach(product -> consumer.accept(
                                        productMapper.toProductResponseDto(product))))
                .toList();
    }
}
