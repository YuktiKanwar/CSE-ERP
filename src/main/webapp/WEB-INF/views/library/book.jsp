<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Books Page</title>
	<style>
	.dataTables_filter {
		display: none; 
	}
	</style>
</head>
<body>
<div class="container-fluid">
	<h2>Add a Book</h2>
	<c:url var="addAction" value="/Library/addBook" ></c:url>
	<form:form action="${addAction}" modelAttribute="book" class="form-horizontal">
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="title">
					<spring:message text="Title"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="title" class="form-control" placeholder="Enter Title" required="true"/>
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="desc">
					<spring:message text="Description"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="desc" class="form-control" placeholder="Enter Description" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="count">
					<spring:message text="Count"/>
				</form:label>
		      <div class="col-sm-4">
				<form:input path="count" class="form-control" placeholder="Enter Number of Books" required="true" />
		      </div>
		    </div>
		    <div class="form-group">
		    	<form:label class="control-label col-sm-2" path="semester">
					<spring:message text="Semester"/>
				</form:label>
		      <div class="col-sm-4">
				<form:select id="subject_select" class="form-control" path="semester" multiple="false">
				    <form:option value="0" label="---Select---" />
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
	        	<form:label class="control-label col-sm-2" path="departmentId">
					<spring:message text="Department"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="departmentId" multiple="false">
		        	<form:option value="0" label="---Select---" />
				    <form:options items="${departmentList}"/>
				</form:select>
		      </div>
		    </div>
   	        <div class="form-group">
	        	<form:label class="control-label col-sm-2" path="subjectId">
					<spring:message text="Subject"/>
				</form:label>
		      <div class="col-sm-4">
		        <form:select class="form-control" path="subjectId" multiple="false">
		        	<form:option value="0" label="---Select---" />
				    <form:options items="${subjectList}"/>
				</form:select>
		      </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <input type="submit" id="addbook" class="btn btn-default" value="<spring:message text="Add Book"/>" />
		      </div>
		    </div>
	</form:form>
	<br>
	<h3>Books List</h3>
	<c:if test="${!empty listBooks}">
		<table class="mdl-data-table row-border hover" id="book_list">
			<thead>
				<tr>
					<th width="80">Title</th>
					<th width="120">Description</th>
					<th width="80">Subject</th>
					<th width="120">Count</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th width="80">Title</th>
					<th width="120">Description</th>
					<th width="80">Subject</th>
					<th width="120">Count</th>
					<th width="60">Semester</th>
					<th width="60">Department</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<c:forEach items="${listBooks}" var="book">
			<tr>
				<td>${book.title}</td>
				<td>${book.desc}</td>
				<td>${book.subject.name}</td>
				<td>${book.count}</td>
				<td>${book.semester}</td>
				<td>${book.department.name}</td>
				<td><a href="<c:url value='edit/${book.id}' />" >Edit</a></td>
				<td><a href="<c:url value='remove/${book.id}' />" >Delete</a></td>
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
	    
	} );
	</script>
</body>
</html>