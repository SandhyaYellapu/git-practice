package Octopi.example.demo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demo4Application {

    public static void main(String[] args) {

       ConfigurableApplicationContext context=SpringApplication.run(Demo4Application.class, args);

    }
}

