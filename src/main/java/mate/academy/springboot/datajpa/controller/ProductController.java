package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productDtoMapper.toResponseDto(product);
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.toModel(requestDto);
        productService.save(product);
        return productDtoMapper.toResponseDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productDtoMapper.toResponseDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
