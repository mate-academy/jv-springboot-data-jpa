package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestMapper;
    private final ResponseDtoMapper<Product, ProductResponseDto> responseMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             RequestDtoMapper<ProductRequestDto,
                                     Product> requestMapper,
                             ResponseDtoMapper<Product,
                                     ProductResponseDto> responseMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = requestMapper.mapToModel(productRequestDto);
        Category category = categoryService.get(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Can`t find category with id:"
                        + productRequestDto.getCategoryId()));
        product.setCategory(category);
        return responseMapper.mapToDto(productService.save(product));
    }

    @GetMapping
    public ProductResponseDto get(@RequestParam Long id) {
        return responseMapper.mapToDto(productService.get(id)
                .orElseThrow(() -> new NoSuchElementException("Can`t find product by id:" + id)));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPrice(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam Set<String> categories) {
        return productService.getAllByCategoriesIn(categories).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestMapper.mapToModel(productRequestDto);
        product.setId(id);
        Category category = categoryService.get(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Can`t find category with id:"
                        + productRequestDto.getCategoryId()));
        product.setCategory(category);
        return responseMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Long id) {
        productService.remove(id);
    }
}
