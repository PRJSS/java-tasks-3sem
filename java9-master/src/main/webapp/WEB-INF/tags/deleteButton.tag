<%@ tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="ad" required="true" rtexprvalue="true"
	type="entity.Ad"%>
<%-- Кнопка удаления показывается только если:
1) пользователь аутентифицирован (authUser!=null);
2) передано текущее объявление (ad!=null);
3) id автора объявленния и id аутентифицированного пользователя
совпадают --%>
<c:if
	test="${sessionScope.authUser!=null && ad!=null &&ad.authorId==sessionScope.authUser.id}">
	<div
		style="float: right; margin: 0px 0px 0px;padding: 0px 0px; border: 1px solid black; background-color: #ffdd0f; width: 150px; text-align: center">
		<a
			href="<c:url value="/doDeleteAd.jsp">
<c:param name="id" value="${ad.id}" />
</c:url>">Удалить</a>
	</div>
</c:if>
