<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>User Page</title>
</head>
<body>
<div class="container">
	<h2>Add a User</h2>
	<c:url var="addAction" value="/User/add" ></c:url>
	<form:form action="${addAction}" modelAttribute="user" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="username">
					<spring:message text="Name"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="username" class="form-control" placeholder="Enter Username" />
		      </div>
		    </div>
		    <div class="form-group">
				<form:label path="password" class="control-label col-sm-2">
					<spring:message text="Password"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:input type="password" path="password" class="form-control" placeholder="Enter password" />
		      </div>
		    </div>
	        <div class="form-group">
	        	<form:label class="control-label col-sm-2" path="enabled">
					<spring:message text="Enabled?"/>
				</form:label>
		      <div class="col-sm-offset-1 col-sm-4">
		        <div class="checkbox">
					<form:checkbox path="enabled" />
		        </div>
		      </div>
		    </div>
	        <div class="form-group">
	        	<form:label class="control-label col-sm-2" path="userRoleString">
					<spring:message text="Role:"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="userRoleString" multiple="true">
				    <form:options items="${rolesList}"/>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add User"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
	<h3>Users List</h3>
	<c:if test="${!empty listUsers}">
		<table class="tg row-border hover" id="user_list">
			<thead>
				<tr>
					<th width="80">Enabled</th>
					<th width="120">User Name</th>
					<th width="120">User Password</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
					<th width="120">User Role</th>
				</tr>
			</thead>
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
</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#user_list').DataTable({
	        
	        
	    });
	} );
	</script>
</body>
</html>