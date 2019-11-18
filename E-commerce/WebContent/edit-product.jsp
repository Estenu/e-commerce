<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List"
	import="java.util.ArrayList"
	import="servlet_ecommerce.*"%>
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
				<%
				Usuario user = (Usuario) session.getAttribute("user");
				Object lista = user.getProductos();
				List<Producto> elementos = (List<Producto>)lista;
				int index = (Integer) session.getAttribute("index");
				Producto myProducto = elementos.get(index);
				%>
				<div class="section-title">
					<h4 class="title">Editando: <%=  myProducto.getIdProducto() %></h4>
				</div>
				<p>Por favor rellene los siguientes campos:</p>

				<form action="E_commerce_servlet" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="nombre">Precio</label>
						<input class="input" type="number" name="precio" placeholder="<%= myProducto.getPrecio()%>" min="0">
					</div>
					<div class="form-group">
						<label for="nombre">Stock</label>
						<input class="input" type="number" name="stock" placeholder="<%= myProducto.getStock()%>" min="0">
					</div>
					<!-- 
					<div><p>Categoría la que pertenece el producto:</p>
						<SELECT name="selector">
							<OPTION value="<%= myProducto.getCategoríasInferiore().getNombre_Cat_Inf()%>" selected><%= myProducto.getCategoríasInferiore().getNombre_Cat_Inf()%></OPTION>
							<OPTION value="Lambo">Lambo</OPTION>
							<OPTION value="Category 02">Category 02</OPTION>
						</SELECT>
						
					</div><br>
					 -->
					<div class="form-group">
						<label for="nombre">Descripción breve</label>
						<input class="input" type="text" name="desc" placeholder="<%= myProducto.getDescription()%>" maxlength="250">
					</div>
					<div class="form-group">
						<label for="nombre">Descripción larga</label>
						<input class="input" type="text" name="longDesc" placeholder="<%= myProducto.getLongDesc()%>" maxlength="2000">
					</div>
					<div class="form-group">
						<p>Selecciona la imagen del producto: </p>
						<input type="file" name="fileToUpload" id="fileToUpload">
					</div>
					
					<div class="pull-right">
						<button class="primary-btn" type="submit" name="action" value="editProduct">
						Register
						</button>
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