<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

<title>Trang chủ</title>
</head>
<body>
  	<header class="bg-primary mb-5">
		<nav class="navbar navbar-expand-lg navbar-dark">
			<div class="container">
				<a class="navbar-brand" href='<c:url value="/trang-chu"/>'>English Online</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<%-- searchbar --%>
				<div class="cntr">
					<div class="cntr-innr">
						<label class="search" for="inpt_search">
							<input id="inpt_search" name="inpt_search" type="text" placeholder="..." onkeypress="keyPressSearch(event)"/>
						</label>
					</div>
				</div>
				<%-- dropdown menu --%>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item">
							<a class="nav-link" href='<c:url value="/khoa-hoc?type=list&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc"/>'>
								<ion-icon name="library"></ion-icon>
								Khóa học
							</a>
						</li>
						<c:if test="${empty USERMODEL}">
							<li class="nav-item">
								<a class="nav-link" href='<c:url value="/dang-nhap?action=dangnhap"/>'>Đăng nhập</a>
							</li>
						</c:if>
						<c:if test="${not empty USERMODEL}">
							<li class="nav-item active">
								<div class="dropdown">
									<a class="nav-link dropbtn" href="#">
									<span class="avatar avatar-sm rounded-circle">
									  	<img class="user-img-header" alt="EO" src="<c:url value="/template/image/unnamed.png"/>" width="36" height="36">
									</span>
										<span class="dropdown-name">${USERMODEL.hoTen}</span>
									</a>
									<div class="dropdown-content">
										<c:if test="${USERMODEL.vaiTro == 'giaovien' || USERMODEL.vaiTro == 'admin'}">
											<a href='<c:url value="/admin-trang-chu"/>'>Trang quản trị</a>
											<a href='<c:url value="/trang-chu?action=thoat"/>'>Đăng xuất</a>
										</c:if>
										<c:if test="${USERMODEL.vaiTro == 'hocvien'}">
											<a href='<c:url value="/cap-nhat?id=${USERMODEL.id}"/>'>Cập nhật thông tin</a>
											<a href='<c:url value="/khoa-hoc?type=own&id=${USERMODEL.id}&page=1&maxPageItem=8&sortName=tenKhoaHoc&sortBy=asc"/>'>Các khóa học</a>
											<a href='<c:url value="/trang-chu?action=thoat"/>'>Đăng xuất</a>
										</c:if>
									</div>
								</div>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-lg-12 main-title">
					<h1 class="display-4 text-white mt-5 mb-2">HỌC NGOẠI NGỮ MIỄN PHÍ <br> ĐÁNH BAY NỖI SỢ TIẾNG ANH</h1>
					<c:if test="${empty USERMODEL}">
						<a class="btn btn-full js--button-plan" href="<c:url value="/dang-nhap?action=dangnhap&type=dangky"/>">Đăng ký ngay</a>
						<a class="btn btn-ghost js--button-start" href="<c:url value="/dang-nhap?action=dangnhap"/>">Tôi đã có tài khoản</a>
					</c:if>
				</div>
			</div>
		</div>
  	</header>
	<div class="container js--section-features">
		<div class="row">
			<div class="col-md-8 mb-5">
				<h2>English Online</h2>
				<hr>
				<p>Các bài học Tiếng Anh từ dễ đến khó. Vui nhộn, hiệu quả và miễn phí 100%. <br> Dù bạn mới bắt đầu học ngoại ngữ hoặc đang muốn nâng cao trình độ viết và đọc, tham gia cùng chúng tôi ngay!</p>
				<p>Các khóa học của chúng tôi là bước đột phá trong việc học tiếng Anh hiện nay tại Việt Nam. Khóa học sẽ giúp người học củng cố, hệ thống lại kiến thức ngữ pháp, từ vựng, có một lộ trình học rõ ràng, chi tiết với thang điểm đánh giá từng nấc cụ thể.</p>
				<a class="btn btn-primary btn-lg" href="<c:url value="/khoa-hoc?type=list&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc"/> ">Khám phá ngay &raquo;</a>
			</div>
			<div class="col-md-4 mb-5">
				<h2>Liên Lạc</h2>
				<hr>
				<address>
					<strong>English Online</strong>
					<br>3481 PTIT Man Thiện
					<br>Tăng Nhơn Phú A, Quận 9
					<br>
				</address>
				<address>
					<abbr title="Phone">ĐT:</abbr>
					0908 328 610
					<br>
					<abbr title="Email">E:  </abbr>
					<a href="mailto:#">englishonline@gmail.com</a>
				</address>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="feedback-title mb-5">
				<h2>Khóa học mới</h2>
			</div>
			<c:forEach var="item" items="${modelMoi.listResult}">
				<c:url var="showURL" value="/bai-giang">
					<c:param name="type" value="show"/>
					<c:param name="idKH" value="${item.id}"/>
					<c:param name="idBG" value=""/>
					<c:param name="idUSER" value="${USERMODEL.id}"/>
				</c:url>
				<div class="col-md-3 mb-5">
					<div class="card h-100 transition-3d-hover">
						<img class="card-img-top" src="${initParam['file-upload']}${item.anh}" width="240px" height="160px" alt="">
						<div class="card-body">
							<strong class="card-title">${item.tenKhoaHoc}</strong>
							<p class="card-text details-short">Giáo Viên: ${item.tenGiaoVien}</p>
						</div>
						<div class="card-footer">
							<c:if test="${empty USERMODEL}">
								<c:url var="dangnhapURL" value="/dang-nhap">
									<c:param name="action" value="dangnhap"/>
									<c:param name="type" value="show"/>
									<c:param name="idKH" value="${item.id}"/>
								</c:url>
								<a class="btn btn-primary" href="${dangnhapURL}">Vào Học</a>
							</c:if>
							<c:if test="${not empty USERMODEL}">
								<a class="btn btn-primary" href='${showURL}'>Vào học</a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="feedback-title mb-5">
				<h2>Khóa học nhiều học viên đăng ký</h2>
			</div>
			<c:forEach var="item" items="${modelHot.listResult}">
				<c:url var="showURL" value="/bai-giang">
					<c:param name="type" value="show"/>
					<c:param name="idKH" value="${item.id}"/>
					<c:param name="idBG" value=""/>
					<c:param name="idUSER" value="${USERMODEL.id}"/>
				</c:url>
				<div class="col-md-3 mb-5">
					<div class="card h-100 transition-3d-hover">
						<img class="card-img-top" src="${initParam['file-upload']}${item.anh}" width="240px" height="160px" alt="">
						<div class="card-body">
							<strong class="card-title">${item.tenKhoaHoc}</strong>
							<p class="card-text details-short">Giáo Viên: ${item.tenGiaoVien}</p>
						</div>
						<div class="card-footer">
							<c:if test="${empty USERMODEL}">
								<c:url var="dangnhapURL" value="/dang-nhap">
									<c:param name="action" value="dangnhap"/>
									<c:param name="type" value="show"/>
									<c:param name="idKH" value="${item.id}"/>
								</c:url>
								<a class="btn btn-primary" href="${dangnhapURL}">Vào Học</a>
							</c:if>
							<c:if test="${not empty USERMODEL}">
								<a class="btn btn-primary" href='${showURL}'>Vào Học</a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- /.row -->

		<div class="row space-2">
			<div class="feedback-title mb-5">
				<h2>Nhận xét từ học viên</h2>
			</div>
			<div class="card-deck d-block d-lg-flex card-lg-gutters-2">
				<%-- Testimonials --%>
				<div class="card shadow-none bg-transparent">
					<div class="card-body">
						<ul class="list-inline text-warning small">
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
						</ul>
						<div class="mb-auto">
							<p class="mb-0">
								Mình là một học viên của EO. Trước khi học ở đây mình đã rất tự ti về khả năng nói tiếng Anh của mình, nhưng chỉ sau một khóa học mình đã có được phương pháp luyện tiếng Anh hiệu quả.
							</p>
						</div>
					</div>
					<div class="card-footer border-0 bg-transparent pt-0 px-5 pb-5">
						<div class="media">
							<div class="avatar avatar-circle mr-3">
								<img class="avatar-img" src="<c:url value="/template/image/man1.jpg"/>"  alt="student-image" loading="lazy" width="40" height="40">
							</div>
							<div class="media-body">
								<h4 class="mb-1">Quang Hòa</h4>
								<small class="d-block text-body">Học sinh cấp ba</small>
							</div>
						</div>
					</div>
				</div>
				<%-- End Testimonials --%>

				<%-- Testimonials --%>
				<div class="card text-white bg-primary-fb shadow-primary-lg">
					<div class="card-body">
						<ul class="list-inline text-warning small">
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
						</ul>
						<div class="mb-auto">
							<p class="text-white mb-0">
								Mình chưa bao giờ nghĩ rằng mình có thể giao tiếp tiếng Anh với người nước ngoài trôi chảy cho đến khi mình đến đăng ký khóa học tiếng anh tại EnglishOnline							</p>
						</div>
					</div>
					<div class="card-footer bg-primary-fb border-0 pt-0 px-5 pb-5">
						<div class="media">
							<div class="avatar avatar-circle mr-3">
								<img class="avatar-img" src="<c:url value="/template/image/man2.jpg"/>" alt="student-image" loading="lazy" width="40" height="40">
							</div>
							<div class="media-body">
								<h4 class="text-white mb-1">Tuấn Anh</h4>
								<small class="d-block text-light">Người đi làm</small>
							</div>
						</div>
					</div>
				</div>
				<%-- End Testimonials --%>

				<%-- Testimonials --%>
				<div class="card shadow-none bg-transparent">
					<div class="card-body">
						<ul class="list-inline text-warning small">
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
							<li class="list-inline-item mx-0">
								<ion-icon name="star"></ion-icon>
							</li>
						</ul>
						<div class="mb-auto">
							<p class="mb-0">
								Các thầy cô không chỉ giúp mình tìm ra phương pháp học hiệu quả mà còn nhiệt tình giải đáp các thắc mắc trong suốt quá trình học. Mình cảm thấy thoải mái và tự tin khi nói tiếng Anh hơn trước rất nhiều.
							</p>
						</div>
					</div>
					<div class="card-footer border-0 bg-transparent pt-0 px-5 pb-5">
						<div class="media">
							<div class="avatar avatar-circle mr-3">
								<img class="avatar-img" src="<c:url value="/template/image/woman.jpg"/>" alt="student-image" loading="lazy" width="40" height="40">
							</div>
							<div class="media-body">
								<h4 class="mb-1">Hoàng Thị Hảo</h4>
								<small class="d-block text-body">Sinh viên</small>
							</div>
						</div>
					</div>
				</div>
				<%-- End Testimonials --%>
			</div>
		</div>
  	</div>

	<div class="bg-img-hero text-center">
		<div class="container space-2">
			<div class="mb-5">
				<h2 class="text-white">Miễn phí, nhanh chóng</h2>
			</div>
			<a class="btn-footer" href='<c:url value="/dang-nhap?action=dangnhap"/>'>Tham gia ngay</a>
		</div>
	</div>

	<div class="footer-ms">
		<div>
			<a class="navbar-brand" href='<c:url value="/trang-chu"/>'>English Online</a>
		</div>
		<div class="footer-ms--child">
			<p class="m-0 text-center">Copyright &copy; Your Website 2020</p>
		</div>
		<div class="footer-ms--child">
			<a href="#" class="side-icon fb"><ion-icon name="logo-facebook"></ion-icon></a>
			<a href="#" class="side-icon tw"><ion-icon name="logo-twitter"></ion-icon></a>
			<a href="#" class="side-icon ig"><ion-icon name="logo-instagram"></ion-icon></a>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('.js--section-features').waypoint(function(direction) {
				if (direction == "down") {
					$('nav').addClass('sticky');
				} else {
					$('nav').removeClass('sticky');
				}
			}, {
				offset: '80px'
			});
		});
	</script>

	<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>