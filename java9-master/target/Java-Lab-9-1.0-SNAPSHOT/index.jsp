<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ad" uri="http://tag/ad"%>
<!DOCTYPE html>
<html>
<head>
<title>Главная страница</title>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
	<jsp:include page="/static/header.jsp"></jsp:include>
	<h1>
		Главная страница
	</h1>
	<my:layout2Columns leftColumnWidth="68%" rightColumnWidth="28%">
		<jsp:attribute name="leftColumnBody">
<ad:getAds range="all" var="adListing" sort="${sessionScope.sort}"
				dir="${sessionScope.dir}" />
<my:adListing adListing="${adListing}" editMode="false" />
</jsp:attribute>
		<jsp:attribute name="rightColumnBody">
<my:errorMessage/>
<my:loginForm>
<jsp:attribute name="processor">
<c:url value="/doLogin.jsp" />
</jsp:attribute>
</my:loginForm>
<my:registerButton>
<jsp:attribute name="processor">
<c:url value="/register.jsp" />
</jsp:attribute>
</my:registerButton>
</jsp:attribute>
	</my:layout2Columns>
	<%@ include file="/static/footer.jsp"%>
</body>
</html>
