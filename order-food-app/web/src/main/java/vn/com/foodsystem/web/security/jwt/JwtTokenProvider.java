package vn.com.foodsystem.web.security.jwt;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.com.foodsystem.business.dto.UserLoginDetails;
import vn.com.foodsystem.business.service.UserService;
import vn.com.foodsystem.business.utils.UserLoginDetailsUtil;
import vn.com.foodsystem.dao.model.UserModel;

@Component
public class JwtTokenProvider {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

	private static final String USER_DETAILS_TOKEN_KEY = "auth";

	/**
	 * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key
	 * here. Ideally, in a microservices environment, this key would be kept on a
	 * config-server.
	 */
	@Value("${spring.security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${spring.security.jwt.token.expire-length}")
	private long validityInMilliseconds; // 1h

	@Autowired
	private UserService userService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(Authentication authentication) {
		String accessToken = generateToken(authentication);
		userService.updateToken(authentication.getName(), accessToken);

		return accessToken;
	}

	public String generateToken(Authentication authentication) {
		Claims claims = Jwts.claims().setSubject(authentication.getName());

		// Cast to UserLoginDetails
		claims.put(USER_DETAILS_TOKEN_KEY, authentication.getPrincipal());

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	public String generateRefreshToken(String token) {
        String refreshToken = null;
        UserModel user = userService.findByToken(token);
        if (user != null) {
            if (!validateToken(token)) {
                UserLoginDetails userLoginDetail = UserLoginDetailsUtil.getUserDetailFrom(user);
                Authentication authen = getAuthentication(userLoginDetail);
                refreshToken = generateToken(authen);
                userService.updateToken(user.getEmail(), refreshToken);
            } else {
                refreshToken = token;
            }
        }

        return refreshToken;
	}

	public Authentication getAuthentication(String token) {
		return getAuthentication(getUserDetails(token));
	}

	public Authentication getAuthentication(UserLoginDetails userDetail) {
		return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
	}

	/**
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public UserLoginDetails getUserDetails(String token) {
		return new ObjectMapper().convertValue(
				Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(USER_DETAILS_TOKEN_KEY),
				new TypeReference<UserLoginDetails>() {
				});
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		boolean isValid = false;
		if (token != null) {
			UserModel user = userService.findByToken(token);
			System.out.println(user);
			if (user != null && token.equals(user.getAccessToken())) {
				try {
					Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
					isValid = true;
				} catch (ExpiredJwtException e) {
//                    throw new JwtException("Expired JWT token", HttpStatus.UNAUTHORIZED);
					log.error("{}", e.getMessage());
				} catch (IllegalArgumentException e) {
					throw new JwtException("Invalid JWT token", HttpStatus.BAD_REQUEST);
				}
			}
		}

		return isValid;
	}

}
