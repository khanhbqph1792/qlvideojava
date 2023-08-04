<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
	<div class="row mt-3 table responsive">
		<article class="col-12 bg- pt-3 pb-3">
			<div class="container">
				<div class="row" style="text-align: left;">
					<div class="container">
						<div class="row">
							<div class="col-8 offset-2">
								<form action="/Ass_Java4/RegistrationServlet" method="post">
									<div class="card">
										<div class="card-header">
											<b>Registration</b>
										</div>
										<div class="card-body">
											<div class="row">
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
												<div class="col">
													<div class="form-group">
														<label for="username">Username</label> <input
															value="${user.userId }" type="text"
															class="form-control" name="userId" id="userId"
															aria-describedby="userIdHId" placeholder="Username">
														<small id="userIdHId" class="form-text text-muted">Username
															is required!</small>
													</div>
													<div class="form-group">
														<label for="fullname">Fullname</label> <input
															value="${user.fullname }" type="text"
															class="form-control" name="fullname" id="fullname"
															aria-describedby="fullnameHId" placeholder="Fullname">
														<small id="fullnameHId" class="form-text text-muted">Fullname
															is required!</small>
													</div>
												</div>
												<div class="col">
													<div class="form-group">
														<label for="password">Password</label> <input
															value="${user.password }" type="password"
															class="form-control" name="password" id="password"
															placeholder="Password">

													</div>
													<div class="form-group">
														<label for="email">Email</label> <input
															value="${user.email }" type="text" class="form-control"
															name="email" id="email" aria-describedby="emailHId"
															placeholder="Email"> <small id="emailHId"
															class="form-text text-muted">Email is required!</small>
													</div>
												</div>
											</div>
										</div>
										<div class="card-footer text-muted">
											<button formaction="/Ass_Java4/RegistrationServlet/login"
												class="btn btn-success">Sign Up</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
	</div>
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

