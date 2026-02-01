package Octopi.example.demo4.Controller;

import Octopi.example.demo4.Service.UserService;
import Octopi.example.demo4.User.AuthRequest;
import Octopi.example.demo4.User.Permissions;
import Octopi.example.demo4.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController
{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    UserService userService;
    @PostMapping("/authenticate")
    public String userToken(@RequestBody AuthRequest authRequest)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
            UserDetails userDetails=userService.loadUserByUsername(authRequest.getUsername());
            return jwtUtil.generateToken(
                    userDetails.getUsername(),
//                    List.of(new SimpleGrantedAuthority(Permissions.USER_READ.name())

            userDetails.getAuthorities());

        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
