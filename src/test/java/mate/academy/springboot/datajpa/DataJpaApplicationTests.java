package mate.academy.springboot.datajpa;

import mate.academy.springboot.datajpa.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProductController.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class DataJpaApplicationTests {

    @Test
    void contextLoads() {
    }

}
