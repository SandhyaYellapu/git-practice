package Octopi.example.demo4.Service;

import Octopi.example.demo4.Repository.UserRepository;
import Octopi.example.demo4.User.Role;
import Octopi.example.demo4.User.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserIntializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty())
            {
                Users admin=new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("admin is created");
            }
            if(userRepository.findByUsername("user").isEmpty())
            {
                Users user=new Users();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userRepository.save(user);
                System.out.println("user is created");
            }
        };
    }
}
