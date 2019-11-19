<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet_ecommerce.*"
	import="javax.jms.TextMessage"
	import="java.util.ArrayList"
	%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>E-SHOP HTML Template</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>
	<!-- HEADER -->
	<jsp:include page="HEADER.jsp"></jsp:include>	
	<!-- /HEADER -->

	<!-- NAVIGATION -->
	<jsp:include page="NAVIGATION.jsp"></jsp:include>
	<!-- /NAVIGATION -->

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="E_commerce_servlet?action=home">Home</a></li>
				<li class="active">Account Details</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->

	<!-- section -->
	
		<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
			
			<div class="pull-right">
				<button class="primary-btn" onclick="window.location.href ='jms_servlet?mode=clearInbox';">
				Clear Inbox
				</button>
			</div>
			<div class="pull-right">
				<button class="primary-btn" onclick="window.location.href ='jms_servlet?mode=toSend';">
				Send New Message
				</button>
			</div>
			
				<form id="checkout-form" class="clearfix">
					<div class="col-md-6">
						<div class="billing-details">
							<div class="section-title">
							

								<h3 class="title">Personal messages</h3>

							</div>
							
							
								
								<br><%if(request.getAttribute("personal-mensajes")==null||request.getAttribute("personal-mensajes")==""){%>
								
								
									<strong class="text-uppercase">No hay mensajes nuevos</strong>
									
									
								<%}else{
									ArrayList<TextMessage> mensajes = (ArrayList<TextMessage>) request.getAttribute("personal-mensajes");
									for(int i=0;i<mensajes.size();i++){
										
										String senderUser = mensajes.get(i).getStringProperty("JMSXUserID");%>
										<div class="section" style="border-bottom: 1px solid #DADADA;">		
											<label for="messageRead">Message from: <span style="color:blue"><%=senderUser%></span></label>								
											<div id="messageRead">
											<%=mensajes.get(i).getText()%>
											</div>
											<a  class="list-links pull-right"  href="jms_servlet?mode=toSend&corrId=<%=senderUser%>">Reply Message</a>
										
										</div>
										
									<%}
								} %>
								
								
								<p>
								<br>
								</p>
								<hr>
							

						</div>
					</div>
					
				</form>	
			
			
			
				<form id="checkout-form" class="clearfix">
					<div class="col-md-6">
						<div class="billing-details">
							<div class="section-title">
							
								<%
								Usuario user = (Usuario) session.getAttribute("user");
								if(user.getEstatus()==0){%>
								<h3 class="title">Notifications from the Sellers</h3>
								<%}else{ %>
								<h3 class="title">Notifications from the Sellers</h3>
								<%} %>
							</div>
							
							
								
								<br><%if(request.getAttribute("mensajes")==null||request.getAttribute("mensajes")==""){%>
								
								
									<strong class="text-uppercase">No hay mensajes nuevos</strong>
									
									
								<%}else{
									ArrayList<TextMessage> mensajes = (ArrayList<TextMessage>) request.getAttribute("mensajes");
									for(int i=0;i<mensajes.size();i++){
									
										String senderUser = mensajes.get(i).getStringProperty("JMSXUserID");%>
										<div class="section" style="border-bottom: 1px solid #DADADA;">		
											<label for="messageRead">Message from: <span style="color:blue"><%=senderUser%></span></label>								
											<div id="messageRead">
											<%=mensajes.get(i).getText()%>
											</div>
											<a  class="list-links pull-right"  href="jms_servlet?mode=toSend&corrId=<%=senderUser%>">Reply Message</a>
										
										</div>

									<%}
								} %>
								
								
								<p>
								<br>
								</p>
								<hr>
							

						</div>
					</div>
					
				</form>			
				

				
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->



	
	


	
	<!-- /section -->

	<!-- FOOTER -->
	<jsp:include page="FOOTER.jsp"></jsp:include>	
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>