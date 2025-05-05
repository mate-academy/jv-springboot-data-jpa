package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductDtoMapper;
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
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        return productDtoMapper.modelToResponse(
                productService.create(
                        productDtoMapper.requestToModel(
                                dto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productDtoMapper.modelToResponse(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.delete(id);
        return "Product with id " + id + " was deleted!";
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto) {
        Product product = productDtoMapper.requestToModel(dto);
        product.setId(id);
        return productDtoMapper.modelToResponse(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productDtoMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findAllByCategoryIn(@RequestParam Set<Long> categoryIds) {
        return productService.findAllByCategoryIdIn(categoryIds)
                .stream()
                .map(productDtoMapper::modelToResponse)
                .collect(Collectors.toList());
    }
}
