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
	<c:if test="${error.length() > 0}">
		<div class="alert alert-warning">
		    <a href="#" class="close" data-dismiss="alert">&times;</a>
		    <strong>Warning!</strong> There was a conflict with the timetable entry.
		</div>
	</c:if>
	<h2>Add a TimeTable</h2>
	
	<c:url var="addAction" value="/HOD/addTimeTable" ></c:url>
	<form:form action="${addAction}" modelAttribute="timetable" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="day">
					<spring:message text="Day"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="day" multiple="false">
			    	<form:option value="MON" label="MON" />
			    	<form:option value="TUE" label="TUE" />
			    	<form:option value="WED" label="WED" />
			    	<form:option value="THU" label="THU" />
			    	<form:option value="FRI" label="FRI" />
				</form:select>
		      </div>
		    </div>
   		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="time">
					<spring:message text="Time"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="time" multiple="false">
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
		       	<label class="control-label col-sm-2">
					<spring:message text="Semester"/>
				</label>
		      <div class="col-sm-4">
		        <select id="semester" class="form-control" >
		            	<option value="NONE">---Select---</option>
				    	<option value="1">Sem1</option>
				    	<option value="2">Sem2</option>
				    	<option value="3">Sem3</option>
				    	<option value="4">Sem4</option>
				    	<option value="5">Sem5</option>
				    	<option value="6">Sem6</option>
				    	<option value="7">Sem7</option>
				</select>
		      </div>
		    </div>
   	        <div class="form-group" id="lectures">
	        	<form:label class="control-label col-sm-2" path="lectureId">
					<spring:message text="Lecture"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select id="lecture_select" class="form-control" path="lectureId" multiple="false">
		        <form:option value="0" label="---Select---" />
				    <c:forEach var="lecture" items="${lectureList}">
				    
				    	<form:option value="${lecture.id}" class="${lecture.subject.semester}" label="${lecture.subject.name}" />
				    
				    </c:forEach>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add TimeTable"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
	<h3>TimeTable List</h3>
	<c:if test="${!empty listTimeTables}">
		<table class="tg row-border hover" id="timetables_list">
			<thead>
				<tr>
					<th width="80">Lecture</th>
					<th width="80">Faculty</th>
					<th width="80">Subject</th>
					<th width="80">Department</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Lecture</th>
					<th width="80">Faculty</th>
					<th width="80">Subject</th>
					
					<th width="80">Department</th>
					<th width="120">Day</th>
					<th width="120">Time</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listTimeTables}" var="timetable">
			<tr>
				<td>${timetable.lecture.id}</td>
				<td>${timetable.lecture.user.username}</td>
				<td>${timetable.lecture.subject.name}</td>
				
				<td>${timetable.lecture.subject.department.name}</td>
				<td>${timetable.day}</td>
				<td>${timetable.time}</td>
				<td><a href="<c:url value='edit/timetable/${timetable.id}' />" >Edit</a></td>
				<td><a href="<c:url value='remove/timetable/${timetable.id}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#lectures').hide();
	    var table = $('#timetables_list').DataTable({
	    	
	    });
	    
	    // Setup - add a text input to each footer cell
	    $('#timetables_list tfoot th').each( function () {
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
	    
	    // Display subjects based on semester
	    $('#semester').on('change',function(){
    		$('#lectures').show();
	    	var selected_sem = this.value;
	    	document.getElementById("lecture_select").options[0].selected = true;
	    	$('#lecture_select option').hide();
	    	for(i=0;i<$('#lecture_select option').length;i++)
	    		{
	    		if($('#lecture_select option')[i].className == selected_sem)
	    			{
	    			$('#lecture_select option')[i].style.display= "block";
	    			}
	    		}
	    });
	    if('${status}' == 'removeTimeTable'){
	    	setTimeout(
	    			  function() 
	    			  {
	    				  $.notify('TimeTable Removed', 'warn');
	    			  }, 2000);
	    }
	    if('${status}' == 'addTimeTable'){
	    	setTimeout(
	    			  function() 
	    			  {
	    				  $.notify('TimeTable Added', 'success');
	    			  }, 2000);
	    }
	} );
	</script>
</body>
</html>