package Octopi.example.demo4.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController
{
    @GetMapping("/details")
    @PreAuthorize("hasRole('USER')")
    public String userDetails()
    {
        System.out.println("user controller");
        return "user1";
    }
    @PostMapping("/userInformation")
    @PreAuthorize("hasRole('ADMIN')")
    public String UserInformation()
    {
        return "userInformation";
    }
}
