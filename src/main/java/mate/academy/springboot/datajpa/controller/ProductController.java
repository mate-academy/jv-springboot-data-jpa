package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/price")
    public  List<ProductResponseDto> getProductsByPrice(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findByPriceBetween(from, to);
    }

    @GetMapping("/{id}")
    public  ProductResponseDto getProductsById(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        return productService.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }

    @GetMapping("/inject")
     public void saveProducts() {
        ProductRequestDto dto = new ProductRequestDto();
        dto.setTitle("bread");
        dto.setPrice(BigDecimal.valueOf(21.50));
        productService.save(dto);

        ProductRequestDto dto2 = new ProductRequestDto();
        dto2.setTitle("salt");
        dto2.setPrice(BigDecimal.valueOf(9.50));
        productService.save(dto2);

        ProductRequestDto dto3 = new ProductRequestDto();
        dto3.setTitle("tomato");
        dto3.setPrice(BigDecimal.valueOf(18.20));
        productService.save(dto3);

//        Product product3 = new Product();
//        product3.setTitle("tomato");
//        product3.setPrice(BigDecimal.valueOf(17.00));
//        productService.save(product3);
        //productService.getAllProducts().forEach(System.out::println);
    }
}
