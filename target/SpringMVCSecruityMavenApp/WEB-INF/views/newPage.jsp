<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Only the admin(Mrs Thakur) will be able to see this page</h3>
<ul>
	<li>Not any user like Mr Thakur</li>
</ul>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>