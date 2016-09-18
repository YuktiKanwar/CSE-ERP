<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 <head>
 <title><sitemesh:write property='title'/></title>
 <sitemesh:write property='head'/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/layout.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
 
 </head>
 
 <body>
 	<!-- Top navbar -->
 	<nav class="navbar navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="navbar-left"><img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
        </div>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
	        <div id="navbar" class="navbar-collapse collapse">
	          <form class="navbar-form navbar-right" action="<c:url value='/loginPage' />" method='POST'>
	            <div class="form-group">
	              <input type="text" placeholder="Username" name='username' class="form-control">
	            </div>
	            <div class="form-group">
	              <input type="password" placeholder="Password" name='password' class="form-control">
	            </div>
	            <button type="submit" class="btn btn-success">Sign in</button>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	          </form>
	        </div><!--/.navbar-collapse -->
        </c:if>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
				<div class="navbar-form navbar-right">
					<ul class="nav navbar-nav">
				    	<li class="dropdown">
					        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Hi ${pageContext.request.userPrincipal.name}!
					        <span class="caret"></span></a>
					        <ul class="dropdown-menu">
					          <li><a href="#">Thing A</a></li>
					          <li><a href="#">Thing B</a></li>
					          <li><a href="#">Thing C</a></li>
					          <li><a href="javascript:document.getElementById('logout').submit()">Logout</a></li> 
					        </ul>
				    	</li>		       
					<!-- Form for submitting logout request -->
			      	<c:url value="/logout" var="logoutUrl" />
					<form id="logout" action="${logoutUrl}" method="post" >
					  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					</ul>
				</div>
		</c:if>
      </div>
    </nav>
 	<!-- Bottom Navbar -->
	<div class="container">
		<nav class="navbar navbar-fixed-bottom">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <span class="navbar-brand" href="#">CSE ERP</span>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="#">Home</a></li>
		      <li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">Page 1-1</a></li>
		          <li><a href="#">Page 1-2</a></li>
		          <li><a href="#">Page 1-3</a></li> 
		        </ul>
		      </li>
		      <li><a href="#">Page 2</a></li> 
		      <li><a href="#">Page 3</a></li>
		    </ul>
		  </div>
		</nav>
 		<sitemesh:write property='body'/>
 		<br/><br/><br/>
	</div>
 </body>
 <script>
 
 </script>
</html>