<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="contrainer">
	<form>
		<div class="form-group">
			<label for="username">유저네임</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">패스워드</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
</div>

<button id="login--submit" class="btn btn-success">로그인</button>


<%@ include file="../include/footer.jsp"%>