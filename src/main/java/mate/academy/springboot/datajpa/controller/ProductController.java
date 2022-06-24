package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.exception.CustomGlobalExceptionHandler;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController extends CustomGlobalExceptionHandler {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/create")
    public ResponseProductDto create(RequestProductDto requestProductDto) {
            return productMapper.toDto(productService.create(productMapper
                .toModel(requestProductDto)));
    }

    @GetMapping("/{id}")
    public ResponseProductDto getById(@PathVariable Long id) {
            return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@PathVariable Long id, RequestProductDto requestProductDto) {
            Product product = productMapper.toModel(requestProductDto);
            product.setId(id);
            return productMapper.toDto(productService.update(product));
    }

    @GetMapping("")
    public List<ResponseProductDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
