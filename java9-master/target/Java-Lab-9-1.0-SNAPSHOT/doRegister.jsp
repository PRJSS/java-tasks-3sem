<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ad" uri="http://tag/ad"%>
<fmt:requestEncoding value="UTF-8" />
<c:remove var="userData" />
<jsp:useBean id="userData" class="entity.User" scope="session" />
<jsp:setProperty name="userData" property="*" />
<ad:addUser user="${userData}" />
<c:choose>
<c:when test="${sessionScope.errorMessage==null}">
<c:remove var="userData" scope="session" />
<jsp:forward page="/doLogin.jsp" />
</c:when>
<c:otherwise>
	<c:redirect url="/register.jsp" />
</c:otherwise>
</c:choose>
