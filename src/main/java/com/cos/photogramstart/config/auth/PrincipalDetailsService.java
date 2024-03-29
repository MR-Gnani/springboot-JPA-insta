package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //
@Service //IOC
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	// 패스워드는 시큐리티가 알아서 비교해준다!
	// 리턴이 잘되면 자동으로 세션을 만들어준다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username);
		
		if (userEntity==null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity);
		}
		
	}

}
