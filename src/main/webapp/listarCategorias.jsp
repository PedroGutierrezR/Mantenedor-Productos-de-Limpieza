<%@page import="com.desafiolatam.dao.modelo.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.List"%>
<%
List<Categoria> listaCategorias = (List) request.getAttribute("listaCategorias");
String modificacionExitosa = (String) request.getAttribute("modificacionExitosa");
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
		<a class="navbar-brand ps-3" href="principal.jsp">Productos
			Limpieza</a>
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
							</div> ?Qu? desea hacer?
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
								</a><a class="nav-link collapsed" href="procesaListarCategorias">
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
				<div class="mx-5 mt-3">
					<%
					if (!modificacionExitosa.equals("no se ha podido completar la solicitud")) {
					%>
					<div
						class="container alert alert-warning alert-dismissible fade show"
						role="alert">
						Categor?a modificada correctamente
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
					<%
					}
					%>
				</div>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Categorias</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">Dashboard</li>
					</ol>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Categorias
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>ID</th>
										<th>NOMBRE</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>NOMBRE</th>
									</tr>
								</tfoot>
								<tbody>
									<%
									for (Categoria c : listaCategorias) {
									%>
									<tr>
										<td><%=c.getIdCategoria()%></td>
										<td><%=c.getNombreCategoria()%></td>
										<td><div align='center'>
												<a class="btn btn-primary me-2"
													href="procesaModificarCategoria?id=<%=c.getIdCategoria()%>"
													role="button">modificar</a>
											</div></td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
					</div>
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
