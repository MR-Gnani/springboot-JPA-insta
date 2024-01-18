// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); // 폼태그 액션을 막아줌
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type: "put",
		url : `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{
		console.log("성공", res); // HttpStatus 상태코드가 200번대 일때 - done
		location.href = `/user/${userId}`;
	}).fail(error=>{
		if(error.data == null) {
			alert(error.responseJSON.message)
		} else {
			alert(JSON.stringify(error.reponseJSON.data));
		}
		// console.log("실패", error.reponseJSON.data); // HttpStatus 상태코드가 200번대가 아닐때 - fail
	});
	
}