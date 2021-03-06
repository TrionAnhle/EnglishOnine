<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="HocVienURL" value="/admin-hocvien?type=list"/>
<html>
<head>
    <title>Danh sách học viên</title>
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
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert}">
                        ${messageResponse}
                </div>
            </c:if>
            <div class="page-content">
                <div class="row" >
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="title-list-student">
                                    <h2><ion-icon name="people-outline"></ion-icon> DANH SÁCH HỌC VIÊN</h2>
                                </div>
                                <div class="row">
                                    <div class="col-xs-11">
                                        <input type="button" class="btn btn-sm btn-primary btn-danger" value="Xóa khỏi khóa học" id="btnDelete"/>
                                        <div class="label-checkbox label-student">
                                            <p style="color: red">Vui lòng chọn học viên cần xóa</p>
                                        </div>
                                    </div>
                                    <div class="col-xs-1">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                           title='Thêm khóa học' href='<c:url value="/admin-khoahoc?type=list&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc"/>'>
                                                <span>
                                                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                                                </span>
                                        </a>
                                    </div>
                                </div>
                                <div>
                                    <div class="card-header-student">
                                        <ion-icon name="newspaper-outline"></ion-icon>
                                        ${modelKhoaHoc.tenKhoaHoc}
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th>Tên học viên</th>
                                                <th>Giới tính</th>
                                                <th>Email</th>
                                                <th>Địa chỉ</th>
                                                <th>Số điện thoại</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="item" items="${model.listResult}">
                                                <tr>
                                                    <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                                    <td>${item.hoTen}</td>
                                                    <td>
                                                        <c:if test="${item.gioiTinh == true}">Nam</c:if>
                                                        <c:if test="${item.gioiTinh == false}">Nữ</c:if>
                                                    </td>
                                                    <td>${item.email}</td>
                                                    <td>${item.diaChi}</td>
                                                    <td>${item.soDienThoai}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <input type="hidden" value="${modelKhoaHoc.id}" id="idKhoaHoc"/>
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" id="page" name="page"/>
                                        <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                        <input type="hidden" value="" id="sortName" name="sortName"/>
                                        <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                        <input type="hidden" value="" id="type" name="type"/>
                                    </div>
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

<%--modal xoa--%>
<div class="blurred-background"></div>
<div class="confirm-delete">
    <div class="modal-content">
        <div class="modal-header">
            <h4>Xác nhận xóa</h4>
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
        </div>
        <div class="modal-body">
            Bạn có chắc chắn muốn xóa không?
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-close" data-dismiss="modal">Hủy</button>
            <button type="button" class="btn btn-danger btn-delete">Xóa</button>
        </div>
    </div>
</div>

<script>
    <%--var totalPages = ${model.totalPage};--%>
    <%--var currentPage = ${model.page};--%>
    <%--var limit = 3;--%>
    <%--$(function () {--%>
    <%--    window.pagObj = $('#pagination').twbsPagination({--%>
    <%--        totalPages: totalPages,--%>
    <%--        visiblePages: 3,--%>
    <%--        startPage: currentPage,--%>
    <%--        onPageClick: function (event, page) {--%>
    <%--            if (currentPage != page) {--%>
    <%--                $('#maxPageItem').val(limit);--%>
    <%--                $('#page').val(page);--%>
    <%--                $('#sortName').val('tenKhoaHoc');--%>
    <%--                $('#sortBy').val('desc');--%>
    <%--                $('#type').val('list');--%>

    <%--                // để chuyển được trang thì ta phải submit form đến '/admin-khoahoc'--%>
    <%--                $('#formSubmit').submit();--%>
    <%--            }--%>
    <%--        }--%>
    <%--    });--%>
    <%--});--%>

    $('#btnDelete').click(function (e) {
        e.preventDefault();
        var id = $('#idKhoaHoc').val();
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;

        if (ids.length == 0) {
            $('.label-checkbox').addClass('show-up');
        } else {
            $('.confirm-delete').addClass('show-up');
            $('.blurred-background').addClass('show-up');
            $('.close').click(function(event) {
                $('.confirm-delete').removeClass('show-up');
                $('.blurred-background').removeClass('show-up');
                $('.label-checkbox').removeClass('show-up');
            });
            $('.btn-close').click(function(event) {
                $('.confirm-delete').removeClass('show-up');
                $('.blurred-background').removeClass('show-up');
                $('.label-checkbox').removeClass('show-up');
            });
            $('.btn-delete').click(function(event) {
                $('.confirm-delete').removeClass('show-up');
                $('.blurred-background').removeClass('show-up');
                $('.label-checkbox').removeClass('show-up');
                deleteHocVien(data,id);
            });
        }
    })

    function deleteHocVien(data,id) {
        $.ajax({
            url: '<c:url value="/api-admin-dangkykhoahoc?id="/>'+id,
            type: 'DELETE',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${HocVienURL}&id="+id+"&message=xoa_thanhcong";
            },
            error: function (error) {
                window.location.href = "${HocVienURL}&id="+id+"&message=loi_hethong";
            }
        })
    }
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
