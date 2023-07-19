package mate.academy.springboot.datajpa.resourse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseMapper;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductResource {
    private final ProductService productService;
    private final RequestMapper<ProductRequestDto, Product> requestMapper;
    private final ResponseMapper<ProductResponseDto, Product> responseMapper;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toEntity(requestDto);
        ProductResponseDto dto = responseMapper.toDto(productService.save(product));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        ProductResponseDto dto = responseMapper.toDto(productService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDto requestDto
    ) {
        Product product = requestMapper.toEntity(requestDto);
        product.setId(id);
        ProductResponseDto dto = responseMapper.toDto(productService.update(product));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<ProductResponseDto>> findAllByPriceBetween(
            @RequestParam String from,
            @RequestParam String to
    ) {
        List<Product> products = productService.findAllByPriceBetween(
                new BigDecimal(from), new BigDecimal(to)
        );
        return ResponseEntity.ok(mapToDtoList(products));
    }

    private List<ProductResponseDto> mapToDtoList(List<Product> products) {
        return products.stream()
                .map(responseMapper::toDto)
                .toList();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll(
            @RequestParam Map<String, String> params
    ) {
        List<Product> products = (params == null || params.isEmpty())
                ? productService.findAll()
                : productService.findAll(params);
        return ResponseEntity.ok(mapToDtoList(products));
    }
}
