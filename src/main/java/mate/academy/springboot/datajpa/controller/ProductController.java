package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryServiceImp;
import mate.academy.springboot.datajpa.service.ProductServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ProductServiceImp productService;
    private final CategoryServiceImp categoryService;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @Valid @RequestBody ProductRequestDto request) {
        Long categoryId = request.getCategoryId();
        Category category = categoryService.getById(categoryId);
        Product newProduct = mapper.mapToEntity(request);
        newProduct.setCategory(category);
        Product product = productService.create(newProduct);
        ProductResponseDto response = mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable Long id) {
        Product product = productService.getById(id);
        ProductResponseDto response = mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto request) {
        Long categoryId = request.getCategoryId();
        Category category = categoryService.getById(categoryId);
        Product product = mapper.mapToEntity(request).setCategory(category).setId(id);
        Product updatedProduct = productService.update(product);
        ProductResponseDto response = mapper.mapToDto(updatedProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price/between")
    public ResponseEntity<List<ProductResponseDto>> getByPriceBetween(
            @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice) {
        List<ProductResponseDto> products = productService
                .getByPriceBetween(minPrice, maxPrice).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductResponseDto>> getByCategories(
            @RequestParam List<String> names) {
        List<Category> categories = names.stream()
                .map(categoryService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<ProductResponseDto> products = productService.getByCategories(categories).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
