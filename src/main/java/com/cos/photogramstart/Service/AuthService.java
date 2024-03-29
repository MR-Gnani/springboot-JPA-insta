package com.cos.photogramstart.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 1. IOC  2. 트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional // Write(Insert, Update, Delete) 
	public User 회원가입(User user) {
		// 회원가입 진행(레파지토리 필요)
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); //해시로 암호화
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");	 // 관리자는 ROLE_ADMIN
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
