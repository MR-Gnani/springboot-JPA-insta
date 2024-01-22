package com.cos.photogramstart.web.dto.subscribe;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	
	// mariaDB는 Integer사용 가능, MySQL작업시 BigInteger사용해야함
	private int Id;
	private String username;
	private String profileImageUrl;
	private BigInteger subscribeState;
	private BigInteger equalUserState;
	
}
