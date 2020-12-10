<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var ="KhoahocURL" value="/khoa-hoc"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Trang chủ" /></title>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!-- css -->
    <link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/navbar.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/searchbar.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/course.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/lesson.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/detailcourse.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,900;1,300&display=swap" rel="stylesheet">
</head>
<body>

<dec:body/>

<script type="text/javascript" src="<c:url value='/template/web/jquery/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/web/js/searchbarjs.js' />"></script>
<script type="text/javascript" src="<c:url value='/template/web/js/jquery.waypoints.min.js' />"></script>

<script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
<script>
    function keyPressSearch(event) {
        event.keyCode;
        if (event.keyCode == 13 || event.which == 13) {
            event.preventDefault();
            var key = $('#inpt_search').val();
            window.location.href = "${KhoahocURL}?type=search&key="+key+"&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc";
        }
    }
</script>
</body>
</html>