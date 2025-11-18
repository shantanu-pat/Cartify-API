package com.ecom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.UserDetails;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserService;

public class UserServiceImpl implements UserService{
@Autowired
	private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails saveUser(UserDetails user) {
		// TODO Auto-generated method stub
		user.setRole("ROLE_USER");
		user.setIsEnable(true);
		user.setAccountNonLocked(true);
		user.setFailedAttempt(0);
		
		String encodePassword =passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		UserDetails saveUser=userRepository.save(user);
		return saveUser;
		
	}

	@Override
	public UserDetails getUserByEmail(String email) {
		// TODO Auto-generated method stub
		
		return userRepository.findByEmail(email);
		
	}

	@Override
	public List<UserDetails> getUsers(String role) {
		// TODO Auto-generated method stub
		return userRepository.findByRole(role);
	}

	@Override
	public Boolean updateAccountStatus(Integer id, Boolean status) {
		// TODO Auto-generated method stub
		Optional<UserDetails> FindByuser=userRepository.findById(id);
		if(FindByuser.isPresent()) {
			UserDetails userDetails=FindByuser.get();
			userDetails.setIsEnable(status);
			userRepository.save(userDetails);
			return true;
		}
		return false;
	}

	@Override
	public void increaseFailedAttempt(UserDetails user) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void userAccountLock(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean unlockAccountTimeExpired(UserDetails user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetAttempt(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserResetToken(String email, String resetToken) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetails getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails updateUserProfile(UserDetails user, MultipartFile img) {
		// TODO Auto-generated method stub
		UserDetails dbUser=userRepository.findById(user.getId()).get();
		if(! img.isEmpty()) {
			dbUser.setProfileImage(img.getOriginalFilename());
		}
		if(! ObjectUtils.isEmpty(dbUser)) {
			dbUser.setName(user.getName());
			dbUser.setMobileNumber(user.getMobileNumber());
			dbUser.setAddress(user.getAddress());
			dbUser.setCity(user.getCity());
			dbUser.setState(user.getState());
			dbUser.setPincode(user.getPincode());
			dbUser=userRepository.save(dbUser);
		}
		return null;
	}

	@Override
	public UserDetails saveAdmin(UserDetails user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}