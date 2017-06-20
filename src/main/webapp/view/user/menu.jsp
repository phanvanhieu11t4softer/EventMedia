<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty pageContext.request.userPrincipal.name}">
	<div class="navbar-header collapse navbar-collapse menu-admin">
		<a href="${pageContext.request.contextPath}/upload"><button
				type="button" class="btn">Upload Image</button></a> <a
			href="${pageContext.request.contextPath}/manageImage"><button
				type="button" class="btn">Manage Image</button></a>

		<div class="dropdown">
			<a><button
				type="button" class="btn">Funny</button></a>
			<div class="dropdown-content">
				<a href="${pageContext.request.contextPath}/funny_girl">Girl</a> <br>
				<a href="${pageContext.request.contextPath}/funny_yummy">Yummy</a> <br>
				<a href="${pageContext.request.contextPath}/funny_anime">Anime & Manga</a> <br>
				<a href="${pageContext.request.contextPath}/funny_marvel">Marvel & DC</a>
			</div>
		</div>
		<button type="button" class="btn">My group</button>
		<button type="button" class="btn">Persional</button>
	</div>
</c:if>
