package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	// : <- 변수를 바인딩해서 넣겠다는 문법
	
	@Modifying // INSERT, DELETE, UPDATE를 네이티브쿼리로 작성하려면 필요한 어노테이션
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscirbe(int fromUserId, int toUserId); 
	
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId= :fromUserId AND toUserId= :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId= :principalId AND toUserId= :pageUserId", nativeQuery= true)
	int mSubscribeState(int principalId, int pageUserId);
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId= :pageUserId", nativeQuery= true)
	int mSubscribeCount(int pageUserId);
}
