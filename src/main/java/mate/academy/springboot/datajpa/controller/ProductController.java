package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
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
    private final DtoRequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> productResponseMapper;
    private final DtoRequestMapper<CategoryRequestDto, Category> categoryRequestMapper;

    public ProductController(ProductService productService,
                             DtoRequestMapper<ProductRequestDto, Product> productRequestMapper,
                             DtoResponseMapper<ProductResponseDto, Product> productResponseMapper,
                             DtoRequestMapper<CategoryRequestDto, Category> categoryRequestMapper) {
        this.productService = productService;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productRequestMapper.fromDto(requestDto);
        productService.save(product);
        return productResponseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productResponseMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam Long id,
                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productRequestMapper.fromDto(requestDto);
        product.setId(id);
        productService.update(product);
        return productResponseMapper.toDto(product);
    }

    @GetMapping("/by-price-range")
    public List<ProductResponseDto> getByPriceRange(@RequestParam Double from,
                                                    @RequestParam Double to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getByCategories(@RequestParam
                                                        Map<String, String> params) {
        return productService.findAllByCategories(params).stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
