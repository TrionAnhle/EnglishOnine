<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-khoahoc"/>
<c:url var="KhoahocURL" value="/admin-khoahoc"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert}">
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <div class="title-list-student">
                            <h2><ion-icon name="people-outline"></ion-icon> DANH SÁCH KHÓA HỌC</h2>
                        </div>
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                           title='Thêm khóa học' href='<c:url value="/admin-khoahoc?type=edit"/>'>
                                            <span>
                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                            </span>
                                        </a>
                                        <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa khóa học'>
                                            <span>
                                                <i class="fa fa-trash-o bigger-110 pink"></i>
                                            </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="label-checkbox">
                            <p style="color: red">Vui lòng chọn khóa học cần xóa</p>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="checkAll"></th>
                                            <th>Tên khóa học</th>
                                            <th>Mô tả ngắn</th>
                                            <th>Tên Giáo Viên</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <div class="card-header-student">
                                            <ion-icon name="search-outline"></ion-icon>
                                            <input type="text" id="myInput" onkeypress="keyPressSearch(event)" placeholder="Tìm kiếm theo tên" title="Tìm kiếm theo tên">
                                        </div>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                                <td>${item.tenKhoaHoc}</td>
                                                <td>${item.moTaNgan}</td>
                                                <td>${item.tenGiaoVien}</td>
                                                <td>
                                                    <c:url var="editURL" value="/admin-khoahoc">
                                                        <c:param name="type" value="edit"/>
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật khóa học" href='${editURL}'>
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                    <a class="btn btn-sm btn-primary btn-listHocVien" data-toggle="tooltip"
                                                       title="Danh sách học viên" href='<c:url value="/admin-hocvien?type=list&id=${item.id}"/>'>
                                                        <i class="fa fa-users" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:if test="${tongKhoaHoc > 3}">
                                        <ul class="pagination" id="pagination"></ul>
                                    </c:if>
                                    <input type="hidden" value="" id="page" name="page"/>
                                    <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                    <input type="hidden" value="" id="sortName" name="sortName"/>
                                    <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                    <input type="hidden" value="${model.type}" id="type" name="type"/>
                                    <c:if test="${model.type=='search'}">
                                        <input type="hidden" value="${model.key}" id="key" name="key"/>
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
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
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
                    // để chuyển được trang thì ta phải submit form đến '/admin-khoahoc'
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $("#btnDelete").click(function() {
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
                xoaKhoaHoc(data);
            });
        }
    });

    function xoaKhoaHoc(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${KhoahocURL}?page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc&type=list&message=xoa_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc&type=list&message=loi_xoa";
            }
        });
    }
    function keyPressSearch(event) {
        event.keyCode;
        if (event.keyCode == 13 || event.which == 13) {
            event.preventDefault();
            var key = $('#myInput').val();
            window.location.href = "${KhoahocURL}?type=search&key="+key+"&page=1&maxPageItem=3&sortName=tenKhoaHoc&sortBy=desc";
        }
    }
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
