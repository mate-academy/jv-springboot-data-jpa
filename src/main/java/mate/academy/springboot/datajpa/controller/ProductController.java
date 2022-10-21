package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductServiceImpl;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
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
    private final ProductServiceImpl productService;
    private final ProductMapper mapper;

    public ProductController(ProductServiceImpl productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(mapper.fromDto(requestDto));
        return mapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.get(id);
        return mapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam ProductRequestDto requestDto) {
        Product product = productService.update(mapper.fromDto(requestDto));
        return mapper.toDto(product);
    }

    @GetMapping("/between-price")
    public List<ProductResponseDto> getProductsBetweenPrice(@RequestParam BigDecimal from,
                                                            @RequestParam BigDecimal to) {
        return productService.getAllForPriceBetween(from, to)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categoryIn")
    public List<ProductResponseDto> getProductsInCategory(@RequestParam
                                                              Map<String, String> params) {
        return productService.getAllForCategoryIn(params).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
