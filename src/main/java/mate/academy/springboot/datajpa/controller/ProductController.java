package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<ProductRequestDto, Product> requestMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseMapper;

    public ProductController(ProductService productService,
                             ResponseDtoMapper<ProductResponseDto, Product> responseMapper,
                             RequestDtoMapper<ProductRequestDto, Product> requestMapper) {
        this.productService = productService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return responseMapper.mapToDto(productService.save(requestMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return responseMapper.mapToDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.mapToModel(requestDto);
        product.setId(id);
        return responseMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-categories")
    public List<ProductResponseDto> getAllProductsInCategories(@RequestParam String[] categories) {
        return productService.findAllByCategoryNameIn(List.of(categories)).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
