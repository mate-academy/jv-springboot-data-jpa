package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
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
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productMapper.toProductResponseDto(productService.getById(id));
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setPrice(productRequestDto.getPrice());
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        Product createdProduct = productService.create(product);
        return productMapper.toProductResponseDto(createdProduct);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return productMapper.toProductResponseDto(productService.create(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("by_price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam Integer from, Integer to) {
        return productService.getAllByPriceBetween(from, to)
          .stream()
          .map(productMapper::toProductResponseDto)
          .collect(
          Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
          .map(productMapper::toProductResponseDto)
          .collect(Collectors.toList());
    }
}
