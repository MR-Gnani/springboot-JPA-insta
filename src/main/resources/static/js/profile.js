/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
  (2) 구독자 정보 모달 보기
  (3) 구독자 정보 모달에서 구독하기, 구독취소
  (4) 유저 프로필 사진 변경
  (5) 사용자 정보 메뉴 열기 닫기
  (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달 
  (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "팔로우취소") {
		
		$.ajax({
			type: "delete",
			url: "/api/subscribe/"+toUserId,
			dataType: "json"
		}).done(res=> { 
			$(obj).text("팔로잉");
			$(obj).toggleClass("blue");			
		}).fail(error=> {
			console.log("팔로우취소실패", error);
		});

	} else {
		
		$.ajax({
			type: "post",
			url: "/api/subscribe/"+toUserId,
			dataType: "json"
		}).done(res=> { 
			$(obj).text("팔로우취소");
			$(obj).toggleClass("blue");			
		}).fail(error=> {
			console.log("팔로우실패", error);
		});
	}
}

// 게시글 상세 창 보기
function contentsModalOpen(imageId) {
	$(".modal-contents").css("display", "flex");
	
	$.ajax({
		url: `/api/user/${imageId}/contents`,
		dataType: "json"
	}).done(res=>{
		console.log(res);
		let image = res.data;
		let contentsItem = getContentsModalItem(image);
		$("#contentsModalList").append(contentsItem);
	}).fail(error=>{
		console.log("오류", error);
	}); 
}

function getContentsModalItem(image) {
	let item=`			
			<div class="contents-header">
				<span> ${image.user.username} </span>
				<button onclick="modalClose()">
					<i class="fas fa-times"></i>
				</button>
			</div>
			
			
			<div class="contents-body" >
			
				
				<div class="contents-photo">
				  
				<img src="/upload/${image.postImageUrl}" />
				</div>
					
				
				<div class="contents-details">
				
					<div class="post-content">
						<p>${image.caption}</p>
					</div>`;
					
					image.comments.forEach((comment)=>{
						item+=`
					<div class="comment" id="contentsCommentItem-${comment.id}">
						<p> 
							<b><span class="user-username">${comment.user.username}</span> :</b> ${comment.content}.
						</p>`;
					
						if(principalId == comment.user.id){
							item+= `<button onclick="deleteComment(${comment.id})">
										  <i class="fas fa-times"></i>
										  </button>`;
							}
				
						item+= `
						</div>`;
						
					});
					
					item+=
					`<div class="comments-icon">
						<button>`;
						
							if(image.likeState){
								item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
							}else{
								item += `<i class="far fa-heart " id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
							}
							
					item +=`
						</button>
					</div>
					
					<span class="like"><b id="contentsLikeCount-${image.id}">${image.likeCount} </b>likes</span>
					        
					<div class="comments-input">
						<input type="text" placeholder="댓글 달기..." id="CommentsInput-${image.id}" />
						<button type="button" onClick="addComment(${image.id})">게시</button>
					</div>
					
				</div>
		</div>`
		return item;
}

// +좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	
	if (likeIcon.hasClass("far")) { //좋아요가 안된 상태, 좋아요 누르겠다.
		
		$.ajax({
			type: "post",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(res=>{
			
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) + 1;
			console.log("좋아카운트", likeCount);
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error=>{
			console.log("오류", error);
		});
		
	} else { //좋아요가 된 상태, 좋아요 취소하겠다.
		
		$.ajax({
			type: "delete",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(res=>{
					
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			console.log("좋아카운트", likeCount);
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error=>{
			console.log("오류", error);
		});
	}
}


// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
	$(".modal-subscribe").css("display", "flex");
	
	$.ajax({
		url: `/api/user/${pageUserId}/subscribe`,
		dataType: "json"
	}).done(res=>{
		console.log(res.data);
		
		res.data.forEach((u)=>{
			let item = getSubscribeModalItem(u);
			$("#subscribeModalList").append(item);
		});
	}).fail(error=>{
		console.log("팔로잉정보불러오기오류", error);
	});
}

function getSubscribeModalItem(u) {
	console.log(u);
	let item = `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
	<div class="subscribe__img">
		<img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/person.jpeg'"/>
	</div>
	<div class="subscribe__text">
		<h2><span class="user-username" onclick="goToUserProfile(${u.id})">${u.username}</span></h2>
	</div>
	<div class="subscribe__btn">`;
	
	if(!u.equalUserState){ // 동일유저가 아닐 때 버튼 생성
		if(u.subscribeState){ // 팔로우되어 있으면 
			item+=`<button class="cta blue" onclick="toggleSubscribe(${u.id}, this)">팔로우취소</button>`;
		}else{ // 팔로우가 안되어 있으면
			item+=`<button class="cta" onclick="toggleSubscribe(${u.id}, this)">팔로잉</button>`;
		}
	}
	item+=`
	</div>
</div>`;
	return item;
}

// 유저 프로필 페이지로 이동
function goToUserProfile(userId) {
 
  let userProfileUrl = `/user/${userId}`;
  
  window.location.href = userProfileUrl;
}

// (3) 유저 프로파일 사진 변경 (완)
function profileImageUpload(pageUserId, principalId) {
	
	// console.log("pageUserId", pageUserId);
	// console.log("principalId", principalId);
	
	// 다른 프로필 유저의 사진을 클릭했을때 프로필 변경 못하게 하는것
	// 하지만 내생각에는 애초에 다른 회원의 프로필을 클릭했을 때 변경 모달을 호출 시키지 않고 사진만 응답시키고 싶음
	if(pageUserId != principalId){
		alert("프로필 사진을 수정할 수 없는 유저입니다.")
		return;
	}
	
	$("#userProfileImageInput").click();
	
	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		// 서버에 이미지를 전송
		let profileImageForm = $("#userProfileImageForm")[0];
		
		// FormData 객체를 이용하면 form태그 필드와 그 값을 나타내는 key/value 쌍을 담을 수 있다.
		let formData = new FormData(profileImageForm);
		
		$.ajax({
			type: "put",
			url: `/api/user/${principalId}/profileImageUrl`,
			data: formData,
			contentType: false, //필수 x-www-form-urlencoded로 파싱되는 것을 방지
			processData: false, //필수 contentType를 false로 주면 QueryString으로 설정되는데 이것도 방지
			enctype:  "multipart/form-data",
			dataType: "json"
		}).done(res=>{
			// 사진 전송 성공시 이미지 변경
			let reader = new FileReader();
			reader.onload = (e) => {
				$("#userProfileImage").attr("src", e.target.result);
				}
			reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
		}).fail(error=>{
			console.log("오류", error);
		});
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo(pageUserId, principalId) {
		console.log("pageUserId", pageUserId);
	    console.log("principalId", principalId);
		$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 구독자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






