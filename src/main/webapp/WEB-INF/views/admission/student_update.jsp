<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Student Update Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Update Student</h2>
	<c:url var="addAction" value="/Admission/updateStudent" ></c:url>
	<form:form action="${addAction}" modelAttribute="student" class="form-horizontal">
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
				<form:input path="firstName" class="form-control" placeholder="Enter FirstName" required="true"/>
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
				<form:input path="rollNo" class="form-control" placeholder="Enter RollNumber" required="true" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="email">
					<spring:message text="Email Address"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="email" class="form-control" placeholder="Enter Email"  type="email" required="true" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="semester">
					<spring:message text="Semester"/>
				</form:label>
		      <div class="col-sm-4">
				<form:select id="subject_select" class="form-control" path="semester" multiple="false">
				    <form:option value="0" label="---Select---" />
			    	<form:option value="1" label="1" />
			    	<form:option value="2" label="2" />
			    	<form:option value="3" label="3" />
			    	<form:option value="4" label="4" />
			    	<form:option value="5" label="5" />
			    	<form:option value="6" label="6" />
			    	<form:option value="7" label="7" />
			    	<form:option value="8" label="8" />
				</form:select>
		      </div>
		    </div>
	        <div class="form-group">
	        	<form:label class="control-label col-sm-2" path="departmentId">
					<spring:message text="Department"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="departmentId" multiple="false">
		        	<form:option value="0" label="---Select---" />
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
	<script type="text/javascript">
	$(document).ready(function() {
	} );
	</script>
</body>
</html>