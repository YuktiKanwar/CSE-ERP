<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Person
</h1>

<c:url var="addAction" value="/User/add" ></c:url>

<form:form action="${addAction}" modelAttribute="user">
<table>

	<tr>
		<td>
			<form:label path="username">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="Password"/>
			</form:label>
		</td>
		<td>
			<form:input path="password" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="enabled">
				<spring:message text="Enabled?"/>
			</form:label>
		</td>
		<td>
			<form:checkbox path="enabled" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="userRoleString">
				<spring:message text="Role:"/>
			</form:label>
		</td>
		<td>
			<form:select path="userRoleString" multiple="true">
			    <form:options items="${rolesList}"/>
			</form:select>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="<spring:message text="Add Person"/>" />
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listUsers}">
	<table class="tg">
	<tr>
		<th width="80">Enabled</th>
		<th width="120">User Name</th>
		<th width="120">User Password</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
		<th width="120">User Role</th>
	</tr>
	<c:set var="index" value="0"/>
	<c:forEach items="${listUsers}" var="user" varStatus="loop">
		<tr>
			<td>${user.enabled}</td>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td><a href="<c:url value='edit/${user.username}' />" >Edit</a></td>
			<td><a href="<c:url value='remove/${user.username}' />" >Delete</a></td>
			<c:set var="userRoleArray" value="${userRoles.get(String.valueOf(loop.index))}"/>
			<td>
			<c:forEach items="${userRoleArray}" var="role"> 
			  ${role.getRole()}
			</c:forEach>
			</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>