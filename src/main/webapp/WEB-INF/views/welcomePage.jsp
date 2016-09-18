<center>
	<h2>Welcome to CSE-ERP Project</h2>
	<br/>
	<img src="${pageContext.request.contextPath}/resources/images/ERP.png" />
</center>
<script>
$(document).ready(function(){
	if("${error}" != "" )
		alert("${error}");
	if("${message}" != "" )
		alert("${message}");
});


</script>
