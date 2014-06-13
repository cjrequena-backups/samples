<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<util:load-scripts />
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div class="navbar-collapse collapse">
				<form:form class="navbar-form navbar-right" role="form" method="GET"
					action="test.html" commandName="test">
					<div class="form-group" style="margin-right: 5px;">
						<input type="text" placeholder="Email" class="form-control" />
					</div>
					<div class="form-group" style="margin-right: 5px;">
						<input type="password" placeholder="Password" class="form-control" />
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form:form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>






</body>
</html>
