<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 <head>
 <title><sitemesh:write property='title'/></title>
 <sitemesh:write property='head'/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/layout.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.datepicker.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/font-awesome.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/slider.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/animations.css">
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery.cslider.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/modernizr.custom.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/notify.js"></script>
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
	          <form class="navbar-form navbar-right" action="<c:url value='/' />" method='POST'>
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
 	<div class="main">
		<div class="container-fluid">
	 		<sitemesh:write property='body'/>
	 		<br/><br/><br/>
	 		<nav class="navbar navbar-fixed-bottom">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <span class="navbar-brand" href="#">CSE ERP</span>
			    </div>
			    <ul class="nav navbar-nav">
			     	<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
			      	<c:if test="${pageContext.request.userPrincipal.name == null}">
			      	</c:if>
			      	<c:if test="${pageContext.request.userPrincipal.name != null}">
			      		<li><a href="${pageContext.request.contextPath}/homePage">Domains</a></li>
			      	</c:if>
			      	
			    </ul>
		     	<ul class="nav navbar-nav navbar-right" style="font-size: 25px;">
				     	<li>
				      		<a href="https://github.com/lagneshthakur/CSE_ERP" target='_blank'>
					            <i class="fa fa-github"></i>
					        </a>
			      		</li>
			      		<li>
				      		<a href='https://linkedin.com/in/yuktikanwar' class="linkedin">
				           		<i class="fa fa-linkedin"></i>
				          	</a>
			      		</li>
			      		<li>
			      			<a href='https://mail.google.com/mail/?view=cm&fs=1&to=cseerp2016@gmail.com&su=Hi!&body=Drop us a message! :-)' target='_blank'>
					            <i class="glyphicon glyphicon-envelope"></i>
				          	</a>
			      		</li>
		      		</ul>
			  </div>
			</nav>
		</div>
	</div>
 <script src="${pageContext.request.contextPath}/resources/js/css3-animate-it.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery.datepicker.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/jquery.mousewheel.js"></script>
 
 </body>
 <script>
 $('a.linkedin').click(function(e) {
	    window.open('https://linkedin.com/in/lagneshthakur');
	});
 </script>
</html>