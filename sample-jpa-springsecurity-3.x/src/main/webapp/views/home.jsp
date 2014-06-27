<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="sp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<util:load-scripts />
<script src="<%=request.getContextPath()%>/resources/js/global-form-validator.js" type="text/javascript">
	
</script>

</head>

<!-- URLS -->
<sp:url value='/j_spring_security_check' var="login_proceed" />

<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Sample Spring Security</a>
			</div>
			<sec:authorize access="isAnonymous()">
				<div class="navbar-collapse collapse">
					<form id="login-form" name="login-form" class="navbar-form navbar-right" action="${login_proceed}" method="POST">
						<div class="form-group" style="margin-right: 5px;">
							<input id="j_username" name="j_username" type="text" placeholder="Email" class="form-control" />
						</div>
						<div class="form-group" style="margin-right: 5px;">
							<input id="j_password" name="j_password" type="password" placeholder="Password" class="form-control" />
						</div>
						<button type="submit" class="btn btn-success">Log in</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</div>
			</sec:authorize>
			<!--/.navbar-collapse -->
		</div>
	</div>

	<c:if test="${not empty messages}">
		<div class="alert alert-success" align="center">
			<c:forEach items="${messages}" var="message">
				<strong>${message}</strong>
				<br>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty warnings}">
		<div class="alert alert-warning" align="center">
			<c:forEach items="${warnings}" var="warning">
				<strong>${warning}</strong>
				<br>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty errors}">
		<div class="alert alert-danger" align="center">
			<c:forEach items="${errors}" var="error">
				<strong>${error}</strong>
				<br>
			</c:forEach>
		</div>
	</c:if>


</body>
</html>
