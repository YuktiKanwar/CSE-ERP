<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Faculty Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<br>
	<h3>Lectures List</h3>
	<c:if test="${!empty listLectures}">
		<table class="tg row-border hover" id="lectures_list">
			<thead>
				<tr>
					<th width="80">Faculty</th>
					<th width="120">Subject</th>
					<th width="120">Semester</th>
					<th width="120">Department</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Faculty</th>
					<th width="120">Subject</th>
					<th width="120">Semester</th>
					<th width="120">Department</th>
				</tr>
			</tfoot>
			<c:forEach items="${listLectures}" var="lecture">
			<tr>
				<td>${lecture.user.username}</td>
				<td>${lecture.subject.name}</td>
				<td>${lecture.subject.semester}</td>
				<td>${lecture.subject.department.name}</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
		if("${error}" != "" )
			alert("${error}");
		
		$('#subjects').hide();
	    var table = $('#lectures_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#lectures_list tfoot th').each( function () {
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