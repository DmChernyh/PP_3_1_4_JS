package ru.kata.spring.boot_security.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.init.InitConfig;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        app.getBean(InitConfig.class).init();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
