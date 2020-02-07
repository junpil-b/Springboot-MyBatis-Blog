<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">
	<!-- - 부트스트랩, -- 직접 설정 -->
	<!-- css __ 자바스크립트 -- -->
	<!-- 데이터를 가지고 가는게 아니라 서버에 확인만 하면 되니까  post 방식  -->
	<form>
		<div class="form-group">
			<label for="username">유저네임</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username" maxlength ="15">
		</div>

		<div class="form-group">
			<label for="password">패스워드</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password" maxlength ="15">
		</div>

		<div class="form-group">
			<label for="email">이메일</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email" maxlength ="30">
		</div>
	</form>

	<button id="join--submit" class="btn btn-primary">회원가입</button>

</div>

 
<script src = "/js/join.js"> </script>


<%@ include file="../include/footer.jsp"%>