package mate.academy.springboot.datajpa.controller;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
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
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto dto) {
        return productMapper.toDto(productService.save(productMapper.toModel(dto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.get(id));
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto dto) {
        Product product = productMapper.toModel(dto);
        product.setId(id);
        return productMapper.toDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam @Valid BigDecimal from,
                                                         @RequestParam @Valid BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getProductByCategoryName(@RequestParam List<String> names) {
        return productService.findAllByCategoryName(names).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
