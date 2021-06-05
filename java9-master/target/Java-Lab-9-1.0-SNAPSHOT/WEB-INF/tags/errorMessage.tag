<%@ tag pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Проанализировать, сохранено ли в сессии сообщение об ошибке --%>
<c:if test="${sessionScope.errorMessage!=null}">
	<%-- Если да, то показать его --%>
	<div style="padding: 10px;">
		<span style="background-color: yellow;"> <c:out
				value="${sessionScope.errorMessage}" />
		</span>
	</div>
	<%-- А потом удалить --%>
	<c:remove var="errorMessage" scope="session" />
</c:if>
