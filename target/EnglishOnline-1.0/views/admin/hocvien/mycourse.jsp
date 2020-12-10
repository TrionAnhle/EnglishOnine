<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="HocVienURL" value="/admin-hocvien?type=list"/>
<html>
<head>
    <title>Danh sách khóa học</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/admin-khoahoc'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin-trang-chu"/>">Trang chủ</a>
                    </li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row" >
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="title-list-student">
                                    <h2><ion-icon name="people-outline"></ion-icon> DANH SÁCH KHÓA HỌC</h2>
                                    <a href="<c:url value="/admin-hocvien?type=all"/>" data-toggle="tooltip" title="Quay lại">
                                        <ion-icon size="large" name="exit-outline"></ion-icon>
                                    </a>
                                </div>
                                <div>
                                    <div class="card-header-student">
                                        <ion-icon name="search-outline"></ion-icon>
                                        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Tìm kiếm tên khóa học" title="Tìm kiếm tên khóa học">
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="myTable">
                                            <thead>
                                            <tr>
                                                <th>Tên khóa học</th>
                                                <th>Mô tả ngắn</th>
                                                <th>Chi tiết</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="item" items="${model.listResult}">
                                                <tr>
                                                    <td>${item.tenKhoaHoc}</td>
                                                    <td>${item.moTaNgan}</td>
                                                    <td>
                                                        <a class="btn btn-sm btn-primary btn-listHocVien" data-toggle="tooltip"
                                                           title="Chi tiết khóa học" href='<c:url value="/bai-giang?type=show&idKH=${item.id}&idBG=&idUSER=${USERMODEL.id}"/>'>
<%--                                                            <i class="fa fa-users" aria-hidden="true"></i>--%>
                                                                <i class="fa fa-book" aria-hidden="true"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <input type="hidden" value="${modelKhoaHoc.id}" id="idKhoaHoc"/>
                                        <c:if test="${not empty model.listResult}">
                                            <ul class="pagination" id="pagination"></ul>
                                        </c:if>
                                        <input type="hidden" value="" id="page" name="page"/>
                                        <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                        <input type="hidden" value="" id="sortName" name="sortName"/>
                                        <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                        <input type="hidden" value="" id="type" name="type"/>
                                    </div>
                                    <c:if test="${model.listResult.size() == 0}">
                                        <ion-icon class="icon-alert" name="alert-circle-outline"></ion-icon>
                                        <span class="lesson-card-notice">Học viên chưa đăng ký khóa học nào!!</span>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->

<script>
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    var listResult = ${model.listResult};
    var limit = 3;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 3,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('tenKhoaHoc');
                    $('#sortBy').val('desc');
                    $('#type').val('list');

                    // để chuyển được trang thì ta phải submit form đến '/admin-khoahoc'
                    $('#formSubmit').submit();
                }
            }
        });
    });

    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
