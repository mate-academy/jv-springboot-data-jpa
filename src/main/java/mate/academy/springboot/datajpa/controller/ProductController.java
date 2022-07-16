package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.Mapper;
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
    private final ProductService service;
    private final Mapper<Product, ProductRequestDto, ProductResponseDto> mapper;

    public ProductController(ProductService service,
                             Mapper<Product, ProductRequestDto, ProductResponseDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return mapper.toResponseDto(service.save(mapper.toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return mapper.toResponseDto(service.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody ProductRequestDto productRequestDto) {
        Product product = mapper.toModel(productRequestDto);
        product.setId(id);
        service.update(product);
    }

    @GetMapping
    public List<ProductResponseDto> findAllByPrice(@RequestParam BigDecimal from,
                                                 @RequestParam BigDecimal to) {
        return service.findAll(from, to)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filtered")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return service.findAll(params)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
