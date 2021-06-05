<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Обработать параметр сортировки --%>
<c:if test="${param.sort!=null}">
	<c:set var="sort" scope="session" value="${param.sort}" />
</c:if>
<%-- Обработать параметр направления сортировки --%>
<c:if test="${param.dir!=null}">
	<c:set var="dir" scope="session" value="${param.dir}" />
</c:if>
<%-- Общая декоративная "шапка" для всех страниц --%>
<div style="background-color: #a0c8ff; padding: 10px;">
	<img src="https://fainaidea.com/wp-content/uploads/2014/04/783_2.jpg" width="50" height="53" border="0" align="left" alt="image">
	<div style="font-family: 'Trebuchet',sans-serif; font-size: 30px; height: 53px; margin-left: 80px;">
		Доска объявлений
	</div>
</div>
<%-- Панель отображается если пользователь аутентифицирован --%>
<c:if test="${sessionScope.authUser!=null}">
	<div style="background-color: #ccc; padding: 5px">
		<div style="float: right; margin-right: 5px">
			[ <a href="<c:url value="/doLogout.jsp" />">Exit</a> ]
		</div>
		<div style="float: right; margin-right: 5px">
			[ <a href="<c:url value="/cabinet.jsp" />">Вернуться в кабинет</a> ]
		</div>
		<div style="float: right; margin-right: 5px">
			[ <a href="<c:url value="/index.jsp" />">На главную</a> ]
		</div>
		Вы вошли как:
		<c:out value="${sessionScope.authUser.name}" />
		(
		<c:out value="${sessionScope.authUser.login}" />
		)
	</div>
</c:if>