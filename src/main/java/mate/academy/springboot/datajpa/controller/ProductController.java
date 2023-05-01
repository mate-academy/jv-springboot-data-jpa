package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    public ProductController(ProductService productService,
                             RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product>
                                     productResponseDtoMapper) {
        this.productService = productService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productRequestDtoMapper
                .mapToModel(productRequestDto));
        return productResponseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productResponseDtoMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        productService.update(product);
        return productResponseDtoMapper.mapToDto(product);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsWhenPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        List<Product> allByPriceBetween = productService.getAllByPriceBetween(from, to);
        return allByPriceBetween.stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("categories")
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam List<String> categories) {
        List<Product> allByCategorise = productService.getAllByCategories(categories);
        return allByCategorise.stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
