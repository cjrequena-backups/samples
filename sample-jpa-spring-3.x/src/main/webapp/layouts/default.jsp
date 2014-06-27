<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page session="false"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<section>
		<tiles:insertAttribute name="body" />
	</section>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>


</html>
