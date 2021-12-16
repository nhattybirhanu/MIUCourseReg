package miu.edu.com.courseregistrationsystem.Security;


import io.swagger.models.Response;
import miu.edu.com.courseregistrationsystem.exception.ExceptionResponse;
import miu.edu.com.courseregistrationsystem.exception.RoleException;
import miu.edu.com.courseregistrationsystem.service.implementation.UserServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    protected UserServiceImpl userAccountService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final  String authorizationHeader=httpServletRequest.getHeader("Authorization");
        System.out.println(authorizationHeader);
        String username=null;
        String jwt=null;
        String role=null;
        if (authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            username=jwtUtil.extractUsername(jwt);
            role=jwtUtil.extractRole(jwt);

        }
        if (!httpServletRequest.getRequestURL().toString().contains("auth")&&(role==null||!httpServletRequest.getRequestURL().toString().contains(role.toLowerCase())))

        {

                setUnauthorizedResponse(httpServletResponse);
                return;
            }

        if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userAccountService.loadUserByUsername(username);


            if (jwtUtil.validToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken=jwtUtil.authenticationToken(jwt,userDetails);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    public void setUnauthorizedResponse(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
      //  Response unAuthorizedResponse = Response.unauthorized().build();
        ResponseEntity responseEntity=ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(new Date(),"UnAuthorized request","Role is not Permitted to access current route"));
        try {
            PrintWriter out = response.getWriter();
            JSONObject jo = new JSONObject(new ExceptionResponse(new Date(),"UnAuthorized request","Role is not Permitted to access the current route"));
            out.println(jo);
        } catch (IOException e) {
        }
    }
}
