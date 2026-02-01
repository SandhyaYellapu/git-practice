//package Octopi.example.demo4;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("prod")
//public class ProductService
//{
//    @Value("${my.username}")
//    private String useName;
//
//    @Value("${my.password}")
//    private String password;
//   @PostConstruct
//    public void printed()
//    {
//        System.out.println("product service");
//        System.out.println("username: "+useName+" password :"+password);
//    }
//}
//
