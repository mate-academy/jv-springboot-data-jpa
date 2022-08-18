package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategoriesId(@RequestParam List<Long> categoryIds) {
        return productService.findAllByCategories(categoryIds)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getByPrice(@RequestParam BigDecimal from, BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        Product product1 = new Product();
        product1.setTitle("Barby");
        product1.setPrice(BigDecimal.TEN);
        product1.setCategory(categoryService.getById(1L));
        productService.save(product1);
        Product product2 = new Product();
        product2.setTitle("Bread");
        product2.setPrice(BigDecimal.ONE);
        product2.setCategory(categoryService.getById(2L));
        productService.save(product2);
        return "Products was created";
    }
}
