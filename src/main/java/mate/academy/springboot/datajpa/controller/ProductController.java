package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.util.InjectDataService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final InjectDataService injectDataService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(InjectDataService injectDataService,
                             ProductService productService,
                             ProductMapper productMapper) {
        this.injectDataService = injectDataService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/inject")
    public List<ProductResponseDto> injectData(@RequestParam int count) {
        injectDataService.inject(count);
        return productService.getAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> parameters) {
        return productService.findAll(parameters).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @PatchMapping
    public ProductResponseDto update(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.update(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

}
