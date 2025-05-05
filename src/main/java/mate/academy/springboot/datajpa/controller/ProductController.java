package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.impl.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productRequestMapper;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productRequestMapper,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productRequestMapper = productRequestMapper;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.fromDto(productRequestDto);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable Long productId) {
        Product productById = productService.findById(productId);
        return productMapper.toDto(productById);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponseDto updateProduct(@PathVariable Long productId,
                                            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.fromDto(productRequestDto);
        product.setId(productId);
        return productMapper.toDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findProductsByPriceBetween(@RequestParam BigDecimal lowBound,
                                                               @RequestParam BigDecimal highBound) {
        return productService.findProductsByPriceBetween(lowBound, highBound).stream()
                .map(productMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
