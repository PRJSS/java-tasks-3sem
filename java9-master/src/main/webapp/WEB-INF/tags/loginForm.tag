<%@ tag pageEncoding="UTF-8"%>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Входным атрибутом тега является processor - адрес страницы-обработчика
запроса на аутентификацию --%>
<%@ attribute name="processor" required="true" rtexprvalue="true"%>
<%-- Форма входа показывается только если в настоящий момент пользователь не
аутентифицирован --%>
<c:if test="${sessionScope.authUser==null}">
	<form action="${processor}" method="post">
		<table><%--!// border="0" cellspacing="0" cellpadding="5">--%>
			<tr>
				<td>Логин:</td>
				<td><input type="text" name="login" value=""></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Войти"></td>
			</tr>
		</table>
	</form>
</c:if>
