package vn.com.foodsystem.business.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.VerifiedCodeService;
import vn.com.foodsystem.dao.model.VerifiedCodeModel;
import vn.com.foodsystem.dao.respository.VerifiedCodeRespository;

@Service
public class VerifiedCodeServiceIpml extends AbstractService<VerifiedCodeModel, Long> implements VerifiedCodeService {

	private VerifiedCodeRespository verifiedCodeRespository;

	@Autowired
	public VerifiedCodeServiceIpml(VerifiedCodeRespository verifiedCodeRespository) {
		super(verifiedCodeRespository);
		this.verifiedCodeRespository = verifiedCodeRespository;
	}

	@Override
	public VerifiedCodeModel findByOtpCode(int otpCode) {
		return verifiedCodeRespository.findByOtpCode(otpCode);
	}

	@Override
	public VerifiedCodeModel findByToken(String token) {
		return verifiedCodeRespository.findByToken(token);
	}

}
