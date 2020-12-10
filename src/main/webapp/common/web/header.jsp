<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
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
