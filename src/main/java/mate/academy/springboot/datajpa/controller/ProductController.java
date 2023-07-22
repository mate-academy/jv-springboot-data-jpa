package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
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
    private final ProductService service;
    private final CategoryService categoryService;
    private final DtoRequestMapper<ProductRequestDto, Product> requestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> responseMapper;

    public ProductController(ProductService service,
                             DtoRequestMapper<ProductRequestDto, Product> requestMapper,
                             DtoResponseMapper<ProductResponseDto, Product> responseMapper,
                             CategoryService categoryService) {
        this.service = service;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    ProductResponseDto add(@RequestBody ProductRequestDto dto) {
        Product product = service.save(requestMapper.fromDto(dto));
        return responseMapper.toDto(product);
    }

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = requestMapper.fromDto(dto);
        product.setId(id);
        service.save(product);
    }

    @GetMapping("/{id}")
    ProductResponseDto get(@PathVariable Long id) {
        Product product = service.get(id);
        return responseMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/price-between")
    List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        return service.getAllByPriceBetween(from, to).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category-in")
    List<ProductResponseDto> getAllByCategoryIn(@RequestParam List<String> categories) {
        return service.findAllByCategoryNameIn(categories).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
