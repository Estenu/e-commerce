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
				<li class="active">Account Details</li>
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
								<h3 class="title">Send a message</h3>
							</div>
							<form action="E_commerce_servlet" method="POST">
								<div class="form-group">
								
									<INPUT type="text" name="mensaje" size="94">
								
								</div>
								
								<div class="form-group">
									<SELECT name="metodo">
										<OPTION value="1" selected>Escribir en la Cola usando JNDI</OPTION>
										<OPTION value="2">Escribir para lectura asícrona</OPTION>
										<OPTION value="3">Escritura usando referencias (Implementar por el alumno)</OPTION>
									</SELECT>
								</div>
								<div class="form-group">
									<SELECT name="operacion">
										<option value="1" selected>Mandar Mensaje. Escribir en la Cola</option>
										<option value="2">Leer en Browser (Implementar por el alumno)</option>
										<option value="3">Leer Mensaje por JMSCorrelationID</option>
									</SELECT>
								</div>
								<div class="pull-right">
									
									<button class="primary-btn" type="submit" name="action" value="messages">
									Ejecutar
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