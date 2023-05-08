package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
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

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.find(id).orElseThrow(()
                -> new RuntimeException("Can't get product by id " + id));
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PostMapping
    ProductResponseDto save(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        Product savedProduct = productService.save(product);
        return productMapper.toDto(savedProduct);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        Product updatedProduct = productService.save(product);
        return productMapper.toDto(updatedProduct);
    }

    @GetMapping("/by-price")
    List<ProductResponseDto> getAllByPrice(@RequestParam(value = "from") BigDecimal priceFrom,
                                           @RequestParam(value = "to") BigDecimal priceTo) {
        List<Product> products = productService.getAllByPriceBetween(priceFrom, priceTo);
        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/by-categories")
    List<ProductResponseDto> getAllByCategories(@RequestParam(value = "id") List<Long> ids) {
        return productService.getAllByCategoryIds(ids)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
