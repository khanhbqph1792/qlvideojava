<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/Ass_Java4/css/bootstrap.min.css" />
</head>
<body class="container">
	<header class="row">
		<h1 class="alert alert-success">FPT Polytechnic</h1>
	</header>
	<nav class="row bg-info">
		<nav class="navbar navbar-expand-sm navbar-light bg-light">
			<a class="navbar-brand" href="homepage">Home</a>
			<button class="navbar-toggler d-lg-none" type="button"
				data-toggle="collapse" data-target="#multiCollapseExample2"
				aria-controls="collapsibleNavId" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggle-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavId">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">

					<li class="nav-item"><a class="nav-link" href="UserServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>User
					</a></li>
					<li class="nav-item"><a class="nav-link" href="VideoServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>Video
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-comments" aria-hidden="true"></i>My Favorites
					</a></li>
					<li class="nav-item"><a class="nav-link" href="ReportManagementServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>Report
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> My Account </a>
						<ul class="dropdown-menu">
							<c:if test="${ ! isLogin }">
								<li><a class="dropdown-item" href="Login">Login</a></li>
								<li><a class="dropdown-item" href="ForgotPassword">Forgot Password</a></li>
								<li><a class="dropdown-item"
									href="RegistrationServlet/login">Registration</a></li>
							</c:if>
							<c:if test="${ isLogin }">
								<li><a class="dropdown-item" href="Logoff">Logoff</a></li>
								<li><a class="dropdown-item" href="ChangePassword">Change
										Password</a></li>
								<li><a class="dropdown-item" href="EditProfile">Edit
										Profile</a></li>
							</c:if>
						</ul></li>
				</ul>
			</div>
		</nav>
	</nav>
	<div class="row">
		<div class="col">
			<c:if test="${ not empty message }">
				<div class="alert alert-success">${message}</div>
			</c:if>
			<c:if test="${ not empty error }">
				<div class="alert alert-danger">${error}</div>
			</c:if>
		</div>
	</div>
	<main class="container">
		<div class="row">
			<div class="col-7">
				<div class="card text-center mt-3">
					<div class="card-body">
						<div class="row">
							<img class="img-fluid" alt=""
								src="${ empty poster ? 'images/1002.jpg' : poster }" width="90%">
						</div>
						<div class="row p-2">
							<ul>
								<li>${ description }</li>
								<li>${ title }</li>
								<li>${ views }</li>
							</ul>
						</div>
					</div>
					<div class="card-footer text-right">
						<a href="" class="btn btn-success">Like</a> <a href=""
							class="btn btn-info">Share</a>
					</div>
				</div>
			</div>
			<div class="col-5">
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
				<div class="row border mt-3 mb-3">
					<div class="col-2">
						<img class="img-fluid" alt="" src="../images/1002.jpg">
					</div>
					<div class="col-10 border-left">Video Title</div>
				</div>
			</div>
		</div>
	</main>
	<script src="/Ass_Java4/js/jquery.min.js"></script>
	<script src="/Ass_Java4/js/popper.min.js"></script>
	<script src="/Ass_Java4/js/bootstrap.min.js"></script>
</body>
</html>