package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
				uniqueConstraints = {
						@UniqueConstraint(
								name="subscribe_uk",
								columnNames= {"fromUserId", "toUserId"}
						)
				}
		)
public class Subscribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략
	private int id;
	
	@JoinColumn(name="fromUserId") 
	@ManyToOne
	private User fromUser; 	// 구독을 하는 사람
	
	@JoinColumn(name="toUserId")
	@ManyToOne
	private User toUser;			// 구독을 할 대상
	
	
	private LocalDateTime createDate;
		
		@PrePersist // 디비에 INSERT되기 전에 실행
		public void createDate() {
			this.createDate = LocalDateTime.now();
		}
}
