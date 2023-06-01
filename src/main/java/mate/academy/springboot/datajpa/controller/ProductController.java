package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.maper.DtoMapper;
import mate.academy.springboot.datajpa.model.Product;
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
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper;

    public ProductController(ProductService productService, DtoMapper<Product,
            ProductRequestDto, ProductResponseDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto crate(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.mapToModel(productRequestDto));
        return productMapper.modelToMap(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.modelToMap(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productMapper.modelToMap(productService.update(product));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        return productService.findAllByPriseBetween(from, to)
                .stream()
                .map(productMapper::modelToMap)
                .toList();
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategory(
            @RequestParam Long categoryId) {
        return productService.findAllByCategory(categoryId)
                .stream()
                .map(productMapper::modelToMap)
                .toList();
    }
}
