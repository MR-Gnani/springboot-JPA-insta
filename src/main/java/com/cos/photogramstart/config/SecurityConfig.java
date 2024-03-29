package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 해당  파일로 시큐리티를 활성화
@Configuration // IOC   
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final OAuth2DetailsService oAuth2DetailsService;	
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super - 삭제 : 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
		
		// csrf토큰 비활성화
		http.csrf().disable();
		
		// 인증이 되지 않은 사용자는 모두 login페이지로 이동시킬 예정.
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated() //앞의 주소는 인증을해라
			.anyRequest().permitAll() // 이외의 주소는 허용해주겠다.
			.and()
			.formLogin() // 그리고 전자의 주소로 올경우 	
			.loginPage("/auth/signin") // 로그인 페이지로 이동시키겠다. GET
			.loginProcessingUrl("/auth/signin") // 스프링 시큐리티가 로그인 프로세스 진행 POST
			.defaultSuccessUrl("/") //인증에 성공하게되면 "/" 페이지로 이동시켜줄게
			.and()
			.oauth2Login() // oauth2 로그인도 할꺼야
			.userInfoEndpoint() // 회원정보에 바로 접근 할래( 코드 - 토큰과정 생략)
			.userService(oAuth2DetailsService); 
	}
	
}
