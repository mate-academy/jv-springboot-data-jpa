package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.exception.ControllerException;
import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {
    private final static Long ID = 1L;
    private final static String TITTLE = "apple";
    private final static BigDecimal PRICE = BigDecimal.valueOf(3);
    private final static Long CATEGORY_ID = 1L;
    private final static String FRUIT = "fruit";
    private final static String MESSAGE = "A product is absent by id : " + ID + " !";
    private final static String MESSAGE_TWO = "A controller is not exist for these parameters!";
    private static final Long FROM = 1L;
    private static final Long TO = 3L;
    private ProductController productController;
    private ProductMapper productMapper;
    private ProductService productService;
    private RequestProductDto requestDto;
    private Product productWithoutId;
    private Product product;
    private ResponseProductDto expectedDto;

    @BeforeEach
    void setUp() {
        Category category = new Category();
        category.setId(ID);
        category.setTitle(FRUIT);

        productMapper = Mockito.mock(ProductMapper.class);
        productService = Mockito.mock(ProductService.class);
        productController = new ProductController(productService, productMapper);

        requestDto = new RequestProductDto();
        requestDto.setTitle(TITTLE);
        requestDto.setPrice(PRICE);
        requestDto.setCategoryId(CATEGORY_ID);

        productWithoutId = new Product();
        productWithoutId.setTitle(TITTLE);
        productWithoutId.setPrice(PRICE);
        productWithoutId.setCategory(category);

        product = new Product();
        product.setId(ID);
        product.setTitle(TITTLE);
        product.setPrice(PRICE);
        product.setCategory(category);

        expectedDto = new ResponseProductDto();
        expectedDto.setId(ID);
        expectedDto.setTitle(TITTLE);
        expectedDto.setPrice(PRICE);
        expectedDto.setCategoryId(CATEGORY_ID);
    }

    @Test
    void createOk() throws ServiceDataException, ControllerException {
        Mockito.when(productMapper.toModel(requestDto)).thenReturn(productWithoutId);
        Mockito.when(productService.create(productWithoutId)).thenReturn(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(expectedDto);

        ResponseProductDto actual = productController.create(requestDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }

    @Test
    void getByIdOK() throws ControllerException, ServiceDataException {
        Mockito.when(productService.getById(ID)).thenReturn(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(expectedDto);

        ResponseProductDto actual = productController.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }

    @Test
    void getByIdCatchServiceDataException() throws ServiceDataException {
        Mockito.when(productService.getById(ID)).thenThrow(
                new ServiceDataException(MESSAGE));

        ControllerException actual = Assertions.assertThrows(ControllerException.class, ()
                -> productController.getById(ID));
        Assertions.assertEquals(MESSAGE, actual.getMessage());
    }

    @Test
    void update() throws ServiceDataException, ControllerException {
        Mockito.when(productMapper.toModel(requestDto)).thenReturn(productWithoutId);
        Mockito.when(productService.update(product)).thenReturn(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(expectedDto);

        ResponseProductDto actual = productController.update(ID, requestDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedDto, actual);
    }

    @Test
    void findAllByCategoryOK() throws ControllerException, ServiceDataException {
        Mockito.when(productService.findAllByCategory(ID)).thenReturn(List.of(product));
        Mockito.when(productMapper.toDto(product)).thenReturn(expectedDto);

        List<ResponseProductDto> actual = productController
                .findAllByPriceBetweenOrByCategory(null, null, ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(expectedDto), actual);
    }

    @Test
    void findAllByPriceBetweenOK() throws ControllerException {
        Mockito.when(productService.findAllByPriceBetween(BigDecimal.valueOf(FROM),
                BigDecimal.valueOf(TO))).thenReturn(List.of(product));
        Mockito.when(productMapper.toDto(product)).thenReturn(expectedDto);

        List<ResponseProductDto> actual = productController
                .findAllByPriceBetweenOrByCategory(FROM, TO, null);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(List.of(expectedDto), actual);
    }

    @Test
    void findAllByPriceBetweenOrByCategoryCatchServiceDataException() {
        ControllerException actual = Assertions.assertThrows(ControllerException.class, ()
                -> productController.findAllByPriceBetweenOrByCategory(FROM, TO, CATEGORY_ID));
        Assertions.assertEquals(MESSAGE_TWO, actual.getMessage());
    }
}