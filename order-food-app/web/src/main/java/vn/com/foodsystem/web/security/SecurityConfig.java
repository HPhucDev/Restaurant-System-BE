package vn.com.foodsystem.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.com.foodsystem.business.ipml.UserDetailsServiceImpl;
import vn.com.foodsystem.web.security.jwt.JwtTokenFilterConfigurer;
import vn.com.foodsystem.web.security.jwt.JwtTokenProvider;
import vn.com.foodsystem.web.security.jwt.LoginProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CRSF
		http.csrf().disable();

		// No session will be created or used by spring security because we're using JWT
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// matcher

		http.authorizeRequests() ///
		.antMatchers("/signin", "/refreshtoken", "/register/**", "/data/**", "/swagger-ui/**", "/swagger-ui.html", "/api/users/forgot-pass/**", "/api/users/**").permitAll()
		.anyRequest().authenticated();

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		// logout
		http.logout();

	}

	@Bean
	public LoginProvider loginProvider() throws Exception {
		return new LoginProvider(this.authenticationManagerBean());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/data/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", //
                "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }
}
