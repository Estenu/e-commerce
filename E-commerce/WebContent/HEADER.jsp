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
								<option value="0">All Categories</option>
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
									String user = (String) session.getAttribute("user");
									if(user==null){
										user = "My Account";
										
									}else{
										user = "Username: " + user;
									}
								%>
								<strong class="text-uppercase"><%=user%><i
									class="fa fa-caret-down"></i></strong>
							</div> 	
							
							<!-- parteAntigua Saul -->
							<%if (null == session.getAttribute("user")){ %>
							<a href="E_commerce_servlet?action=login" class="text-uppercase">Login</a>
							/ <a href="E_commerce_servlet?action=create-account"
							class="text-uppercase">Join</a>

							<%}else{ %>
								<a href="E_commerce_servlet?action=logout" class="text-uppercase">Logout</a>
								
							<% } %>
							
							
							
							<ul class="custom-menu">
								<li ><a href="E_commerce_servlet?action=myaccount"><i
										class="fa fa-user-o"></i> My Account</a></li>
								<li><a href="E_commerce_servlet?action=wishlist"><i
										class="fa fa-heart-o"></i> My Wishlist</a></li>
								<li><a href="E_commerce_servlet?action=checkout"><i
										class="fa fa-check"></i> Checkout</a></li>
								<li id="LOGIN"><a href="E_commerce_servlet?action=login"><i
										class="fa fa-unlock-alt"></i> Login</a></li>
								<li><a href="E_commerce_servlet?action=create-account"><i
										class="fa fa-user-plus"></i> Create An Account</a></li>
								<li id="LOGOUT"><a href="E_commerce_servlet?action=logout"><i
										class="fa fa-unlock-alt"></i> Log Out</a></li>
							</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i> <span class="qty">0</span>
								</div> <strong class="text-uppercase">My Cart:</strong> <br> <span>35.20$</span>
						</a>
							<div class="custom-menu">
								<div id="shopping-cart">
									<div class="shopping-cart-list">
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">
													$32.50 <span class="qty">x3</span>
												</h3>
												<h2 class="product-name">
													<a href="#">Product Name Goes Here</a>
												</h2>
											</div>
											<button class="cancel-btn">
												<i class="fa fa-trash"></i>
											</button>
										</div>
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">
													$32.50 <span class="qty">x3</span>
												</h3>
												<h2 class="product-name">
													<a href="#">Product Name Goes Here</a>
												</h2>
											</div>
											<button class="cancel-btn">
												<i class="fa fa-trash"></i>
											</button>
										</div>
									</div>
									<div class="shopping-cart-btns">
										<button class="main-btn"
											onclick="window.location.href ='E_commerce_servlet?action=carrito';">View
											Cart</button>
										<button class="primary-btn"
											onclick="window.location.href ='E_commerce_servlet?action=checkout';">
											Checkout <i class="fa fa-arrow-circle-right"></i>
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