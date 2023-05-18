package vn.com.foodsystem.web.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;

public class LoginProvider {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    
    private AuthenticationManager authenticationManager;

    public LoginProvider(AuthenticationManager authManager) {
        this.authenticationManager = authManager;
    }

    @ResponseBody
    public String signin(String username, String password) {
        Authentication authen = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authen);
        return jwtTokenProvider.createToken(authen);
    }
}
