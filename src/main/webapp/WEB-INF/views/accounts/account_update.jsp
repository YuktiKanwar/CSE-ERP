<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Account Update Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Update Account</h2>
	<c:url var="addAction" value="/Accounts/updateAccount/${studentId}" ></c:url>
	<form:form action="${addAction}" modelAttribute="account" class="form-horizontal">
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
		    	<form:label class="control-label col-sm-2" path="studentId">
					<spring:message text="Student_ID"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="studentId" disabled="true" class="form-control" placeholder="Enter Title" required="true"/>
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="totalFee">
					<spring:message text="Total Fee"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="totalFee" class="form-control" placeholder="Enter Number of Books" required="true" />
		      </div>
		    </div>
   		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="submittedFee">
					<spring:message text="Submitted Fee"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="submittedFee" class="form-control" placeholder="Enter Number of Books" required="true" />
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Update Account"/>" />
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