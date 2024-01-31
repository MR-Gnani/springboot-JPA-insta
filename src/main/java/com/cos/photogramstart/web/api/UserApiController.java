package com.cos.photogramstart.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.Service.ImageService;
import com.cos.photogramstart.Service.SubscribeService;
import com.cos.photogramstart.Service.UserService;
import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService; // DI
	private final SubscribeService subscribeService;
	private final ImageService imageService;
	
	@GetMapping("/api/user/{imageId}/contents")
	public ResponseEntity<?> contents(@PathVariable int imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Image images = imageService.게시글상세보기(imageId);
		return new ResponseEntity<>(new CMRespDto<>(1, "성공", images), HttpStatus.OK);
	}
	
	@PutMapping("api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
		principalDetails.setUser(userEntity); // 세션 변경
		return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진 변경 성공", null), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		List<SubscribeDto> subscribeDto = subscribeService.팔로잉리스트(principalDetails.getUser().getId(), pageUserId);
	
		return new ResponseEntity<>(new CMRespDto<>(1, "팔로잉 리스트 불러오기 성공", subscribeDto), HttpStatus.OK);
	}
	
	@PutMapping("/api/user/{id}")
		public CMRespDto<?> update(
				@PathVariable int id, 
				@Valid UserUpdateDto userUpdateDto,
				BindingResult bindingResult, // 꼭 valid가 적혀있는 다음 파라미터에 적어야함
				@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
				User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
				principalDetails.setUser(userEntity); // 세션정보 변경
				return new CMRespDto<>(1, "회원수정완료", userEntity); 
				// 응답시에 userEntity의 모든 getter함수가 호출되고 JSON데이터로 파싱하여 응답한다.
			
	  }
}
