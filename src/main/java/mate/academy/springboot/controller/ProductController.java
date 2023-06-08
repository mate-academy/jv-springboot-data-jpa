package mate.academy.springboot.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.dto.request.ProductRequestDto;
import mate.academy.springboot.dto.request.ProductUpdateRequestDto;
import mate.academy.springboot.dto.response.ProductResponseDto;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.service.CategoryService;
import mate.academy.springboot.service.ProductService;
import mate.academy.springboot.service.mapper.RequestDtoMapper;
import mate.academy.springboot.service.mapper.ResponseDtoMapper;
import mate.academy.springboot.service.mapper.UpdateRequestDtoMapper;
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
    private final CategoryService categoryService;
    private final RequestDtoMapper<Product, ProductRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Product, ProductResponseDto> responseDtoMapper;
    private final UpdateRequestDtoMapper<Product, ProductUpdateRequestDto> updateRequestDtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             RequestDtoMapper<Product, ProductRequestDto> requestDtoMapper,
                             ResponseDtoMapper<Product, ProductResponseDto> responseDtoMapper,
                             UpdateRequestDtoMapper<Product,
                                     ProductUpdateRequestDto> updateRequestDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.updateRequestDtoMapper = updateRequestDtoMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(requestDtoMapper.toModel(requestDto));
        return responseDtoMapper.toDto(product);
    }

    @PutMapping
    public ProductResponseDto update(
            @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = updateRequestDtoMapper.toModelUpdate(productUpdateRequestDto);
        Product productAfterUpdate = productService.update(product);
        return responseDtoMapper.toDto(productAfterUpdate);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.toDto(productService.getById(id));
    }

    @GetMapping("/{fromPrice}/{toPrice}")
    public List<ProductResponseDto> getByPrice(
            @PathVariable BigDecimal fromPrice, @PathVariable BigDecimal toPrice) {
        return productService.getByPrice(fromPrice, toPrice)
                .stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/get-by-categories")
    public List<ProductResponseDto> getByCategories(@RequestParam List<Long> categoryIds) {
        List<Category> categories = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            categories.add(categoryService.getById(categoryId));
        }
        return productService.getByCategories(categories)
                .stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
