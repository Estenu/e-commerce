<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet_ecommerce.*"
	import="java.util.List"
	import="java.util.ArrayList"
	import="org.apache.commons.codec.binary.StringUtils"
	import="org.apache.commons.codec.binary.Base64"%>
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
				<li class="active">Products</li>
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
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">Shop by:</h3>
						<ul class="filter-list">
							<li><span class="text-uppercase">Size:</span></li>
							<li><a href="#">X</a></li>
							<li><a href="#">XL</a></li>
						</ul>
						<a href="E_commerce_servlet?action=products" class="primary-btn">Clear All</a>
					</div>
					<!-- /aside widget -->
					
					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">Filter by Name</h3>
						<form action="E_commerce_servlet" method="post">
							<input class="input" type="text" name="byName" placeholder="Nombre del Producto" maxlength="45">
							<button class="search-btn" type="submit" name="action" value="catalogoSearch">
							<i class="fa fa-search"></i>Buscar</button>
						</form>
					</div>
					<!-- /aside widget -->
					
					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">Filter by Brand</h3>
						<ul class="list-links">
							<li><a href="#">Nike</a></li>
							<li><a href="#">Adidas</a></li>
							<li><a href="#">Polo</a></li>
							<li><a href="#">Lacost</a></li>
						</ul>
					</div>
					<!-- /aside widget -->
				</div>
				<!-- /ASIDE -->

				<!-- MAIN -->
				<div id="main" class="col-md-9">
					<!-- STORE -->
					<div id="store">
						<!-- row -->
						<div class="row">
							<%
							List<Producto> elementos= new ArrayList<Producto>();
							Object lista = session.getAttribute("catalogo");
							int counter = 0, size = 1;
  							if (lista != null){
							if(lista instanceof List){
								elementos = (List<Producto>)lista;
								int maxSize = elementos.size();
								for(Producto elemento: elementos){%>
									<!-- Product Single -->
									<div class="col-md-4 col-sm-6 col-xs-6">
										<div class="product product-single">
											<div class="product-thumb">
												<div class="product-label">
												</div>
												<button class="main-btn quick-view"
													onclick="window.location.href ='E_commerce_servlet?action=productpage';">
													<i class="fa fa-search-plus"></i> Quick view</button>
												<img src="<% StringBuilder sb = new StringBuilder();
												sb.append("data:image/png;base64,");
												sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(elemento.getImagen(), false)));
												out.print(sb.toString()); %>" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$<%=elemento.getPrecio() %></h3>
												<h2 class="product-name"><a href="#"><%=elemento.getIdProducto() %></a></h2>
												<div class="product-btns">
													<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
													<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
												</div>
											</div>
										</div>
									</div>
								<!-- /Product Single -->
								<%if(size != maxSize){%>
									<div class="clearfix visible-sm visible-xs"></div>
								<%}%>
										<%counter++; size++; %>
									<% }
								}
							}%>	
						</div>
						<!-- /row -->
					</div>
					<!-- /STORE -->
				</div>
				<!-- /MAIN -->
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
