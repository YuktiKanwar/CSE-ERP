<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Attendance Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	.hidden{
	display:none
	}
	</style>
</head>
<body>
<div class="container">
	<br>
	<div class="col-md-4">
		<h3>Mark Attendance</h3>
		<div class="input-group">
			<input type="text" class="form-control" name="date" placeholder="Select Date" id="date" data-select="date" >
			<span class="input-group-btn">
				<button class="btn btn-primary btn-date" style="padding: 9px;" data-toggle="select" type="button"><i class="fa fa-calendar"></i></button>
			</span>
		</div>
	<br/>
	</div>
	<c:if test="${!empty listTimeTables}">
		<table class="tg row-border hover" id="timetable_list">
			<thead>
				<tr>
					<th width="80">Lecture</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Lecture</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listTimeTables}" var="timetable">
				<tr>
					<td>${timetable.lecture.subject.name}</td>
					<td class="row-day">${timetable.day}</td>
					<td>${timetable.time}</td>
					<td>
						<a onclick="markAttendance(${timetable.id})" >Mark</a>
					</td>
				</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">

	var dateString = "";
	function markAttendance(timetableid){
		dateString = dateString.replace('/','-');
		dateString = dateString.replace('/','-');
		window.location.href = "http://localhost:8080/ERPApp/Faculty/attendance/mark/" + timetableid + "/" + dateString; 
	}
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

		$('#timetable_list_wrapper').hide();
		$('#date').change(function(){
			dateString = $('#date')[0].value;
			var dateParts = dateString.split("/");
			var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]); // month is 0-based
			var day = dateObject.toString().slice(0,3);
			$('.row-day').each(function(){
				if(this.innerHTML != day.toUpperCase())
					{
						$(this).parent().addClass('hidden');
					}
				else{
					$(this).parent().removeClass('hidden');
				}
			});
			$('#timetable_list_wrapper').show();
		})
		
	} );
	</script>
</body>
</html>