<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li class="active">Somewhere</li>
				<li class="active">Upload Product</li>
				//TODO 
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
								<h4 class="title">Product Registration</h4>
							</div>
							<p>Por favor rellene los siguientes campos:</p>

							<form action="E_commerce_servlet" method="post">
								<div class="form-group">
									<input class="input" type="text" name="IdProduct" placeholder="Nombre del Producto" required maxlength="45">
								</div>
								<div class="form-group">
									<input class="input" type="number" name="precio" placeholder="Precio del producto en Euros" required>
								</div>
								<div class="form-group">
									<input class="input" type="number" name="stock" placeholder="Numero de unidades" required>
								</div>
								<div><p>Cateogoría la que pertenece el producto:</p>
									<SELECT name="selector">
										<OPTION value="Category 01" selected>Category 01</OPTION>
										<OPTION value="Category 02">Category 02</OPTION>
									</SELECT>
								</div><br>
								<div class="form-group">
									<input class="input" type="text" name="desc" placeholder="Descripción breve del producto" required maxlength="250">
								</div>
								<div class="form-group">
									<input class="input" type="text" name="longDesc" placeholder="Descripción completa del producto" required maxlength="2000">
								</div>
								<div class="form-group">
									<p>Selecciona la imagen del producto: </p>
									<input type="file" name="fileToUpload" id="fileToUpload" required>
								</div>
								
								<div class="pull-right">
									<button class="primary-btn" type="submit" name="action" value="register_Product">
									Register
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