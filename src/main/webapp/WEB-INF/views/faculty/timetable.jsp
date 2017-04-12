<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>TimeTable Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<br>
	<h3>TimeTable List</h3>
	<c:if test="${!empty listTimeTables}">
		<table class="tg row-border hover" id="timetable_list">
			<thead>
				<tr>
					<th width="80">Lecture</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Lecture</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
				</tr>
			</tfoot>
			<c:forEach items="${listTimeTables}" var="timetable">
			<tr>
				<td>${timetable.lecture.subject.name}</td>
				<td>${timetable.day}</td>
				<td>${timetable.time}</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
		if("${error}" != "" )
			alert("${error}");
	    var table = $('#timetable_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#timetable_list tfoot th').each( function () {
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