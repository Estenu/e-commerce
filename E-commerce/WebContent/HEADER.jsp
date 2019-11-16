<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet_ecommerce.*"
	import="java.util.List"
	import="org.apache.commons.codec.binary.StringUtils"
	import="org.apache.commons.codec.binary.Base64"
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
<link href="https://fonts.googleapis.com/css?family=Hind:400,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<link rel="stylesheet" href="Style2.css">
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
	<header>
		<!-- top Header -->
		<div id="top-header">
			<div class="container">
				<div class="pull-left">
					<span>Welcome to E-shop!</span>
				</div>
				<div class="pull-right">
					<ul class="header-top-links">
						<li><a href="E_commerce_servlet?action=products">Store</a></li>
						<li><a href="#">FAQ</a></li>
						<li class="dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">ENG <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">English (ENG)</a></li>
								<li><a href="#">Russian (Ru)</a></li>
								<li><a href="#">French (FR)</a></li>
								<li><a href="#">Spanish (Es)</a></li>
							</ul></li>
						<li class="dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">USD <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">USD ($)</a></li>
								<li><a href="#">EUR (â¬)</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="#"> <img
							onclick="window.location.href ='E_commerce_servlet?action=home';"
							src="./img/logo.jpg" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form>
							<input class="input search-input" type="text"
								placeholder="Enter your keyword"> <select
								class="input search-categories">
								<option value="0">Todas las categorías</option>
								<option value="1">Category 01</option>
								<option value="1">Category 02</option>
							</select>
							<button class="search-btn">
								<i class="fa fa-search"></i>
							</button>
						</form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown"
								aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<%
									Usuario user = (Usuario) session.getAttribute("user");
									String userEmail;
									if(user==null){
										userEmail = "Mi Cuenta";
										
									}else{
										userEmail = "Username: " + user.getEmail();
									}
								%>
								<strong class="text-uppercase"><%=userEmail%><i
									class="fa fa-caret-down"></i></strong>
							</div> 	
							
							
							<%if (null == user){ %>
							<a href="E_commerce_servlet?action=login" class="text-uppercase">Login</a>
							/ <a href="E_commerce_servlet?action=create-account"
							class="text-uppercase">Join</a>
							
							<ul class="custom-menu">

								<li id="LOGIN"><a href="E_commerce_servlet?action=login"><i
										class="fa fa-unlock-alt"></i>Login</a></li>
								<li><a href="E_commerce_servlet?action=create-account"><i
										class="fa fa-user-plus"></i>Crear una cuenta</a></li>


							</ul>
							
							
							

							<%}else{ 
								
								if(user.getEstatus()==1){%>
										<a href="E_commerce_servlet?action=logout" class="text-uppercase">Logout</a>
										
									<ul class="custom-menu">
										<li ><a href="E_commerce_servlet?action=myaccount"><i
												class="fa fa-user-o"></i> Mi Cuenta</a></li>
										<li><a href="E_commerce_servlet?action=wishlist"><i
												class="fa fa-heart-o"></i>Lista de Deseos</a></li>
										<li><a href="E_commerce_servlet?action=checkout"><i
												class="fa fa-check"></i> Comprar</a></li>
										<li><a href="E_commerce_servlet?action=createProduct"><i
										class="fa fa-pencil"></i>Configurar Productos</a></li>
										<li id="LOGOUT"><a href="E_commerce_servlet?action=logout"><i
												class="fa fa-unlock-alt"></i>Cerrar Sesión</a></li>
									</ul>
									
								<%
								}else{ %>
									<a href="E_commerce_servlet?action=logout" class="text-uppercase">Cerrar Sesión</a>
									
									<ul class="custom-menu">
										<li ><a href="E_commerce_servlet?action=myaccount"><i
												class="fa fa-user-o"></i> Mi Cuenta</a></li>
										<li><a href="E_commerce_servlet?action=wishlist"><i
												class="fa fa-heart-o"></i> Lista de Deseos</a></li>
										<li><a href="E_commerce_servlet?action=checkout"><i
												class="fa fa-check"></i> Comprar</a></li>
										<li id="LOGOUT"><a href="E_commerce_servlet?action=logout"><i
												class="fa fa-unlock-alt"></i>Cerrar Sesión</a></li>
									</ul>
							<% 	}
							} %>
							
							
							

						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">
								<div class="header-btns-icon">
								<%List<Producto>wishlist=(List<Producto>)session.getAttribute("productoscarrito");
								List<Pedido>wishlist1=(List<Pedido>)session.getAttribute("carrito");
								if(wishlist!=null){%>
								
									<i class="fa fa-shopping-cart"></i> <span class="qty"><%=wishlist.size() %></span>
								<%}else{%>
									<i class="fa fa-shopping-cart"></i> <span class="qty">0</span>
								<%} %>
								</div> <strong class="text-uppercase">Mi Carrito:</strong> 
						</a>
							<div class="custom-menu">
								<div id="shopping-cart">
									<div class="shopping-cart-list">
									<%
									if(wishlist!=null){
										double suma=0.0;
										for(int i=0;i<wishlist.size();i++){
									
									%>
										<div class="product product-widget">
											<div class="product-thumb">
												<img  src="<% StringBuilder sb = new StringBuilder();
						sb.append("data:image/png;base64,");
						sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(wishlist.get(i).getImagen(), false)));
						out.print(sb.toString()); %>">
											</div>
											<div class="product-body">
											<h2 class="product-name">
													<a href="#"><%=wishlist.get(i).getDescription() %></a>
												</h2>
												<h3 class="product-price">
													<%=wishlist.get(i).getPrecio() %> <span class="qty">x<%=wishlist1.get(i).getCantidad() %></span>
												</h3>
												
											</div>
											<form action="E_commerce_servlet" method="post">
				<input class="input" type="hidden" name="counter1" value="<%= i %>">
				<button class="cancel-btn" type="submit" name="action" value="quitar_de_carrito">
				<i class="fa fa-trash"></i> Eliminar</button>
			</form>
											
										</div>
									<%suma+=wishlist.get(i).getPrecio();}} %>
									</div>
									<div class="shopping-cart-btns">
										<button class="main-btn"
											onclick="window.location.href ='E_commerce_servlet?action=carrito';">Ver
											Carrito</button>
										<button class="primary-btn"
											onclick="window.location.href ='E_commerce_servlet?action=checkout';">
											Comprar <i class="fa fa-arrow-circle-right"></i>
										</button>
									</div>
								</div>
							</div></li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn">
								<i class="fa fa-bars"></i>
							</button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->




</body>

</html>