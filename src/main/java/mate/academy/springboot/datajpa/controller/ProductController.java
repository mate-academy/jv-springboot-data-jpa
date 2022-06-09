package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.RequestProductDto;
import mate.academy.springboot.datajpa.dto.ResponseExceptionDto;
import mate.academy.springboot.datajpa.dto.ResponseProductDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.exception.ControllerException;
import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
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
    public ResponseProductDto getById(@PathVariable Long id) throws ControllerException {
        try {
            return productMapper.toDto(productService.getById(id));
        } catch (ServiceDataException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, RequestProductDto requestProductDto) {
        Product product = productMapper.toModel(requestProductDto);
        product.setId(id);
        productService.update(product);
    }

    @GetMapping()
    public List<ResponseProductDto> findAllByPriceBetween(@RequestParam(required = false) Long from,
                                                          @RequestParam(required = false) Long to,
                                                          @RequestParam(required = false) Long categoryId)
            throws ControllerException {
        if (from == null && to == null && categoryId != null) {
            try {
                return productService.findAllByCategory(categoryId).stream()
                        .map(productMapper::toDto)
                        .collect(Collectors.toList());
            } catch (ServiceDataException e) {
                throw new ControllerException(e.getMessage());
            }

        }
        if (from != null && to != null && categoryId == null) {
            return productService.findAllByPriceBetween(BigDecimal.valueOf(from),
                            BigDecimal.valueOf(to)).stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw new ControllerException("A controller is not exist for these parameters!");
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseExceptionDto handleException(ControllerException e) {
        return new ResponseExceptionDto(e.getMessage());
    }

}
