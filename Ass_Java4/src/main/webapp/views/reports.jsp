<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active" id="home-tab"
								data-bs-toggle="tab" data-bs-target="#home-tab-pane"
								type="button" role="tab" aria-controls="home-tab-pane"
								aria-selected="true">Favorites</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
								data-bs-target="#profile-tab-pane" type="button" role="tab"
								aria-controls="profile-tab-pane" aria-selected="false">Favorites
								User List</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="contact-tab" data-bs-toggle="tab"
								data-bs-target="#contact-tab-pane" type="button" role="tab"
								aria-controls="contact-tab-pane" aria-selected="false">Share
								Friends</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home-tab-pane"
							role="tabpanel" aria-labelledby="home-tab" tabindex="0">
							<table class="table table-bordered mt-3">
								<tr>
									<td>Video Title</td>
									<td>Favorites Counts</td>
									<td>Lasted Date</td>
									<td>Oldest Date</td>
								</tr>
								<c:forEach var="item" items="${falist}">
									<tr>
										<td>${item.videoTitle}</td>
										<td>${item.favoriteCount}</td>
										<td>${item.newestDate}</td>
										<td>${item.oldestDate}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel"
							aria-labelledby="profile-tab" tabindex="0">
							<form action="" method="get">
								<div class="row mt-3">
									<div class="col">
										<div class="form-inline">
											<div class="form-group">
												<label>Video Title 
												<select name="videoUserId" id="videoUserId"
													class="form-control ml-3">
													<c:forEach var="item" items="${ vdlist }">
														<option value="${ item.videoId }"  ${ item.videoId == videoUserId?'selected':'' }>
															${ item.title }</option>
													</c:forEach>
												</select>
												</label>
												<button class="btn btn-info ml-2">Report</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col mt-3">
										<table class="table table-bordered ">
											<tr>
												<td>Username</td>
												<td>Fullname</td>
												<td>Email</td>
												<td>Favorite Date</td>
											</tr>
											<c:forEach var="item" items="${favUser}">
												<tr>
													<td>${item.userId}</td>
													<td>${item.fullname}</td>
													<td>${item.email}</td>
													<td>${item.likeDate}</td>

												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane fade" id="contact-tab-pane" role="tabpanel"
							aria-labelledby="contact-tab" tabindex="0">
							<form action="" method="get">
								<div class="row mt-3">
									<div class="col">
										<div class="form-inline">
											<div class="form-group">
												<label>Video Title 
												<select name="videoUserId" id="videoUserId"
													class="form-control ml-3">
													<c:forEach var="item" items="${ vdlist }">
														<option value="${ item.videoId }"  ${ item.videoId == videoUserId?'selected':'' }>
															${ item.title }</option>
													</c:forEach>
												</select>
												</label>
												<button class="btn btn-info ml-2">Report</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col mt-3">
										<table class="table table-striped table-hover">
											<tr>
												<td>Sender Name</td>
												<td>Sender Email</td>
												<td>Receiver Email</td>
												<td>Sent Date</td>
											</tr>
											<c:forEach var="item" items="${shaUser}">
												<tr>
													<td>${item.fullname}</td>
													<td>${item.email}</td>
													<td>${item.emails}</td>
													<td>${item.shareDate}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</form>
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
	<script>
	const triggerTabList = document.querySelectorAll('#myTab button')
	triggerTabList.forEach(triggerEl => {
	  const tabTrigger = new bootstrap.Tab(triggerEl)

	  triggerEl.addEventListener('click', event => {
	    event.preventDefault()
	    tabTrigger.show()
	  })
	})
	</script>
	<script src="/Ass_Java4/js/jquery.min.js"></script>
	<script src="/Ass_Java4/js/popper.min.js"></script>
	<script src="/Ass_Java4/js/bootstrap.min.js"></script>
</body>
</html>