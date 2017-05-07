<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>HOD Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	.hidden{
	display:none;
	}
	</style>
</head>
<body>
<div class="container-fluid">
	<br>
	<h3>Faculty List</h3>
	<c:if test="${!empty facultyList}">
		<table class="mdl-data-table row-border hover" id="student_list">
			<thead>
				<tr>
					<th width="80">Name</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Name</th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${facultyList}" var="faculty">
			<tr>
				<td>${faculty.username}</td>
				<td><a href="<c:url value='./${faculty.username}'/>"><button class="btn btn-sm btn-warning">Select</button></td>
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