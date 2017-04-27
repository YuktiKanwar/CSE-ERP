<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<div class="col-md-12">
		<h3>Mark Attendance</h3>
	</div>
	<div class="col-md-3">
		<p>Faculty : ${facultyName}</p>
	</div>
	<div class="col-md-3">
		<p>Date : ${date}</p>
	</div>
	<br/>
	<c:if test="${!empty listStudents}">
		<table class="tg row-border hover" id="timetable_list">
			<thead>
				<tr>
					<th>StudentName</th>
					<th>Semester</th>
					<th>RollNumber</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>StudentName</th>
					<th>Semester</th>
					<th>RollNumber</th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listStudents}" var="student">
				<tr>
					<td>${student.firstName}</td>
					<td>${student.semester}</td>
					<td>${student.rollNo}</td>
					<td>
						<c:choose>
							<c:when  test="${listAttendance == []}">
								P<input onclick="saveAttendance(${timetable_id},${student.id},'P')" value="P" type="radio" name="attendance${student.id}"/>
								A<input onclick="saveAttendance(${timetable_id},${student.id},'A')" value="A" type="radio" name="attendance${student.id}"/>
							</c:when>
							<c:otherwise>
								<c:set var="flag" value="0"/>
								<c:forEach items="${listAttendance}" var="attendance">
									<c:if test="${attendance.student_id == student.id}">
										<c:set var="flag" value="1"/>
										<c:if test="${attendance.value == 'A'}">
											P<input onclick="saveAttendance(${timetable_id},${student.id},'P')" value="P" type="radio" name="attendance${student.id}"/>
											A<input onclick="saveAttendance(${timetable_id},${student.id},'A')" value="A" type="radio" checked='true' name="attendance${student.id}"/>
										</c:if>
										<c:if test="${attendance.value == 'P'}">
											P<input onclick="saveAttendance(${timetable_id},${student.id},'P')" value="P" type="radio" checked='true' name="attendance${student.id}"/>
											A<input onclick="saveAttendance(${timetable_id},${student.id},'A')" value="A" type="radio" name="attendance${student.id}"/>
										</c:if>
									</c:if>
								</c:forEach>
								<c:if test="${flag == 0}">
									P<input onclick="saveAttendance(${timetable_id},${student.id},'P')" value="P" type="radio" name="attendance${student.id}"/>
									A<input onclick="saveAttendance(${timetable_id},${student.id},'A')" value="A" type="radio" name="attendance${student.id}"/>
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
		</c:forEach>
		</table>
	</c:if>
	<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
	<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
</div>
	<script type="text/javascript">
	var token = $('#csrfToken').val();
	var header = $('#csrfHeader').val();
	function saveAttendance(timetable_id,student_id,AorP){
		var date = '${date}';
		var params = "timetable_id="+timetable_id+"&student_id="+student_id+"&date="+date+"&value="+AorP;
		httpGetAsync('http://localhost:8080/ERPApp/Faculty/attendance/save/',
				params,
				function(res){
					$('[name=attendance'+res+']').each(function(){
						debugger
						if(this.value == 'P' && this.checked == true)
							$(this).parent().css({'background-color' : 'rgba(81, 255, 81, 0.32)'})
						else if(this.value == 'A' && this.checked == true)
							$(this).parent().css({'background-color' : 'rgba(255, 70, 70, 0.43)'})
					})
				});
	}
	
	function httpGetAsync(theUrl,params, callback)
	{
	    var xmlHttp = new XMLHttpRequest();
	    xmlHttp.onreadystatechange = function() { 
	        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
	            callback(xmlHttp.responseText);
	    }
	    xmlHttp.open("POST", theUrl, true); // true for asynchronous 
	    xmlHttp.setRequestHeader(header, token);
	    xmlHttp.setRequestHeader("Content-Type",'application/x-www-form-urlencoded;');
	    xmlHttp.send(params);
	}
	$(document).ready(function() {
		if("${error}" != "" )
			alert("${error}");
	    var table = $('#timetable_list').DataTable({
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#timetable_list tfoot th').each( function () {
	        var title = $(this).text();
	        if(title == ''){
	        //	var marked = '${fn:length(listAttendance)}';
	        //	var total = '${fn:length(listStudents)}';
	        //	$(this).html( '<p> Marked : ' +marked+ ' Total: '+ total + ' </p>' );
	        }
	        else
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