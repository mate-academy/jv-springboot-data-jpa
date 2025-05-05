package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
    private final DtoMapper<ProductRequestDto, ProductResponseDto, Product> productDtoMapper;

    public ProductController(ProductService productService,
                             DtoMapper<ProductRequestDto, ProductResponseDto, Product>
                                     productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(productDtoMapper.toModel(requestDto));
        return productDtoMapper.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productDtoMapper.toModel(requestDto);
        product.setId(id);
        return productDtoMapper.toDto(productService.update(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllBetweenTwoValues(@RequestParam BigDecimal from,
                                                           @RequestParam BigDecimal to) {
        return productService.getAllBetweenTwoValues(from, to).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public Map<Category, List<Product>> findAllInCategories(
            @RequestParam Map<String, String> params) {
        return productService.findAllInCategories(params);
    }
}
