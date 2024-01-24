package com.cos.photogramstart.domain.image;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query(value="SELECT * FROM image WHERE userId IN (SELECT touserid FROM subscribe WHERE fromUserid = :principalId) ORDER BY id DESC", nativeQuery = true)
	Page<Image> mStory(int principalId, Pageable pageable);
	
	@Query(value="SELECT i.* FROM image i JOIN likes l ON i.id = l.imageId GROUP BY i.id ORDER BY COUNT(l.id) DESC", nativeQuery = true)
	List<Image> mPopular();
}
