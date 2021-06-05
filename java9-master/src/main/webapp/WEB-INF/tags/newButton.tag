<%@ tag pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Кнопка нового объявления показывается только если пользователь аутентифицирован (authUser!=null) --%>
<c:if test="${sessionScope.authUser!=null}">
	<div
		style="background-color: #ccc; border: 1px solid black; float: right; margin: 10px; margin-top: 20px; padding: 5px 0px; text-align: center; width: 150px;">
		<a href="<c:url value="/updateAd.jsp" />">Создать</a>
	</div>
</c:if>
