`<%@ page language="java" contentType="text/html; charset=utf-8"
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
		<article class="col-12 col-md-9 bg- pt-3 pb-3">
			<div class="container">
				<div class="row" style="text-align: left;">
					<div class="container">
						<div class="row">
							<c:forEach var="item" items="${ videos }">
								<div class="col-3 mt-2">
									<div class="card text-center">
										<div class="card-body">
											<img class="img-fluid" alt=""
												src="${ empty item.poster ? 'images/1002.jpg' : item.poster }"
												width="90%">
											<div class="row border-top mt-2">
												<b>${ item.title }</b>
											</div>
										</div>
										<div class="card-footer">
											<a href="LikeVideo?videoId=${ item.videoId }"
												class="btn btn-success">Like</a> <a
												href="ShareVideo?videoId=${ item.videoId }"
												class="btn btn-info">Share</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="row">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<li class="page-item disabled"><a class="page-link">Previous</a>
									</li>
									<li class="page-item"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#">2</a></li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">Next</a>
									</li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</article>
		<div class="col-12 col-md-3 pb-3 pt-3 ">
			<div class="card" style="text-align: left;">
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-star-empty"></span> <strong>THỂ LOẠI</strong>
						</div>
						<div class="list-group">
							<a class="list-group-item">Âm nhạc</a> <a class="list-group-item">Hài kịch</a>
							<a class="list-group-item">Phim hành động</a> <a
								class="list-group-item">Thiếu nhi</a> <a class="list-group-item">Gameshow</a>
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
	<script src="/Ass_Java4/js/jquery.min.js"></script>
	<script src="/Ass_Java4/js/popper.min.js"></script>
	<script src="/Ass_Java4/js/bootstrap.min.js"></script>
</body>
</html>