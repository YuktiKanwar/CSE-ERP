<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>User Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Update a Student</h2>
	<c:url var="updateAction" value="/HOD/updateStudent" ></c:url>
	<form:form action="${updateAction}" modelAttribute="student" class="form-horizontal">
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
		    	<form:label class="control-label col-sm-2" path="firstName">
					<spring:message text="FirstName"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="firstName" class="form-control" placeholder="Enter FirstName" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="lastName">
					<spring:message text="LastName"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="lastName" class="form-control" placeholder="Enter LastName" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="rollNo">
					<spring:message text="RollNumber"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="rollNo" class="form-control" placeholder="Enter RollNumber" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="email">
					<spring:message text="Email Address"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="email" class="form-control" placeholder="Enter Email" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="semester">
					<spring:message text="Semester"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="semester" class="form-control" placeholder="Enter Semester" />
		      </div>
		    </div>
	        <div class="form-group">
	        	<form:label class="control-label col-sm-2" path="department">
					<spring:message text="Department:"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="department" multiple="false">
				    <form:options items="${departmentList}"/>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Update Student"/>" />
		      </div>
		    </div>
	</form:form>
</div>

</body>
</html>