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
				<a href="E_commerce_servlet?action=createProduct" style="color: rgb(0, 0, 255)">Añadir Producto</a>
				<div class="section-title">
					<h4 class="title">Your Products</h4>
				</div>
				<h5> Resultado o contenido en la base de datos </h5> 
				<a href="controlador?accion=extraerProducto">Ver totos los elementos</a>
<%
List<Producto> elementos= new ArrayList<Producto>();
Usuario user = (Usuario) session.getAttribute("user");
Object lista = user.getProductos();
/*
HttpSession mysession = request.getSession();
Usuario user = (Usuario) session.getAttribute("user");
Object lista = user.getEmail();

*/
System.out.print(lista);
  if (lista != null){
	if(lista instanceof List){
		elementos = (List<Producto>)lista;
		for(Producto elemento: elementos){ %>
			<h5>Titulo:<%=elemento.getIdProducto() %> (Id_Imagen: <%=elemento.getImagen() %>) </h5>
			<!--  Esta manera de mostrar una imagen requiere descargar una libreria de apache
			      https://commons.apache.org/proper/commons-codec/download_codec.cgi
			       y colocar el .jar dentro de la carpeta WebContent/WEB-INF/lib/   -->
			<!--  Todo el siguiente código quedaría bonito en una clase con un método estático
			que fuera un util para catalogo.jsp -->
			<img style="height: 50px;" src="<% StringBuilder sb = new StringBuilder();
						sb.append("data:image/png;base64,");
						sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(elemento.getImagen(), false)));
						out.print(sb.toString()); %>">
		<% }
	}
}%>	
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