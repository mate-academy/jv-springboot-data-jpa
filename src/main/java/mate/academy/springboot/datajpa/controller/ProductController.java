package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
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
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoRequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> productResponseMapper;

    public ProductController(ProductService productService,
                             DtoRequestMapper<ProductRequestDto, Product>
                                     productRequestMapper,
                             DtoResponseMapper<ProductResponseDto, Product>
                                     productResponseMapper) {
        this.productService = productService;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productResponseMapper.toDto(productService.getById(id));
    }

    @PostMapping
    public ProductResponseDto createNewProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productResponseMapper.toDto(
                productService.save(productRequestMapper.fromDto(productRequestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.fromDto(productRequestDto);
        product.setId(id);
        return productResponseMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllWherePriceBetween(@RequestParam BigDecimal from,
                                                             @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getByCategories(@RequestParam List<Long> categoryIds) {
        List<Product> allByCategoryIn = productService.findAllByCategoryIn(categoryIds);
        return productService.findAllByCategoryIn(categoryIds).stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
