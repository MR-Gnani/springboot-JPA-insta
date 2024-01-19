package com.cos.photogramstart.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	@Value("${file.path}") // yml의 file경로를 가져오는 어노테이션
	private String uploadFolder;
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID(); 
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		System.out.println(imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신 또는 I/O가 일어날 때는 예외 발생 가능성 o
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		System.out.println(imageEntity);
	}
}
