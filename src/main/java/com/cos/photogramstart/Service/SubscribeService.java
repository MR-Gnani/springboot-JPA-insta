package com.cos.photogramstart.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
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
	private final EntityManager em; // Repository는 EntityManager를 구현해서 만들어져있는 구현체
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 팔로잉리스트(int principalId, int pageUserId) {
		
		// 쿼리준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, "); // 끝에 한칸씩 띄어주기!
		sb.append("IF((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribestate, ");
		sb.append("IF((? = u.id),1 ,0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?"); //세미콜론 첨부하면 안됨!!
		
		// 첫번째 물음표 : principalId
		// 두번째 물음표 : principalId
		// 마지막 물음표 : pageUserId
		
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		// 쿼리 실행
		// QLRM -> 데이터베이스에서 result된 결과를 자바클래스에 매핑해주는 라이브러리
		JpaResultMapper result = new JpaResultMapper(); // QLRM 라이브러리따로 등록해야함
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		return subscribeDtos;
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
