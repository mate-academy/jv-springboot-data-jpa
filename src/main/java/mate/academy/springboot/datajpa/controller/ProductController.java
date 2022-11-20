package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    private final CategoryService categoryService;
    private final DtoResponseMapper<ProductResponseDto, Product> productResponseDto;
    private final DtoRequestMapper<ProductRequestDto,Product> productRequestDto;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             DtoResponseMapper<ProductResponseDto, Product> productResponseDto,
                             DtoRequestMapper<ProductRequestDto, Product> productRequestDto) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productResponseDto = productResponseDto;
        this.productRequestDto = productRequestDto;
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPrice(@RequestParam BigDecimal from, BigDecimal to) {
        return productService.findAllByPriceBetween(from,to).stream()
                .map(productResponseDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam List<Long> ids) {
        List<Category> categoryList = new ArrayList<Category>();
        ids.forEach((id) -> {
            categoryList.add(categoryService.get(id));
        });

        return productService.findAllByCategory(categoryList).stream()
                .map(productResponseDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        Product product = productService.create(productRequestDto.fromDto(dto));
        return productResponseDto.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.get(id);
        return productResponseDto.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {

        Product product = productService.get(id);
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryService.get(dto.getCategoryId()));
        productService.update(product);
        return productResponseDto.toDto(product);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto delete(@PathVariable Long id) {
        Product product = productService.get(id);
        productService.delete(product);
        return productResponseDto.toDto(product);
    }

}
