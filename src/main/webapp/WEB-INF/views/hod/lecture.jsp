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
	<h2>Add a Lecture</h2>
	
	<c:url var="addAction" value="/HOD/addLecture" ></c:url>
	<form:form action="${addAction}" modelAttribute="lecture" class="form-horizontal">
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
				    <c:forEach var="subject" items="${subjectList}">
				    	
				    	<form:option value="${subject.id}" class="${subject.semester}" label="${subject.name}" />
				    
				    </c:forEach>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" class="btn btn-default" value="<spring:message text="Add Lecture"/>" />
		      </div>
		    </div>
	</form:form>
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
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Faculty</th>
					<th width="120">Subject</th>
					<th width="120">Semester</th>
					<th width="120">Department</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listLectures}" var="lecture">
			<tr>
				<td>${lecture.user.username}</td>
				<td>${lecture.subject.name}</td>
				<td>${lecture.subject.semester}</td>
				<td>${lecture.subject.department.name}</td>
				<td><a href="<c:url value='edit/lecture/${lecture.id}' />" >Edit</a></td>
				<td><a href="<c:url value='remove/lecture/${lecture.id}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
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

	    $('#semester').on('change',function(){
    		$('#subjects').show();
	    	var selected_sem = this.value;
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