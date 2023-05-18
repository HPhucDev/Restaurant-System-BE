package vn.com.foodsystem.business.ipml;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.dao.model.VerifiedCodeModel;
import vn.com.foodsystem.dao.respository.UserRepository;
import vn.com.foodsystem.dao.respository.VerifiedCodeRespository;

@Service
public class RegisterService {
	private UserRepository userRepository;
    
	private VerifiedCodeRespository verifiedCodeRespository;

	public RegisterService(UserRepository userRepository, VerifiedCodeRespository verifiedCodeRespository) {        super();
        this.userRepository = userRepository;
        this.verifiedCodeRespository = verifiedCodeRespository;
    }



 //   @Autowired
//    private JwtTokenProvider jwtTokenProvider;

	
    public List<VerifiedCodeModel> findAll(){
		return verifiedCodeRespository.findAll();
	}
	
	public VerifiedCodeModel findCodeByEmail(String email) {
		VerifiedCodeModel verifiedCode = verifiedCodeRespository.findByEmail(email);
		if(verifiedCode == null) {
			throw new UsernameNotFoundException(email);
		}
		return verifiedCode;
	}
	
//	public VerifiedCodeModel generateVerifiedCode(String email) {
//		// ramdom code 
//		Random generator = new Random();
//		int randomCode = generator.nextInt((999999 - 100000) + 1 + 100000);
//		
//		return new VerifiedCodeModel(randomCode + "", LocalDateTime.now(), email);
//	}
	
	public VerifiedCodeModel save(VerifiedCodeModel verifiedCode) {
		return verifiedCodeRespository.save(verifiedCode);
	}
	
	@Transactional
	public void deleteCodeByEmail(String email) {
		verifiedCodeRespository.deleteByEmail(email);
		
	}
	
	public boolean existsEmail(String email) {
	    if(verifiedCodeRespository.existsByEmail(email))
	        return true;
	    return false;
	}
	
//	public String signup(UserModel user) {
//        if (!userRepository.existsByEmail(user.getEmail())) {
//          String password = user.getPassword();
//          user.setPassword(passwordEncoder.encode(user.getPassword()));
//          userRepository.save(user);
//          Authentication authentication = authenticationManager.authenticate(
//                  new UsernamePasswordAuthenticationToken(
//                          user.getEmail(),
//                          password
//                          ));
//          SecurityContextHolder.getContext().setAuthentication(authentication);
////          return jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//        } else {
//            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//      }
}
