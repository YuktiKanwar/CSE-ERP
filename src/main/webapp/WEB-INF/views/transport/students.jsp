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
<div class="container-fluid">
	<br>
	<h3>Students List</h3>
	<c:if test="${!empty listStudents}">
		<table class="mdl-data-table row-border hover" id="student_list">
			<thead>
				<tr>
					<th width="80">FirstName</th>
					<th width="120">LastName</th>
					<th width="120">RollNo</th>
					<th width="120">Email</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
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
				<td><a href="<c:url value='view/${student.id}' />" >View Transport Details</a></td>
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
	    
	    if('${status}' == 'removeStudent'){
	    	setTimeout(
	    			  function() 
	    			  {
	    				  $.notify('Student Removed', 'warn');
	    			  }, 2000);
	    }
	    if('${status}' == 'addStudent'){
	    	setTimeout(
	    			  function() 
	    			  {
	    				  $.notify('Student Added', 'success');
	    			  }, 2000);
	    }
	    
	} );
	</script>
</body>
</html>