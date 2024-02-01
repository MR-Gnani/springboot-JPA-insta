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
