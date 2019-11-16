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
	
	<h1 align="center">
		<table>
			<tr>
				
				<td valign="middle">
					<h3>
						TIdW - 2014/15
					</h3>
				</td>
			</tr>
			<tr>
				<td colspan="2">Laboratorio JMS 1.1 - Uso de JMSCorrelationID</td>
			</tr>
		</table>
	</h1>
	<h3 align="center">
		Mensasejes Leidos Vía <font color="black" size="+1">JMSCorrelationID</font>
	</h3>
	<hr>


	<p>
		<br><%=request.getAttribute("mensajes")%>
	</p>
	<p>
		<br>
	</p>
	<hr>
	<p>
		<a href="index.jsp">Página de Inicio</a>
	</p>
	
	
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