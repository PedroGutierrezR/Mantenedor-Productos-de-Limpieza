<%@page import="com.desafiolatam.dao.modelo.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
Producto producto = (Producto) request.getAttribute("producto");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="principal.jsp">Productos Limpieza</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar-->
		<div class="ms-auto">
			<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
						class="fas fa-user fa-fw"></i></a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#!">Settings</a></li>
						<li><a class="dropdown-item" href="#!">Activity Log</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="procesaLogoutServlet">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">Core</div>
						<a class="nav-link" href="principal.jsp">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Home
						</a> <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapsePages" aria-expanded="false"
							aria-controls="collapsePages">
							<div class="sb-nav-link-icon">
								<i class="fas fa-book-open"></i>
							</div> ¿Qué desea hacer?
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapsePages"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link collapsed" href="procesaListarProductos">
									Listar Productos
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a> <a class="nav-link collapsed" href="procesaAgregarProducto">
									Agregar Producto
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a> <a class="nav-link collapsed" href="procesaListarCategorias">
									Listar Categorias
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a> <a class="nav-link collapsed" href="procesaAgregarCategoria">
									Agregar Categoria
									<div class="sb-sidenav-collapse-arrow">
										<i class="fas fa-angle-down"></i>
									</div>
								</a>
							</nav>
						</div>
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Usuario Conectado:</div>
					<c:out value="${sessionScope.usuarioConectado}"></c:out>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container p-5">
					<form class="row g-3" action="procesaModificarProducto"
						method="post">
						<h3 class="mb-4">
							Producto con ID:
							<%=producto.getId()%></h3>
						<input type="hidden" value="<%=producto.getId()%>"
							name="idProducto"></input>
						<div class="col-md-6">
							<label for="validationServer01" class="form-label">Nombre
								Producto </label> <input name="nombreProducto" type="text"
								class="form-control" id="validationServer01"
								value="<%=producto.getNombre()%>" required>
						</div>
						<div class="col-md-6">
							<label for="validationServer02" class="form-label">Precio</label>
							<input name="precioProducto" type="number" class="form-control"
								id="validationServer02" value="<%=producto.getPrecio()%>"
								required>
						</div>
						<div class="col-md-6">
							<label for="validationServerUsername" class="form-label">Descripción</label>
							<div class="input-group has-validation">
								<input name="descripcionProducto" type="text"
									class="form-control" id="validationServerUsername"
									aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback"
									value="<%=producto.getDescripcion()%>" required>
							</div>
						</div>
						<div class="col-md-6">
							<label for="validationServer04" class="form-label">Categoría</label>
							<select name="categoriaProducto" class="form-select"
								id="validationServer04" required>
								<option selected disabled value="">Tipo</option>
								<c:forEach var="c" items="${listaCategorias}">
									<option><c:out value="${c.nombreCategoria}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="col-12">
							<button class="btn btn-primary mt-3" type="submit">Modificar</button>
						</div>
					</form>
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Productos Limpieza
							2022</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
