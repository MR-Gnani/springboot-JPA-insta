package com.cos.photogramstart.config.web.dto.auth;

import lombok.Data;

@Data // Getter, Setter
public class SingupDto {

	private String username;
	private String password;
	private String email;
	private String name;
	
}
