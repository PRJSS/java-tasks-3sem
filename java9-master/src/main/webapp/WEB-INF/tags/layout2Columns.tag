<%@ tag pageEncoding="UTF-8"%>
<%-- Атрибуты тега - содержание левой колонки, содержание правой колонки,
ширина левой колонки, ширина правой колонки --%>
<%@ attribute name="leftColumnBody" required="false" rtexprvalue="true"%>
<%@ attribute name="leftColumnWidth" required="true" rtexprvalue="false"%>
<%@ attribute name="rightColumnBody" required="false" rtexprvalue="true"%>
<%@ attribute name="rightColumnWidth" required="true"
	rtexprvalue="false"%>
<div style="clear: both; width: 100%;">
	<div style="float: left; width: ${leftColumnWidth}">
		<div style="padding: 15px">${leftColumnBody}</div>
	</div>
	<div style="float: right; width: ${rightColumnWidth};">
		<div style="padding: 15px">${rightColumnBody}</div>
	</div>
	<div style="clear: both"></div>
</div>
