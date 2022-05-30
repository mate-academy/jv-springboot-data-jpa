package mate.academy.springboot.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DataJpaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }
}
