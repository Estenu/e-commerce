<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet_ecommerce.*"
	import="java.util.List"
	import="org.apache.commons.codec.binary.StringUtils"
	import="org.apache.commons.codec.binary.Base64"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Mi Carrito</title>

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
				<li class="active">Mi Carrito</li>
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
					

				

					<div class="col-md-12">
						<div class="order-summary clearfix">
							<div class="section-title">
								<h3 class="title">Carrito</h3>
							</div>
							<table class="shopping-cart-table table">
								<thead>
									<tr>
										<th>Producto</th>
										<th></th>
										<th class="text-center">Precio</th>
										<th class="text-center">Cantidad</th>
										<th class="text-right"></th>
									</tr>
								</thead>
								<tbody>
				<%List <Pedido> carrito=(List<Pedido>)session.getAttribute("carrito");
								double suma=0.0;
								if(carrito!=null){
									List<Producto>productoscarrito=(List<Producto>)session.getAttribute("productoscarrito");
									for(int i=0;i<carrito.size();i++){
								
								%>
									<tr>
										<td class="thumb"><img  src="<% StringBuilder sb = new StringBuilder();
						sb.append("data:image/png;base64,");
						sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(productoscarrito.get(i).getImagen(), false)));
						out.print(sb.toString()); %>"></td>
										<td class="details">
											<a href="#"><%productoscarrito.get(i).getIdProducto(); %></a>
								
										</td>
										<td class="price text-center"><strong><%=productoscarrito.get(i).getPrecio() %></strong><br><del class="font-weak"></del></td>
										<td class="price text-center"><strong><%=carrito.get(i).getCantidad() %></strong><br><del class="font-weak"></del></td>
										<td><form action="E_commerce_servlet" method="post">
												<input class="input" type="hidden" name="counter1" value="<%= i%>">
												<button class="main-btn icon-btn" type="submit" name="action" value="quitar_de_carrito">
												<i class="fa fa-close"></i></button>
											</form>
										</td>
									</tr>
									<%suma+=carrito.get(i).getCantidad()*productoscarrito.get(i).getPrecio();
									}} %>
								</tbody>
								<tfoot>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>SUBTOTAL</th>
										<th colspan="2" class="sub-total">$<%= suma%></th>
									</tr>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>SHIPING</th>
										<td colspan="2">Free Shipping</td>
									</tr>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>TOTAL</th>
										<th colspan="2" class="total">$<%=suma %></th>
									</tr>
							</table>
							<div class="pull-right">
							<a href="E_commerce_servlet?action=checkout" class="primary-btn">Comprar</a>
							</div>
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