<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Student Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Add a Student</h2>
	<c:url var="addAction" value="/Admission/addStudent" ></c:url>
	<form:form action="${addAction}" modelAttribute="student" class="form-horizontal">
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
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add Student"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
	<h3>Students List</h3>
	<c:if test="${!empty listStudents}">
		<table class="tg row-border hover" id="student_list">
			<thead>
				<tr>
					<th width="80">FirstName</th>
					<th width="120">LastName</th>
					<th width="120">RollNo</th>
					<th width="120">Email</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">FirstName</th>
					<th width="120">LastName</th>
					<th width="120">RollNo</th>
					<th width="120">Email</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listStudents}" var="student">
			<tr>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.rollNo}</td>
				<td>${student.email	}</td>
				<td>${student.semester}</td>
				<td>${student.department.name}</td>
				<td><a href="<c:url value='edit/${student.id}' />" >Edit</a></td>
				<td><a href="<c:url value='remove/${student.id}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    var table = $('#student_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#student_list tfoot th').each( function () {
	        var title = $(this).text();
	        $(this).html( '<input type="text" placeholder="'+title+'" style="width:120px" />' );
	    } );
	    
	    // Apply the search
	    table.columns().every( function () {
	        var that = this;
	 
	        $( 'input', this.footer() ).on( 'keyup change', function () {
	            if ( that.search() !== this.value ) {
	                that
	                    .search( this.value )
	                    .draw();
	            }
	        } );
	    });
	    
	} );
	</script>
</body>
</html>