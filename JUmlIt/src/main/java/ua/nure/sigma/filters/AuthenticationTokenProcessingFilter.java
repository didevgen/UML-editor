package ua.nure.sigma.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

//For more info visit: http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
// And also http://habrahabr.ru/post/245415/
//And http://stackoverflow.com/questions/21994348/spring-security-token-based-api-auth-user-password-authentication
interface TokenUtils {
    String getToken(UserDetails userDetails);
    String getToken(UserDetails userDetails, Long expiration);
    boolean validate(String token);
    UserDetails getUserFromToken(String token);
}


public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    @Autowired TokenUtils tokenUtils;
    AuthenticationManager authManager;

    public AuthenticationTokenProcessingFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        @SuppressWarnings("unchecked")
        Map<String, String[]> parms = request.getParameterMap();
        if(parms.containsKey("token")) {
            String token = parms.get("token")[0];
            if (tokenUtils.validate(token)) {
                UserDetails userDetails = tokenUtils.getUserFromToken(token);
                UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
                SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));         
            }
        }
        chain.doFilter(request, response);
    }
}