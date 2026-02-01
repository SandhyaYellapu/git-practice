package Octopi.example.demo4.Filter;

import Octopi.example.demo4.Service.UserService;
import Octopi.example.demo4.Util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
      UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
         System.out.println("jwt filter");
          String authHeader=request.getHeader("Authorization");
          String token=null;
          String username=null;
          if(authHeader!=null&&authHeader.startsWith("Bearer"))
          {
               token=authHeader.substring(7);
               username=jwtUtil.extractUsername(token);
          }

          if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
          {
              UserDetails userDetails=userService.loadUserByUsername(username);

              if(jwtUtil.validate(userDetails,username,token))
              {
                  UsernamePasswordAuthenticationToken authToken=  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                  authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(authToken);
              }
          }
          System.out.println("Request reached till filrer chain");
          filterChain.doFilter(request,response);

    }
}
