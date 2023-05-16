package mate.academy.springboot.datajpa.controller;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.mapToModel(productRequestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getById(@PathVariable Long productId) {
        return productMapper.mapToDto(productService.findById(productId));
    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable Long productId) {
        productService.deleteById(productId);
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam int from,
                                                       @RequestParam int to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategory(
            @RequestParam Long categoryId) {
        return productService.findAllByCategory(categoryId)
                .stream()
                .map(productMapper::mapToDto)
                .toList();
    }
}
