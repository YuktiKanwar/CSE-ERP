<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Book Issue Page</title>
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
	<h3>Issued Books</h3>
	<c:if test="${!empty studentBooks}">
		<table class="mdl-data-table row-border hover" id="studentBook_list">
			<thead>
				<tr>
					<th width="80">Book Title</th>
					<th width="120">Subject</th>
					<th width="120">Student Name</th>
					<th width="120">Department</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Book Title</th>
					<th width="120">Subject</th>
					<th width="120">Student Name</th>
					<th width="120">Department</th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${studentBooks}" var="studentBook">
			<tr>
				<td>${studentBook.book.title}</td>
				<td>${studentBook.book.subject.name}</td>
				<td>${studentBook.student.firstName}</td>
				<td>${studentBook.student.department.name}</td>
				<td><a href="<c:url value='/Library/remove/${studentBook.id}'/>"><button class="btn btn-sm btn-warning">Retrieve</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    var table = $('#studentBook_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#studentBook_list tfoot th').each( function () {
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
		if('${message}' == 'Book Retrieved.'){
		    setTimeout(
		  			  function() 
		  			  {
		  				  $.notify('${message}', 'success');
		  			  }, 1000);
		}

	} );
	</script>
</body>
</html>