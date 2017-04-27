<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Fee Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container-fluid">
	<br>
	<h3>Fee Description</h3>
	<c:if test="${!empty account}">
		<table class="table">
			<thead>
				<th>Student Name</th>
				<th>Department</th>
				<th>Total Fee</th>
				<th>Submitted Fee</th>
				<th>Remaining Fee</th>
				<th></th>
			</thead>
			<tbody>
			<tr class="info">
				<td>${account.student.firstName}</td>
				<td>${account.student.department.name}</td>
				<td>${account.getTotalFee()}</td>
				<td>${account.getSubmittedFee()}</td>
				<td>${account.getTotalFee() - account.getSubmittedFee()}</td>
				<td><a href="<c:url value='../edit/${account.id}/${studentId}' />" >Edit</a></td>
			</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty account}">
		<p> There is no fee detail for this student </p>
		<a href="<c:url value='../add/${studentId}' />" > Add Detail</a>
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