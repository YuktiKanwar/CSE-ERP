<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Lecture_Update Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container">
	<h2>Update a Lecture</h2>
	
	<c:url var="updateAction" value="/HOD/updateLecture" ></c:url>
	<form:form action="${updateAction}" modelAttribute="lecture" class="form-horizontal">
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
	        	<form:label class="control-label col-sm-2" path="userId">
					<spring:message text="Faculty"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="userId" multiple="false">
				    <form:options items="${facultyList}"/>
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
	        <div id="subjects" class="form-group">
	        	<form:label class="control-label col-sm-2" path="subjectId">
					<spring:message text="Subject"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select id="subject_select" class="form-control" path="subjectId" multiple="false">
				    <form:option value="0" label="---Select---" />
				    <c:forEach var="subject" items="${subjectList}">
				    	
				    	<form:option value="${subject.id}" class="${subject.semester}" label="${subject.name}" />
				    
				    </c:forEach>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Update Lecture"/>" />
		      </div>
		    </div>
	</form:form>
	
</div>
	<script type="text/javascript">
	$(document).ready(function() {		
		$('#subjects').hide();
	    var table = $('#lectures_list').DataTable({
	    	
	    });
	    
	
	    // Display subjects based on semester
	    $('#semester').on('change',function(){
    		$('#subjects').show();
	    	var selected_sem = this.value;
	    	document.getElementById("subject_select").options[0].selected = true;
	    	$('#subject_select option').hide();
	    	for(i=0;i<$('#subject_select option').length;i++)
	    		{
	    		if($('#subject_select option')[i].className == selected_sem)
	    			{
	    			$('#subject_select option')[i].style.display= "block";
	    			}
	    		}
	    });
	} );
	</script>
</body>
</html>