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
				<li><a href="E_commerce_servlet?action=products">Products</a></li>
				<!-- TODO -->
				<li><a href="#">Category</a></li>
				<li class="active">Spire F VRT</li>
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
								<img src="./img/LamboTrac1.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac2.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac3.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac4.jpg" alt="">
							</div>
						</div>
						<div id="product-view">
							<div class="product-view">
								<img src="./img/LamboTrac_thumb1.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac2.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac3.jpg" alt="">
							</div>
							<div class="product-view">
								<img src="./img/LamboTrac4.jpg" alt="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="product-body">
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

							<div class="product-btns">
								<div class="qty-input">
									<span class="text-uppercase">QTY: </span>
									<input class="input" type="number">
								</div>
								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
								<div class="pull-right">
									<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
									<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
									<button class="main-btn icon-btn"><i class="fa fa-share-alt"></i></button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="product-tab">
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
								<li><a data-toggle="tab" href="#tab2">Technologies</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab1" class="tab-pane fade in active">
									<h4>DESIGN</h4>
									<p>Spire F VRT stands out not only for its exclusive, elegant design in keeping with the Lamborghini family feeling, but also for a cab that boasts unprecedented comfort. Attention to details and the use of technical features usually only found on models with far higher power levels are what separate the Spire tractors from their competitors: this refined note of elegance always makes the difference. The Spire VRT models produce a good feeling straight away, and their user-friendly nature makes everything more simple, even with the most complex functions.</p>
									<h4>ENGINE</h4>
									<p>Apart from unrivalled operating comfort both in the field and on the road, the new VRT transmission also allows a top speed of 40km/h at low engine speeds, for limited consumption. To maximise productivity, automatic front wheel drive enabling and differential locking are standard features, and the Spire F VRT can even be managed without touching the accelerator, clutch, gears and brakes... using just the joystick! There's a dedicated potentiometer for selecting the engine operating mode, so as to optimise performance and consumption in every situation, at the same time improving safety during manoeuvres thanks to "PowerZero" and the HPB hydraulic parking brake (that comes as standard).</p>
									<h4>TRANSMISSION</h4>
									<p>Apart from unrivalled operating comfort both in the field and on the road, the new VRT transmission also allows a top speed of 40km/h at low engine speeds, for limited consumption. To maximise productivity, automatic front wheel drive enabling and differential locking are standard features, and the Spire F VRT can even be managed without touching the accelerator, clutch, gears and brakes... using just the joystick! There's a dedicated potentiometer for selecting the engine operating mode, so as to optimise performance and consumption in every situation, at the same time improving safety during manoeuvres thanks to "PowerZero" and the HPB hydraulic parking brake (that comes as standard).</p>
								</div>
								<div id="tab2" class="tab-pane fade in">
									<p></p>
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
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Picked For You</h2>
					</div>
				</div>
				<!-- section title -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product04.jpg" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">$32.50</h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<div class="product-label">
								<span>New</span>
							</div>
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product03.jpg" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">$32.50</h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<div class="product-label">
								<span class="sale">-20%</span>
							</div>
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product02.jpg" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">$32.50 <del class="product-old-price">$45.00</del></h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<div class="product-label">
								<span>New</span>
								<span class="sale">-20%</span>
							</div>
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product01.jpg" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">$32.50 <del class="product-old-price">$45.00</del></h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->
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
