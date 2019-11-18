<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List"
	import="java.util.ArrayList"
	import="servlet_ecommerce.*"
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
	<%
		Usuario user = (Usuario) session.getAttribute("user");
		Object lista = session.getAttribute("catalogo");
		List<Producto> elementos = (List<Producto>)lista;
		int index = (Integer) session.getAttribute("index");
		Producto myProducto = elementos.get(index);
	%>
	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="E_commerce_servlet?action=home">Home</a></li>
				<li><%= myProducto.getCategoríasInferiore().getCategoríasSuperiore().getNombre_Cat_Sup() %></li>
				<li><%= myProducto.getCategoríasInferiore().getNombre_Cat_Inf() %></li>
				<li class="active"><%= myProducto.getIdProducto() %></li>
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
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div class="col-md-6">
						<div id="product-main-view">
							<div class="product-view">
								<img src="<% StringBuilder sb = new StringBuilder();
								sb.append("data:image/png;base64,");
								sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(myProducto.getImagen(), false)));
								out.print(sb.toString()); %>" alt="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="product-body">
<<<<<<< HEAD
							<div class="product-label">
								<span>New</span>
								<span class="sale">-20%</span>
							</div>
							<h2 class="product-name">Spire F VRT</h2>
							<h3 class="product-price">$9999.99 <del class="product-old-price">$12499.99</del></h3>
							<p><strong>Availability:</strong> In Stock</p>
							<p><strong>Brand:</strong> Lamborghini Trattori</p>
							<h4>The most attractive and powerful specialised tractor</h4>
							<p>The Lamborghini Spire F VRT isn't just the most attractive and technologically advanced specialised tractor on the market, it's also the most powerful, the most productive and the best performing.</p>
							<div class="product-options">
								<label>Select Model: 
									<select class="model-power">
										<option value="0">90</option>
										<option value="1">100</option>
										<option value="1">90.4</option>
										<option value="1">105</option>
										<option value="1">115</option>
									</select>
								</label>
								
								<table class="mytable">
									<tbody class="mybody">
										<tr class="first-row">
											<td></td>
											<td class="headerDate">90</td>
											<td class="headerDate">100</td>
											<td class="headerDate">90.4</td>
											<td class="headerDate">105</td>
											<td class="headerDate">115</td>
											</tr>
										<tr class="second-row">
											<td class="itemLabel">Maximum power (hp/kW)</td>
											<td class="itemValue">88/65</td>
											<td class="itemValue">97/71</td>
											<td class="itemValue">88/65</td>
											<td class="itemValue">102/75</td>
											<td class="itemValue">113/83</td>
											</tr>
										<tr class="third-row">
											<td class="itemLabel">Homologated power at rated engine speed (hp/kW)</td>
											<td class="itemValue">83/61,5</td>
											<td class="itemValue">91/67</td>
											<td class="itemValue">84/61,8</td>
											<td class="itemValue">97/71,5</td>
											<td class="itemValue">107/79</td>
											</tr>
										<tr class="fourth-row">
											<td class="itemLabel">Maximum torque (Nm)</td>
											<td class="itemValue">354</td>
											<td class="itemValue">369</td>
											<td class="itemValue">354</td>
											<td class="itemValue">408</td>
											<td class="itemValue">435</td>
											</tr>
										<tr class="fifth-row">
											<td class="itemLabel">Cylinders / displacement (n°/cc)</td>
											<td class="itemValue">3/2.9</td>
											<td class="itemValue">3/2.9</td>
											<td class="itemValue">4/3.8</td>
											<td class="itemValue">4/3.8</td>
											<td class="itemValue">4/3.8</td>
											</tr>
										<tr class="sixth-row">
											<td class="itemLabel">Lifting capacity (kg)</td>
											<td class="itemValue">2600</td>
											<td class="itemValue">2600</td>
											<td class="itemValue">2600</td>
											<td class="itemValue">2600</td>
											<td class="itemValue">2600</td>
											</tr>
										<tr class="seventh-row">
											<td class="itemLabel">Minimum width (mm)</td>
											<td colspan="5" class="itemValue">1588</td>
											</tr>
										<tr class="eigth-row">
											<td class="itemLabel">Weight with cab (kg)</td>
											<td class="itemValue">3600</td>
											<td class="itemValue">3600</td>
											<td class="itemValue">3800</td>
											<td class="itemValue">3800</td>
											<td class="itemValue">3800</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<form action="E_commerce_servlet" method="post">
								<div class="product-btns">
								<div class="qty-input">
=======
							<h2 class="product-name"><%= myProducto.getIdProducto() %></h2>
							<h3 class="product-price">$<%= myProducto.getPrecio() %></h3>
							<% if(myProducto.getStock() > 0){%>
								<p><strong>Availability:</strong> In Stock (<%= myProducto.getStock() %> Units)</p>
							<%}else{%>
								<p><strong>Availability:</strong> No units</p>
							<%}%>
							<p><strong>Brand:</strong> <%= myProducto.getCategoríasInferiore().getNombre_Cat_Inf() %></p>
							<p><%= myProducto.getDescription() %></p>
						
							<div class="product-btns">
								<form action="E_commerce_servlet" method="post">
									<input class="input" type="hidden" name="productoacarrito" value="<%=index%>">
>>>>>>> branch 'master' of git@github.com:Estenu/e-commerce.git
									<span class="text-uppercase">QTY: </span>
<<<<<<< HEAD
									<input class="input" type="number">
								</div>
								<input class="input" type="hidden" name="productoacarrito" value="">
								<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
								<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
							</form>
							
								
								<div class="pull-right">
									<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
									<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
									<button class="main-btn icon-btn"><i class="fa fa-share-alt"></i></button>
								</div>
=======
									<input class="qty-input" type="number" name="cantidad" min="1" required> 
									<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
									<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
								</form>
								<form action="E_commerce_servlet" method="post">
									<input class="input" type="hidden" name="productoawishlist" value="<%=index%>">
									<button class="secondary-btn add-to-cart" type="submit" name="action" value="add_to_wishlist">
									<i class="fa fa-shopping-cart"></i>Añadir a lista de deseos</button>
								</form>
>>>>>>> branch 'master' of git@github.com:Estenu/e-commerce.git
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="product-tab">
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
								
							</ul>
							<div class="tab-content">
								<div id="tab1" class="tab-pane fade in active">
									<%=myProducto.getLongDesc()%>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	


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
