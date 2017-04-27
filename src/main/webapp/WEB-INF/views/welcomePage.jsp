
<div class='animatedParent'>
	<center>
	<div class="info animated shake slowest">
		<h2 class='welcomeERP'>Welcome to CSE-ERP Project</h2>
		<br/>
		<img style="width:31%;  margin-top: -35px" src="${pageContext.request.contextPath}/resources/images/ERP.png" />
		<br/><br/><br/><br/>
	</div>
	</center>
</div>
<div class="parallax1">
</div>
<div class='animatedParent' style="background-color:rgb(251, 246, 242)">
	<div class="benefit animated fadeInRight">
		<img style="margin: 0 auto;display: block;" src="${pageContext.request.contextPath}/resources/images/erp1.jpg" alt="image01" />
	</div>
</div>
<div class="parallax2">
</div>
<div class="parallax3">
	<br/><br/><br/>
	<p>Work effortlessly with our ERP application</p>
	<br/><br/>
</div>

<div class='animatedParent'>
	<div id="da-slider" class="da-slider animated bounceIn slowest">
	
		<div class="da-slide">
			<h2>Why ERP?</h2>
			<p>It's time you get rid of the legacy software and start using modern ERP to make your life easier.</p>
			<a href="#" class="da-link">Read more</a>
			<div class="da-img">
				<img src="${pageContext.request.contextPath}/resources/images/erp2.png" class="sliderImage" style="height:310px;" alt="image01" />
			</div>
		</div>
		
		<div class="da-slide">
			<h2>Our ERP</h2>
			<p></p>
			<a href="#" class="da-link">Read more</a>
			<div class="da-img">
				<img src="${pageContext.request.contextPath}/resources/images/erp4.png" class="sliderImage" style="height:310px;" alt="image01" />
			</div>
		</div>
		
		<nav class="da-arrows">
			<span class="da-arrows-prev"></span>
			<span class="da-arrows-next"></span>
		</nav>
		
	</div>
</div>

<script>
$(document).ready(function(){
	if("${error}" != "" )
		alert("${error}");
	if("${message}" != "" )
		alert("${message}");
	
	$('#da-slider').cslider({

		current		: 0, 	
		// index of current slide
		
		bgincrement	: 50,	
		// increment the background position 
		// (parallax effect) when sliding
		
		autoplay	: false,
		// slideshow on / off
		
		interval	: 4000  
		// time between transitions
		
	});
});


</script>
