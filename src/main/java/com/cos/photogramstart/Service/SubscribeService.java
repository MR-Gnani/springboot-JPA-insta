package com.cos.photogramstart.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 팔로잉리스트(int principalId, int pageUserId) {
		
		
		
		return null;
	}
	
	@Transactional
	public void 팔로우하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscirbe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 팔로잉한 상대입니다.");
		}
	}
	
	@Transactional
	public void 팔로우취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
