package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.models.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mappers.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> dtoDtoMapper;
    private final ProductService productService;

    @Autowired
    public ProductController(DtoMapper<Product, ProductResponseDto, ProductRequestDto> dtoDtoMapper,
                             ProductService productService) {
        this.dtoDtoMapper = dtoDtoMapper;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = dtoDtoMapper.mapToModel(productRequestDto);
        return dtoDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        dtoDtoMapper.mapToDto(productService.get(id));
        return dtoDtoMapper.mapToDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = dtoDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return dtoDtoMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPriceRange(@RequestParam BigDecimal minPrice,
                                                            @RequestParam BigDecimal maxPrice) {
        List<Product> allProductsByPriceBetween = productService
                .getAllByPriceBetween(minPrice, maxPrice);
        return allProductsByPriceBetween.stream()
                .map(dtoDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getProductsByCategory(@RequestParam List<Long> categories) {
        List<Product> products = categories.stream()
                .map(productService::getByCategoryId)
                .flatMap(Collection::stream)
                .toList();
        return products.stream()
                .map(dtoDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
