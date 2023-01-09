package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;

    public ProductController(ProductService productService,
                             ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper,
                             RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper) {
        this.productService = productService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productDto) {
        return responseDtoMapper.mapToDto(
                productService.save(
                        requestDtoMapper.mapToModel(productDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto requestDto) {
        Product product = requestDtoMapper.mapToModel(requestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> getAllByCategories(@RequestParam Map<String, String> params) {
        return productService.getAll(params).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
