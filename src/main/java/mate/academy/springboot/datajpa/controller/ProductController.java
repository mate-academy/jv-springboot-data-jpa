package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/create")
    public ProductResponseDto create(ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        return productMapper.toDto(productService.create(product));
    }

    @GetMapping("/{productId}")
    public ProductResponseDto findById(@PathVariable Long productId) {
        Product product = productService.getById(productId);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponseDto update(@PathVariable Long productId,
                                     @RequestParam
                                     ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(productId);
        return productMapper.toDto(productService.update(product));
    }
}
