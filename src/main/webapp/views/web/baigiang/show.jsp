<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Bài giảng</title>
</head>
<body>
    <div class="lesson-container">
        <div class="full-width lesson-content">
            <div class="content-cs">
                <ol class="breadcrumb-lesson">
                    <li>
                        <a href="<c:url value="/trang-chu"/>">
                            <span class="bread-name">Trang chủ</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/khoa-hoc?type=list&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc"/>">
                            <span class="bread-name">Khóa học</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/bai-giang?type=show&idKH=${modelKhoaHoc.id}&idBG=&idUSER=${USERMODEL.id}"/>">
                            <span class="bread-name">${modelKhoaHoc.tenKhoaHoc}</span>
                        </a>
                    </li>
                    <li class="breadcrumb-active">
                        <span class="bread-name">${modelBaiGiang.tenBaiGiang}</span>
                    </li>
                </ol>
                <div class="content-cont">
                    <!-- title -->
                    <div class="mb-4">
                        <h1 class="h2">${modelBaiGiang.tenBaiGiang}</h1>
                    </div>

                    <!-- Author -->
                    <div class="border-top border-bottom py-4 mb-5">
                        <div class="row align-items-md-center">
                            <div class="col-md-7 mb-5 mb-md-0">
                                <div class="media align-items-center">
                                    <div class="avatar avatar-circle">
                                        <img class="avatar-img" src="<c:url value="/template/image/user.png"/>" alt="" width="50" height="50" loading="lazy">
                                    </div>
                                    <div class="media-body ml-3">
                                        <span class="h6 text-info">${tenGiaoVien}</span>
                                        <span class="d-block text-muted">${modelKhoaHoc.thoiGianTao}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="d-flex justify-content-md-end align-items-center">
                                    <span class="d-block small font-weight-bold text-capitalize mr-2">Chia sẻ:</span>
                                    <a href="#" class="side-icon fb"><ion-icon name="logo-facebook"></ion-icon></a>
                                    <a href="#" class="side-icon tw"><ion-icon name="logo-twitter"></ion-icon></a>
                                    <a href="#" class="side-icon ig"><ion-icon name="logo-instagram"></ion-icon></a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content -->
                    <div>
                        ${modelBaiGiang.noiDung}
                    </div>
                    <div class="media align-items-center border-top border-bottom py-5 my-8">
                        <div class="avatar avatar-circle avatar-xl">
                            <img class="avatar-img ls-is-cached lazyloaded" src="<c:url value="/template/image/user.png"/>" alt="Hình dại diện" width="90" height="90" loading="lazy">
                        </div>
                        <div class="media-body ml-3">
                            <h3 class="mb-0 text-info">
                                ${tenGiaoVien}
                            </h3>
                            <p class="mb-0 small">Hãy chia sẻ cho mọi người cùng biết đến khóa học!!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="sidebar-left">
            <div class="content-cs-fixed" id="content-id">
                <h2 class="left">Danh sách bài giảng</h2>
                <c:set var="i" value="${0}" />
                <c:forEach var="item" items="${model.listResult}">
                    <c:url var="showURL" value="/bai-giang">
                        <c:param name="type" value="learn"/>
                        <c:param name="idKH" value="${modelKhoaHoc.id}"/>
                        <c:param name="idBG" value="${item.id}"/>
                        <c:param name="index" value="${i}"/>
                    </c:url>
                    <c:set var="i" value="${i + 1}" />
                    <a href='${showURL}' class="btnn list-item">${item.tenBaiGiang}</a>
                </c:forEach>
            </div>
        </div>
    </div>

    <script>
        var index = ${index};
        var header = document.getElementById("content-id");
        var btns = header.getElementsByClassName("btnn");

        for (var i = 0; i < btns.length; i++) {
            if (index == i) {
                btns[i].className += " active-lesson";
            }
        }

        for (var i = 0; i < btns.length; i++) {
            btns[i].addEventListener("click", function() {
                var current = document.getElementsByClassName("active-lesson");
                current[0].className = current[0].className.replace(" active-lesson", "");
                this.className += " active-lesson";
            });
        }
    </script>

    <script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script></body>
</html>
