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

	<title>E-COUNTRY</title>

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
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav">
					<span class="category-header">Categorias <i class="fa fa-list"></i></span>
					<ul class="category-list">

						<%
						/*Extraer el usuario*/
						Usuario user = (Usuario) session.getAttribute("user");
						String userEmail;
						if(user==null){
							userEmail = "Mi Cuenta";
							
						}else{
							userEmail = "Username: " + user.getEmail();
						}
						/*Extraer las categorias superiores e inferiores, asi como los productos para poblar index*/
						List<CategoríasSuperiore> myListSup= new ArrayList<CategoríasSuperiore>();
						Object catSup = session.getAttribute("catSup");
						myListSup = (List<CategoríasSuperiore>)catSup;
						List<CategoríasInferiore> myListInf= new ArrayList<CategoríasInferiore>();
						Object catInf = session.getAttribute("catInf");
						myListInf = (List<CategoríasInferiore>)catInf;
						List<Producto> elementos= new ArrayList<Producto>();
						Object lista = session.getAttribute("catalogo");
						elementos = (List<Producto>)lista;
						for(CategoríasSuperiore catSuper: myListSup){%>
							<li class="dropdown side-dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><%= catSuper.getNombre_Cat_Sup() %><i class="fa fa-angle-right"></i></a>
								<div class="custom-menu">
									<div class="row">
										<%
										for(CategoríasInferiore catInferior: myListInf){
											if(catInferior.getCategoríasSuperiore().getNombre_Cat_Sup().equals(catSuper.getNombre_Cat_Sup())){
											%>
												<div class="col-md-4">
													<ul class="list-links">
														<li>
															<h3 class="list-links-title"><%= catInferior.getNombre_Cat_Inf() %></h3></li>
																<%
																for(int i = 0; i<elementos.size(); i++){
																	if(elementos.get(i).getCategoríasInferiore().getNombre_Cat_Inf().equals(catInferior.getNombre_Cat_Inf())){
																		String url = "E_commerce_servlet?action=productpage&counter=" + i;
																	%>
																		<li><a href="<%=url%>"><%=elementos.get(i).getIdProducto()%></a></li>
																	<%}
																}
																%>
													</ul>
													<hr class="hidden-md hidden-lg">
												</div>
											<%}
										}
										%>
									</div>
									<!-- BANNER -->
									<% int randomBanner = (int) (Math.random()*(2));
									if(randomBanner == 0){%>
										<div class="row hidden-sm hidden-xs">
											<div class="col-md-12">
												<hr>
												<a class="banner banner-1" href="#">
													<img src="./img/bannerTrac.jpg" alt="">
												</a>
											</div>
										</div>
									<%}else{%>
										<div class="col-md-4 hidden-sm hidden-xs">
											<a class="banner banner-2">
												<img src="./img/jannerTry.jpg" alt="">
											</a>
										</div>
									<%} %>
									<!-- BANNER -->
								</div>
							</li>
						<%}%>
						<li><a href="E_commerce_servlet?action=products">Todas las categorías</a></li>

					</ul>
				</div>
				<!-- /category nav -->

				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
						<li><a href="E_commerce_servlet?action=home">Home</a></li>
						<li><a href="E_commerce_servlet?action=products">Catálogo</a></li>
					</ul>
				</div>
				<!-- menu nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->

	<!-- HOME -->
	<div id="home">
		<!-- container -->
		<div class="container">
			<!-- home wrap -->
			<div class="home-wrap">
				<!-- home slick -->
				<div id="home-slick">
					<!-- banner -->
					<div class="banner banner-home1">
						<img src="./img/home1.jpg" alt="">
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-home2">
						<img src="./img/home2.jpg" alt="">
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-home3">
						<img src="./img/home3.jpg" alt="">
					</div>
					<!-- /banner -->
				</div>
				<!-- /home slick -->
			</div>
			<!-- /home wrap -->
		</div>
		<!-- /container -->
	</div>
	<!-- /HOME -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Productos Recomendados</h2>
						<div class="pull-right">
							<div class="product-slick-dots-2 custom-dots">
							</div>
						</div>
					</div>
				</div>
				<!-- /section title -->
				<%
				int counter = 0, size = 1;
				if (lista != null){
				if(lista instanceof List){
					int maxSize = elementos.size();
					Producto first = elementos.get(0);%>
					<!-- Product Single -->
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="product product-single product-hot">
							<div class="product-thumb">
								<form action="E_commerce_servlet" method="post">
									<input class="input" type="hidden" name="counter" value="<%= counter %>">
									<button class="main-btn quick-view"
									type="submit" name="action" value="productpage">
									<i class="fa fa-search-plus"></i> Quick view</button>
								</form>
								<img src="<% StringBuilder sb = new StringBuilder();
								sb.append("data:image/png;base64,");
								sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(first.getImagen(), false)));
								out.print(sb.toString()); %>" alt="">
							</div>
							<div class="product-body">
								<h3 class="product-price">$<%=first.getPrecio() %></h3>
								<h2 class="product-name"><a href="#"><%=first.getIdProducto() %></a></h2>
								<%
								if (null != user){
									if(user.getEstatus()==1){
									}else{%>
										<div class="product-btns">
									<form action="E_commerce_servlet" method="post">
										<input class="input" type="hidden" name="productoawishlist" value="<%=counter%>">
										<button class="secondary-btn add-to-cart" type="submit" name="action" value="add_to_wishlist">
										<i class="fa fa-shopping-cart"></i>Añadir a lista de deseos</button>
									</form>
									<form action="E_commerce_servlet" method="post">
										<input class="input" type="hidden" name="productoacarrito" value="<%=counter%>">
										<input class="input" type="hidden" name="cantidad" value="1"> 
										<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
										<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
									</form>
								</div>
									<%} 
								}else{%>
									<div class="product-btns">
									<form action="E_commerce_servlet" method="post">
										<input class="input" type="hidden" name="productoawishlist" value="<%=counter%>">
										<button class="secondary-btn add-to-cart" type="submit" name="action" value="add_to_wishlist">
										<i class="fa fa-shopping-cart"></i>Añadir a lista de deseos</button>
									</form>
									<form action="E_commerce_servlet" method="post">
										<input class="input" type="hidden" name="productoacarrito" value="<%=counter%>">
										<input class="input" type="hidden" name="cantidad" value="1"> 
										<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
										<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
									</form>
								</div>
								<%} %>
							</div>
						</div>
					</div>
					<!-- /Product Single -->
					<%counter++;%>
					<%}
				}%>

				<!-- Product Slick -->
					<div class="col-md-9 col-sm-6 col-xs-6">
						<div class="row">
							<div id="product-slick-2" class="product-slick">
								<%for(int i = 1; i<10; i++){
									if(i<elementos.size()){%>
										<!-- Product Single -->
											<div class="product product-single">
												<div class="product-thumb">
													<form action="E_commerce_servlet" method="post">
														<input class="input" type="hidden" name="counter" value="<%= counter %>">
														<button class="main-btn quick-view"
														type="submit" name="action" value="productpage">
													<i class="fa fa-search-plus"></i> Quick view</button>
													</form>
														<img src="<% StringBuilder sb = new StringBuilder();
														sb.append("data:image/png;base64,");
														sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(elementos.get(i).getImagen(), false)));
														out.print(sb.toString()); %>" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-price">$<%=elementos.get(i).getPrecio() %></h3>
													<h2 class="product-name"><a href="#"><%=elementos.get(i).getIdProducto() %></a></h2>
													<%
													if (null != user){
														if(user.getEstatus()==1){
														}else{%>
															<div class="product-btns">
																<form action="E_commerce_servlet" method="post">
																	<input class="input" type="hidden" name="productoawishlist" value="<%=counter%>">
																	<button class="secondary-btn add-to-cart" type="submit" name="action" value="add_to_wishlist">
																	<i class="fa fa-shopping-cart"></i>Añadir a lista de deseos</button>
																</form>
																<form action="E_commerce_servlet" method="post">
																	<input class="input" type="hidden" name="productoacarrito" value="<%=counter%>">
																	<input class="input" type="hidden" name="cantidad" value="1"> 
																	<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
																	<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
																</form>
															</div>
														<%} 
													}else{%>
														<div class="product-btns">
															<form action="E_commerce_servlet" method="post">
																<input class="input" type="hidden" name="productoawishlist" value="<%=counter%>">
																<button class="secondary-btn add-to-cart" type="submit" name="action" value="add_to_wishlist">
																<i class="fa fa-shopping-cart"></i>Añadir a lista de deseos</button>
															</form>
															<form action="E_commerce_servlet" method="post">
																<input class="input" type="hidden" name="productoacarrito" value="<%=counter%>">
																<input class="input" type="hidden" name="cantidad" value="1"> 
																<button class="primary-btn add-to-cart" type="submit" name="action" value="add_to_cart_product">
																<i class="fa fa-shopping-cart"></i>Añadir al carrito</button>
															</form>
														</div>
													<%} %>
												</div>
											</div>
										<!-- /Product Single -->				
									<%counter++; size++; %>
									<%}
								}%>
							</div>
						</div>
					</div>
				<!-- /Product Slick -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	<div class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- banner -->
				<div class="col-md-8">
					<div class="banner banner-1">
						<img src="./img/home3.jpg" alt="">
						<div class="banner-caption text-center">
							<h1 class="primary-color">PRECIO INMEJORABLE<br><span class="white-color font-weak">SIN DESCUENTOS</span></h1>
							<form action="E_commerce_servlet" method="post">
								<%int random = (int) (Math.random()*(elementos.size()));%>
								<input class="input" type="hidden" name="counter" value="<%=random%>">
								<button class="primary-btn"
								type="submit" name="action" value="productpage">
								COMPRAR AHORA</button>
							</form>
						</div>
					</div>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1">
						<img src="./img/home1.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NUEVOS PRODUCTOS</h2>
							<form action="E_commerce_servlet" method="post">
								<%int random2 = (int) (Math.random()*(elementos.size()));%>
								<input class="input" type="hidden" name="counter" value="<%=random2%>">
								<button class="primary-btn"
								type="submit" name="action" value="productpage">
								COMPRAR AHORA</button>
							</form>
						</div>
					</a>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1">
						<img src="./img/home2.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NUEVOS PRODUCTOS</h2>
							<form action="E_commerce_servlet" method="post">
								<%int random3 = (int) (Math.random()*(elementos.size()));%>
								<input class="input" type="hidden" name="counter" value="<%=random3%>">
								<button class="primary-btn"
								type="submit" name="action" value="productpage">
								COMPRAR AHORA</button>
							</form>
						</div>
					</a>
				</div>
				<!-- /banner -->
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
