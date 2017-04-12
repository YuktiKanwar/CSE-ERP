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
	<h2>Select a Book</h2>
	<br>
	<h3>Books List</h3>
	<c:if test="${!empty listBooks}">
		<table class="mdl-data-table row-border hover" id="book_list">
			<thead>
				<tr>
					<th class="hidden"></th>
					<th width="80">Title</th>
					<th width="120">Description</th>
					<th width="80">Subject</th>
					<th width="120">Count</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="hidden"></th>
					<th width="80">Title</th>
					<th width="120">Description</th>
					<th width="80">Subject</th>
					<th width="120">Count</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listBooks}" var="book">
			<tr>
				<td class="hidden">${book.id}</td>
				<td>${book.title}</td>
				<td>${book.desc}</td>
				<td>${book.subject.name}</td>
				<td>${book.count}</td>
				<td>${book.semester}</td>
				<td>${book.department.name}</td>
				<c:if test="${book.count != 0}">
				<td><a href="<c:url value='issue/${book.id}'/>" ><button class="btn btn-success">Select</button></a></td>
				</c:if>
				<c:if test="${book.count == 0}">
				<td><a href="<c:url value='#'/>" ><button class="btn btn-warn">Unavailable</button></a></td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    var table = $('#book_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#book_list tfoot th').each( function () {
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
		if('${message}' == 'Book Issued'){
		    setTimeout(
		  			  function() 
		  			  {
		  				  $.notify('${message}', 'warn');
		  			  }, 1000);
		}
	} );
	</script>
</body>
</html>