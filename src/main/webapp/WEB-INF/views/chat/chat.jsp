<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../layout/header.jsp"%>
<!--  디엠 틀 -->
<div class="chat-container">
	
	<!-- 디엠창 왼쪽(채팅리스트) -->
	<section class="chatlist">
		<!--  채팅리스트 상단 -->
		<div class="chatlist-top">
			<div class="cl-top-username"> Nani </div>
			<div class="cl-message"> 메시지 </div>
		</div>
		<!-- 채팅리스트 -->
		<div class="chatlist-bottom">
			<div class="cl-profile">
				<div class= "cl-profile-left>"></div>
				<div class= "cl-profile-right>"></div>
			</div>
		</div>
	</section>
	
	<!-- 디엠창 오른쪽 -->
	<section class="chatroom">
		<div class="chatroom-top">
			<div class="cr-top-profile"></div>
			<div class="cr-top-username"></div>
		</div>
		
		<!-- 실제 채팅방 -->
		<div class="chatroom-middle">
			<!-- 내가 쓴 메세지 -->
			<div class="cr-m-mymessage"></div>
			<!-- 상대가 쓴 메세지 -->
			<div class="cr-m-message"></div>
			<!-- 시간 -->
			<div class="cr-m-time"></div>
		</div>
		
		<!-- 채팅 입력창 -->
		<div class="chatroom-bottom">
			<div class="cr-bt-input">
				<div class="input-container">
					<input type="text" placeholder="메세지 입력...">
					<button type="button" onclick="">전송</button>
				</div>
			</div>
		</div>
	</section>
</div>