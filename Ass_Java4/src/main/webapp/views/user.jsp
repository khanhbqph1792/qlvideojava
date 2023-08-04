<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<a class="navbar-brand" href="homepage">OnEn</a>
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
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> My Account </a>
						<ul class="dropdown-menu">
							<c:if test="${ ! isLogin }">
								<li><a class="dropdown-item" href="/Ass_Java4/Login">Login</a></li>
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
	<div class="row mt-3 table responsive">
		<div class="col-12 bg- pt-3 pb-3">
			<div class="container">
				<div class="row" style="text-align: left;">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active" id="home-tab"
								data-bs-toggle="tab" data-bs-target="#home-tab-pane"
								type="button" role="tab" aria-controls="home-tab-pane"
								aria-selected="true">User Editing</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
								data-bs-target="#profile-tab-pane" type="button" role="tab"
								aria-controls="profile-tab-pane" aria-selected="false">User
								List</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home-tab-pane"
							role="tabpanel" aria-labelledby="home-tab" tabindex="0">
							<form action="" method="post">
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label for="username">Username</label> <input type="text"
														class="form-control" name="username" id="username"
														aria-describedby="usernameHId" placeholder="Username">
													<small id="usernameHId" class="form-text text-muted">Username
														is required!</small>
												</div>
												<div class="form-group">
													<label for="fullname">Fullname</label> <input type="text"
														class="form-control" name="fullname" id="fullname"
														aria-describedby="fullnameHId" placeholder="Fullname">
													<small id="fullnameHId" class="form-text text-muted">Fullname
														is required!</small>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label for="password">Password</label> <input
														type="password" class="form-control" name="password"
														id="password" placeholder="Password">

												</div>
												<div class="form-group">
													<label for="email">Email</label> <input type="text"
														class="form-control" name="email" id="email"
														aria-describedby="emailHId" placeholder="Email"> <small
														id="emailHId" class="form-text text-muted">Email
														is required!</small>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
							<button formaction="/Ass_Java4/VideoServlet/insert"
								class="btn btn-primary">Create</button>
							<button formaction="/Ass_Java4/VideoServlet/update"
								class="btn btn-warning">Update</button>
							<button formaction="/Ass_Java4/VideoServlet/reset"
								class="btn btn-info">Reset</button>
						</div>
						<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel"
							aria-labelledby="profile-tab" tabindex="0">
							<table class="table table-striped table-hover">
								<tr>
									<td>Username</td>
									<td>Fullname</td>
									<td>Email</td>
									<td>Role</td>
									<td>Click</td>
								</tr>
								<c:forEach var="item" items="${items}">
									<tr>
										<td>${item.id}</td>
										<td>${item.title}</td>
										<td>${item.views}</td>
										<td>${item.active?'Active':'In Active'}</td>
										<td><a href="/Ass_Java4/VideoServlet/edit?id=${item.id}"><i
												class="fa fa-edit" aria-hidden="true"></i>Edit</a> <a
											href="/Ass_Java4/VideoServlet/delete?id=${item.id}"><i
												class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
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
	<script>
	const triggerTabList = document.querySelectorAll('#myTab button')
	triggerTabList.forEach(triggerEl => {
	  const tabTrigger = new bootstrap.Tab(triggerEl)

	  triggerEl.addEventListener('click', event => {
	    event.preventDefault()
	    tabTrigger.show()
	  })
	})
	<script src="/Ass_Java4_Khanhbqph17929/js/jquery.min.js"></script>
	<script src="/Ass_Java4_Khanhbqph17929/js/popper.min.js"></script>
	<script src="/Ass_Java4_Khanhbqph17929/js/bootstrap.min.js"></script>
</body>
</html>