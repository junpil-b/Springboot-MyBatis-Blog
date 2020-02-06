<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="contrainer">
	<form action ="/user/profile" method="POST" enctype="multipart/form-data">
		<div class="form-group">
			<label for="username">유저네임</label> 
			<input type="text" class="form-control" placeholder="Enter username" name="username" value="ssar" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="password">패스워드</label> 
			<input type="password" class="form-control" placeholder="Enter password" name="password" value="1234">
		</div>

		<div class="form-group">
			<label for="email">이메일</label> 
			<input type="text" class="form-control" placeholder="Enter email" name="email" value="ssar@naver.com" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="profile">프로필 사진</label> 
			<input type="file" class="form-control" name="profile" value="my.jpg">
		</div>
<!-- 파일이 없으면 json으로 보내도 되지만 있을 때  script에 multipart로 보낸다 여기선 사용 안 했다 -->	

		<button id="login--submit" class="btn btn-primary">수정</button>	
	</form>

</div>

<%@ include file="../include/footer.jsp"%>