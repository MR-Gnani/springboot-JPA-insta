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
			<div class="cl-profile" id="test-1">
				<div class= "cl-profile-left">
					<div class="cl-profile-imagebox">
						<img class="cl-profile-image" src="/images/basicprofile.png"/>
					</div>
				</div>
				<div class= "cl-profile-right">
					<div class= "cl-proflie-username"> YD Backend Engineer </div>
				</div>
			</div>

	
		</div>
	</section>
	
	<!-- 디엠창 오른쪽 -->
	<section class="chatroom">
		<div class="chatroom-top">
			<div class="cr-profile">
				<div class= "cr-profile-left">
					<div class="cr-profile-imagebox">
						<img class="cr-profile-image" src="/images/basicprofile.png"/>
					</div>
				</div>
				<div class= "cr-profile-right">
					<div class="cr-profile-username"> YD Backend Engineer </div>
				</div>
			</div>
		</div>
		
		<!-- 실제 채팅방 -->
		<div class="chatroom-middle">
			<!-- 시간 -->
			<div class="cr-m-time"> 2024.02.02 (금) 15:44 </div>
			<input type="hidden" id="sessionId" value="">
			<!-- 테스트 -->
			<div class="chatting-test" id="chat-List">
				<!-- 내가 쓴 메세지 -->
			
				<!-- 상대가 쓴 메세지 -->
				
			</div> 
		
		</div>
		
		<!-- 채팅 입력창 -->
		<div class="chatroom-bottom">
			<div class="cr-bt-input">
				<div class="input-container">
					<input id="chatinput" type="text" placeholder="메세지 입력...">
					<button type="button" onclick="send()">전송</button>
				</div>
			</div>
		</div>
	</section>
</div>

<script src="/js/chat.js"></script>