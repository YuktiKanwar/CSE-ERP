<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>TimeTable Update Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Update a TimeTable</h2>
	
	<c:url var="updateAction" value="/HOD/updateTimeTable" ></c:url>
	<form:form action="${updateAction}" modelAttribute="timetable" class="form-horizontal">
			<div class="form-group">
		    	<form:label class="control-label col-sm-2" path="id">
					<spring:message text="ID"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="id" readonly="true" size="8"  disabled="true" class="form-control"  />
				<form:hidden path="id" />
		      </div>
		    </div>
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
		        <input type="submit" class="btn btn-default" value="<spring:message text="Update TimeTable"/>" />
		      </div>
		    </div>
	</form:form>
	
</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#lectures').hide();
	    
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
	} );
	</script>
</body>
</html>