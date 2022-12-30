package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.request.CategoryRequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.request.ProductRequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.response.ProductResponseDtoMapper;
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
    private ProductService productService;
    private ProductResponseDtoMapper responseDtoMapper;
    private ProductRequestDtoMapper requestDtoMapper;
    private CategoryRequestDtoMapper categoryRequestDtoMapper;
    private CategoryService categoryService;

    public ProductController(ProductService productService,
                             ProductResponseDtoMapper responseDtoMapper,
                             ProductRequestDtoMapper requestDtoMapper,
                             CategoryRequestDtoMapper categoryRequestDtoMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<ProductResponseDto> getAllWhereCategoriesIn(
            @RequestParam List<String> category) {
        List<Category> categories = category.stream()
                .map(categoryService::getCategoryByName)
                .collect(Collectors.toList());
        return productService.getAllProductsInCategories(categories)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        return responseDtoMapper.mapToDto(productService.save(requestDtoMapper
                    .mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ProductResponseDto update(@RequestBody ProductRequestDto requestDto) {
        return responseDtoMapper.mapToDto(productService.update(requestDtoMapper
                    .mapToModel(requestDto)));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllWherePriceBetween(@RequestParam BigDecimal from,
                                                            @RequestParam BigDecimal to) {
        return productService.getAllWherePriceBetween(from, to)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
