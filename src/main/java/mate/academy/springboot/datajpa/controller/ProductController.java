package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;

    public ProductController(ProductService productService,
                             RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper) {
        this.productService = productService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(requestDtoMapper.mapToModel(productRequestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        productService.update(product);
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllBetweenPrice(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        return productService.getAllByPrice(from, to)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam Map<String, String> params) {
        return productService.getAllByCategory(params)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
