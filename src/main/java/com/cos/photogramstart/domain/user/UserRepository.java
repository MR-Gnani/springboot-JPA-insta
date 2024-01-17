package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션이 없어도 JPA리포지토리를 상속하면 자동으로 IOC등록이 된다.
// JPA REPOSITORY - CRUD 
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA Query Methods
	User findByUsername(String username);
	
}
