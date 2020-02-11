<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 스프링 시큐리티 전용 태그, pom.xml에도 추가해 줘야 한다  -->
<sec:authorize access="isAuthenticated()"> <!-- 시큐리티 컨텍스트에 세션이 있으면 true 없으면 false -->
    <sec:authentication property="principal" var="principal" /> <!--isAuthenticated 안에 있는 세션 값을 principal에 넣는다 ?-->
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css" /> 

</head>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<nav class="navbar navbar-expand-md bg-success navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="/">BLOG IT</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${not empty principal}">
						<li class="nav-item">
						<a class="nav-link" href="/post/write">글쓰기</a></li>

						<li class="nav-item">
						<a class="nav-link" href="/user/profile/${principal.id}">회원정보수정</a></li>
						
						<li class="nav-item">
						<a class="nav-link" href="/logout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
						<a class="nav-link" href="/user/join">JOIN</a></li>

						<li class="nav-item">
						<a class="nav-link" href="/user/login">LOGIN</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
			<img src="/media/${principal.profile}"  class="rounded-circle my__img ml-auto" 
			     width="40px" height="40px" onerror="javascript:this.src = '/images/unknown.jpg' " />

		</div>
	</nav>
	<br />