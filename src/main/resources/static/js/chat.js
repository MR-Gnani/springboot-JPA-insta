let ws;


$(function(){     
	wsOpen();
})

function wsOpen(){ 
	
	console.log('wsopen 탔다');
	// 엔드포인트 수정해야할지도?
	ws = new WebSocket("ws://" + location.host + "/chat");
	wsEvt();
}

function wsEvt() {
	ws.onopen = function(data){
		//소켓이 열리면 초기화 세팅하기
	}

	ws.onmessage = function(data) {  
			console.log(data)
			let msg = data.data;
			if(msg != null && msg.trim() != ''){
				let d = JSON.parse(msg);
				
				console.log(d);
				if(d.type == "getId"){ 
					let si = d.sessionId != null ? d.sessionId : ""; //sessionId가 null이 아니면 d.sessionId가 null이면 빈 문자열이 할당
					if(si != ''){
						$("#sessionId").val(si); 
					}
				}else if(d.type == "message"){
					if(d.sessionId == $("#sessionId").val()){
								let msgItem = getMsgItem(d.msg);
									$("#chat-List").append(msgItem);
					}else{
							let msgItem = getMsgOtherItem(d.msg);
									$("#chat-List").append(msgItem);
					}
				}else{
					console.warn("unknown type!")
				}
			}
	}

	// 엔터를 누르면 전송 하게 함
	document.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ //enter press
			send();
		}
	});
}

function getMsgItem(msg) {
	let item=`
		<div class="cr-m-mymessage" id="mymsg-1">
			<div class="mm-message-box"> ${msg} </div>
		</div>
	`
	return item;
}

function getMsgOtherItem(msg) {
	let item=`
		<div class="cr-m-message" id="mst-1">
					<div class="m-message-box"> ${msg} </div>
				</div>
	`
	return item;
}


// 전송 기능
function send() {
	let msg = $("#chatinput").val();
	ws.send(msg);
	$('#chatinput').val(""); // 인풋 필드 비워줌
}