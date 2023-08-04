<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
			<a class="navbar-brand" href="/Ass_Java4/homepage">Home</a>
			<button class="navbar-toggler d-lg-none" type="button"
				data-toggle="collapse" data-target="#multiCollapseExample2"
				aria-controls="collapsibleNavId" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggle-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavId">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">

					<li class="nav-item"><a class="nav-link" href="/Ass_Java4/UserServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>User
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/Ass_Java4/VideoServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>Video
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-comments" aria-hidden="true"></i>My Favorites
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/Ass_Java4/ReportManagementServlet"> <i
							class="fa fa-id-card" aria-hidden="true"></i>Report
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> My Account </a>
						<ul class="dropdown-menu">
							<c:if test="${ ! isLogin }">
								<li><a class="dropdown-item" href="/Ass_Java4/Login">Login</a></li>
								<li><a class="dropdown-item" href="/Ass_Java4/ForgotPassword">Forgot Password</a></li>
								<li><a class="dropdown-item"
									href="/Ass_Java4/RegistrationServlet/login">Registration</a></li>
							</c:if>
							<c:if test="${ isLogin }">
								<li><a class="dropdown-item" href="/Ass_Java4/Logoff">Logoff</a></li>
								<li><a class="dropdown-item" href="/Ass_Java4/ChangePassword">Change
										Password</a></li>
								<li><a class="dropdown-item" href="/Ass_Java4/EditProfile">Edit
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
		<section class="row">
			<div class="offset-3 col-6">
				<form action="ShareVideo" method="post">
					<input type="hidden" name="videoId" value="${ videoId }"/>
					<div class="card">
						<div class="card-header">Send Video to Your Friend</div>
						<div class="card-body">
							<div class="form-group">
								<label for="email">Your Friends' emails:</label>
								<input type="text" class="form-control" name="email" id="email" aria-describedby="helpId" placeholder="Emails">
								<small id="helpId" class="form-text text-muted">Email is required!</small>
							</div>
						</div>
						<div class="card-footer">
							<button class="btn btn-success">Send</button>
						</div>
					</div>
				</form>
			</div>
		</section>
	</main>
	<div class="card card-footer">
		<footer class="row col-sm-12">
			<div class="panel panel-default">
				<div class="panel-body text-center">
					<h4>FPT Polytechnic &copy; 2022. All rights reserved</h4>
				</div>
			</div>
		</footer>
	</div>
	<script src="/Ass_Java4/js/jquery.min.js"></script>
	<script src="/Ass_Java4/js/popper.min.js"></script>
	<script src="/Ass_Java4/js/bootstrap.min.js"></script>
</body>
</html>