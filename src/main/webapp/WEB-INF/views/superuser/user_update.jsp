<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>User Update Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<div class="container">
	<h2>Update User</h2>
	<c:url var="updateAction" value="/User/update" ></c:url>
	<form:form action="${updateAction}" modelAttribute="user" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="id">
					<spring:message text="ID"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="id" readonly="true" size="8"  disabled="true" class="form-control"  />
				<form:hidden path="id" />
		      </div>
		    </div>
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
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Update User"/>" />
		      </div>
		    </div>
	</form:form>
</div>
</body>
</html>