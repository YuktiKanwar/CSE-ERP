<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Account Add Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container-fluid">
	<h2>Add an Account</h2>
	<c:url var="addAction" value="/Accounts/addAccount/${student_id}" ></c:url>
	<form:form action="${addAction}" modelAttribute="account" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="studentId">
					<spring:message text="StudentId"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="studentId" disabled="true" class="form-control" placeholder="Enter StudentId" required="true"/>
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="totalFee">
					<spring:message text="Total Fee"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="totalFee" class="form-control" placeholder="Enter Total Fee" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="submittedFee">
					<spring:message text="Submitted Fee"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="submittedFee" class="form-control" placeholder="Enter Submitted Fee" required="true" />
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add Account"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>