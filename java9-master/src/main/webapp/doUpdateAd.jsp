<%@page language="java" pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- Импортировать собственную библиотеку тегов --%>
<%@taglib prefix="ad" uri="http://tag/ad"%>
<%-- Указать, что мы ожидаем данные в кодировке UTF-8 --%>
<fmt:requestEncoding value="UTF-8" />
<%-- Очистить переменную перед использованием --%>
<c:remove var="adData" />
<%-- Проанализировать режим работы - изменение существующего или создание нового объявлений --%>
<c:choose>
	<%-- Если параметр id больше нуля (т.е. модифицируется существующее объявление) --%>
	<c:when test="${param.id>0}">
		<%-- то сначала извлечь его --%>
		<ad:getAds id="${param.id}" var="ad" />
		<%-- и переместить из page в session --%>
		<c:set var="adData" scope="session" value="${ad}" />
	</c:when>
	<c:otherwise>
		<%-- В противном случае создать новое объявление в области видимости сессии --%>
		<jsp:useBean id="adData" class="entity.Ad" scope="session" />
	</c:otherwise>
</c:choose>
<%-- Скопировать в bean параметры subject и body из HTTP-запроса --%>
<jsp:setProperty name="adData" property="subject" />
<jsp:setProperty name="adData" property="body" />
<%-- Вызвать собственный тег обновления объявления --%>
<ad:updateAd ad="${adData}" />
<%-- Проверить сообщение об ошибке, чтобы узнать результат операции --%>
<c:choose>
	<c:when test="${sessionScope.errorMessage==null}">
		<%-- Ошибок не возникло, очистить временные данные --%>
		<c:remove var="adData" scope="session" />
		<%-- Переадресовать на страницу личного кабинета --%>
		<c:redirect url="/cabinet.jsp" />
	</c:when>
	<c:otherwise>
		<%-- Переадресовать на страницу редактирования объявления --%>
		<c:redirect url="/updateAd.jsp">
			<%-- В качестве параметра при переадресации передать id объявления --%>
			<c:param name="id" value="${adData.id}" />
		</c:redirect>
	</c:otherwise>
</c:choose>
