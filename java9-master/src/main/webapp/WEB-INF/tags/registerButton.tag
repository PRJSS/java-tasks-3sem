<%@ tag pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Атрибут processor содержит адрес страницы, которой будет адресован
запрос на регистрацию пользователя --%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>
<%-- Ссылка на регистрацию показывается только если в настоящий момент
пользователь не аутентифицирован,
т.е. в переменной authUser в области сессии не сохранѐн JavaBean
пользователя --%>
<c:if test="${sessionScope.authUser==null}">
	<a href="${processor}">Зарегистрироваться</a>
</c:if>
