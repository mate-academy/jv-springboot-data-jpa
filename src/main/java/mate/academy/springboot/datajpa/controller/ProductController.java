package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.MapperDto;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
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
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MapperDto<ProductRequestDto, ProductResponseDto, Product> productMapperDto;

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return productService.findAll().stream()
                .map(productMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> findProductsByPriceBetween(@RequestParam BigDecimal from,
                                                               @RequestParam BigDecimal to) {
        return productService.findProductsByPriceBetween(from, to).stream()
                .map(productMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> findProductsByCategoryIn(
            @RequestParam(name = "categories") List<String> categories) {
        return productService.findProductsByCategoryNameIn(categories).stream()
                .map(productMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.add(productMapperDto.toModel(productRequestDto));
        return productMapperDto.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapperDto.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProductById(@PathVariable Long id,
                                  @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapperDto.toModel(productRequestDto);
        product.setId(id);
        return productMapperDto.toDto(productService.update(product));
    }
}
