<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet_ecommerce.*"
	%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	
	<title>Messages Index</title>

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
				<li class="active">Escribir mensaje</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->

	<!-- section -->
	

	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<form id="checkout-form" class="clearfix">
					<div class="col-md-6">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Escribe un mensaje</h3>
								<%if(request.getParameter("corrId")!=null&&request.getParameter("corrId")!=""){%>
								<h3 class="title">a: <%=request.getParameter("corrId")%></h3>
								<%} %>
							</div>
							<form action="jms_servlet" method="POST">
								<div class="form-group">
								
									<INPUT type="text" name="mensaje" size="94">
								
								</div>
								
								
								<%		
								Usuario user = (Usuario) session.getAttribute("user");
								
								if(request.getParameter("corrId")==null||request.getParameter("corrId")=="null"){ %>
									
									<h5>Esta notificación se enviará a todos los
									<%if(user.getEstatus()==0){%>
										 vendedores
									<%}else{%>
										compradores
									<%}
									%></h5>
									
									
								<%}else{ %>
								<div class="form-group">
								<label for="corrId">Destinatario: </label>
								<SELECT name="corrId">
										<OPTION value="<%=request.getParameter("corrId")%>" selected><%=request.getParameter("corrId")%></OPTION>
										<OPTION value="">Enviar notificación a
										<%if(user.getEstatus()==0){%>
										 	vendedores
										<%}else{%>
											compradores
										<%}
										%>
										</OPTION>
									</SELECT>
								</div>
								
								<%} %>

								<div class="pull-right">
									
									<button class="primary-btn" type="submit" name="mode" value="sendAll">
									Enviar
									</button>
								</div>
							</form>
						</div>
					</div>
					
				</form>
				
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

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