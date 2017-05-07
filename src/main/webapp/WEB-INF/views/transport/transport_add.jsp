<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Transport Add Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container-fluid">
	<h2>Add a Transport Route</h2>
	<c:url var="addAction" value="/Transport/addTransport/${student_id}" ></c:url>
	<form:form action="${addAction}" modelAttribute="transport" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="studentId">
					<spring:message text="StudentId"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="studentId" disabled="true" class="form-control" placeholder="Enter StudentId" required="true"/>
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="routeNumber">
					<spring:message text="Route Number"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="routeNumber" class="form-control" placeholder="Enter Route Number" />
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add Transport"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>