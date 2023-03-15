package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapperDto;
import mate.academy.springboot.datajpa.service.mapper.ProductDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductDtoMapper productDtoMapper;
    private final CategoryMapperDto categoryMapperDto;
    private final ProductService productService;

    public ProductController(ProductDtoMapper dtoMapper,
                             CategoryMapperDto categoryMapperDto,
                                ProductService productService) {
        this.productDtoMapper = dtoMapper;
        this.categoryMapperDto = categoryMapperDto;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto create(
            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productService
                .save(productDtoMapper.mapToModel(productRequestDto));
        return productDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productDtoMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.mapToModel(requestDto);
        product.setId(id);
        return productDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream().map(productDtoMapper::mapToDto).toList();
    }

    @GetMapping("by-category")
    public List<ProductResponseDto> getAllByCategory(
            @RequestBody List<CategoryRequestDto> requestDtos) {
        List<Category> categories = requestDtos.stream()
                .map(categoryMapperDto::mapToModel).toList();
        return productService.findAllByCategoryIn(categories)
                .stream().map(productDtoMapper::mapToDto).toList();
    }
}
