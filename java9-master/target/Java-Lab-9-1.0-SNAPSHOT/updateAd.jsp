<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Импортировать собственную библиотеку теговых файлов --%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%-- Импортировать собственную библиотеку тегов --%>
<%@taglib prefix="ad" uri="http://tag/ad"%>
<%-- Проанализировать значение параметра id. Если он пустой или нулевой - то объявление создаѐтся --%>
<c:choose>
	<c:when test="${param.id==null || param.id==0}">
		<%-- Установить значение переменной title равным "Создание" --%>
		<c:set var="title" scope="page" value="Создание" />
	</c:when>
	<c:otherwise>
		<%-- Установить значение переменной title равным "Редактирование" --%>
		<c:set var="title" scope="page" value="Редактирование" />
		<%-- Если в сессии не сохранилось данных с предыдущей неудачнойпопытки --%>
		<c:if test="${sessionScope.errorMessage==null}">
			<%-- Получить bean требуемого объявления --%>
			<ad:getAds id="${param.id}" var="ad" />
			<%-- Сохранить его в контексте сессии --%>
			<c:set var="adData" scope="session" value="${ad}" />
		</c:if>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<title><c:out value="${title}" /> объявления</title>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
	<%-- Подключить заголовок страницы --%>
	<jsp:include page="/static/header.jsp"></jsp:include>
	<%-- Вставить разметку 1-колоночной страницы --%>
	<my:layout1Column>
		<h1>
			<c:out value="${title}" />
			объявления
		</h1>
		<%-- Вставить тег отображения сообщения об ошибке --%>
		<my:errorMessage />
		<%-- Отобразить форму редактирования объявления (с подстановкой переданных данных) --%>
		<form action="doUpdateAd.jsp" method="post">
			<%-- Если переданный параметр id больше нуля (т.е. мы редактируем объявление), то сохранить его в невидимом поле --%>
			<c:if test="${param.id>0}">
				<input type="hidden" name="id" value="${param.id}">
			</c:if>
			<table>
				<tr>
					<td>Заголовок:</td>
					<%-- Начальное значение поля ввода равно текущей теме объявления --%>
					<td><input type="text" name="subject" value="${sessionScope.adData.subject}" style="width: 90%"></td>
				</tr>
				<tr>
					<td>Текст:</td>
					<%-- Начальное значение области текста равно текущему тексту объявления --%>
					<td><textarea name="body" rows="10" cols="80" style="width: 90%">
							<c:out value="${sessionScope.adData.body}" />
						</textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Сохранить"> <%-- При нажатии на кнопку "Отменить" возвращаемся на страницу кабинета --%>
						<input type="button" value="Отменить" onclick="window.location='<c:url value="/cabinet.jsp" />'">
					</td>
				</tr>
			</table>
		</form>
	</my:layout1Column>
	<%-- Вставить нижний заголовок страницы --%>
	<%@ include file="/static/footer.jsp"%>
</body>
</html>
