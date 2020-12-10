<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-giaovien"/>
<c:url var ="GiaovienURL" value="/admin-giaovien"/>
<html>
<head>
    <title>Danh sách giáo viên</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/admin-giaovien'/>" id="formSubmit" method="get">
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
                            <h2><ion-icon name="people-outline"></ion-icon> DANH SÁCH GIÁO VIÊN</h2>
                        </div>
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                           title='Thêm giáo viên' href='<c:url value="/admin-giaovien?type=edit"/>'>
                                            <span>
                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                            </span>
                                        </a>
                                        <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa giáo viên'>
                                            <span>
                                                <i class="fa fa-trash-o bigger-110 pink"></i>
                                            </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="label-checkbox">
                            <p style="color: red">Vui lòng chọn giáo viên cần xóa</p>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th>Mã giáo viên</th>
                                            <th>Tên giáo viên</th>
                                            <th>Email</th>
                                            <th>Chỉnh sửa</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                                <td>${item.id}</td>
                                                <td>${item.hoTen}</td>
                                                <td>${item.email}</td>

                                                <td>
                                                    <c:url var="editURL" value="/admin-giaovien">
                                                        <c:param name="type" value="edit"/>
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật giáo viên" href='${editURL}'>
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:if test="${tongGiaoVien > 3}">
                                        <ul class="pagination" id="pagination"></ul>
                                    </c:if>
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
                    $('#sortName').val('hoTen');
                    $('#sortBy').val('desc');
                    $('#type').val('list');

                    // để chuyển được trang thì ta phải submit form đến '/admin-giaovien'
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
                xoaGiaoVien(data);
            });
        }
    });

    function xoaGiaoVien(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${GiaovienURL}?type=list&maxPageItem=3&page=1&message=xoa_thanhcong";
            },
            error: function (error) {
                window.location.href = "${GiaovienURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
