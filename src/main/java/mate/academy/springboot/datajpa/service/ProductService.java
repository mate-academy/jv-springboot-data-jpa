package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;

public interface ProductService extends GeneralService<ProductResponseDto, ProductRequestDto> {

    List<ProductResponseDto> getAllInCategories(Set<String> categories);

    List<ProductResponseDto> findByPriceBetween(BigDecimal from, BigDecimal to);
}
