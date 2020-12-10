<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-khoahoc"/>
<c:url var="APIBGurl" value="/api-admin-baigiang"/>
<c:url var ="KhoahocURL" value="/admin-khoahoc"/>
<c:url var ="baigiangURL" value="/admin-baigiang"/>
<html>
<head>
    <title>Chỉnh sửa khóa học</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa khóa học</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert}">
                                ${messageResponse}
                        </div>
                    </c:if>
                    <div class="col-sm-10 form-group"></div>
                    <div class="col-sm-2 form-group">
                        <c:if test="${not empty model.id}">
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật khóa học" id="btnAddOrUpdateKH"/>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm khóa học" id="btnAddOrUpdateKH"/>
                        </c:if>
                    </div>
                    <form id="formSubmit" method="post" action="<c:url value="/admin-upload-anh"/>" enctype="multipart/form-data">
                        <div class="flex-container">
                            <div class="left-edit-course col-sm-8">
                                <div class="col-sm-12 form-group">
                                    <label class="control-label no-padding-right">Tên khóa học</label>
                                    <input type="text" class="form-control" id="tenKhoaHoc" name="tenKhoaHoc" value="${model.tenKhoaHoc}"/>
                                </div>
                                <br/>
                                <br/>
                                <div class="col-sm-6 form-group">
                                    <label class="control-label no-padding-right">Cấp độ</label>
                                    <select class="form-control" id="capDo" name="capDo">
                                        <c:if test="${empty model.capDo}">
                                            <option value="">Chọn cấp độ</option>
                                            <option value="De">Dễ</option>
                                            <option value="Trung Binh">Trung Bình</option>
                                            <option value="Kho">Khó</option>
                                        </c:if>
                                        <c:if test="${not empty model.capDo}">
                                            <option value="">Chọn cấp độ</option>
                                            <option value="De" <c:if test="${model.capDo == 'De'}">selected="selected"</c:if>>
                                                Dễ
                                            </option>
                                            <option value="Trung Binh" <c:if test="${model.capDo == 'Trung Binh'}">selected="selected"</c:if>>
                                                Trung Bình
                                            </option>
                                            <option value="Kho" <c:if test="${model.capDo == 'Kho'}">selected="selected"</c:if>>
                                                Khó
                                            </option>
                                        </c:if>
                                    </select>
                                </div>
                                <br/>
                                <br/>
                                <div class="col-sm-6 form-group">
                                    <label class="control-label no-padding-right">Tên Giáo Viên</label>
                                    <c:if test="${USERMODEL.vaiTro=='giaovien'}">
                                        <input type="text" class="form-control" id="hoTenGiaoVien" name="hoTenGiaoVien" value="${USERMODEL.hoTen}"/>
                                        <input type="hidden" id="giaoVienId" name="giaoVienId" value="${USERMODEL.id}"/>
                                    </c:if>
                                    <c:if test="${USERMODEL.vaiTro =='admin'}">
                                        <select class="form-control" id="giaoVienId" name="giaoVienId">
                                            <c:forEach var="item" items="${modelGiaoVien.listResult}">
                                                <option value="${item.id}" <c:if test="${item.id == model.giaoVienId}">selected="selected"</c:if> >${item.hoTen}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                                <br/>
                                <br/>
                                <div class="col-sm-8 form-group">
                                    <label class="control-label no-padding-right">Mô tả ngắn</label>
                                    <textarea rows="" cols="" class="form-control" id="moTaNgan" name="moTaNgan" style="width: 700px;height: 175px">${model.moTaNgan}</textarea>
                                </div>
                            </div>
                            <div class="right-edit-course col-sm-4 container-loading-image">
                                <div class="form-group row loading-image">
                                    <div class="">
                                        <label class="col-sm-12 control-label no-padding-right">Ảnh</label>
                                        <img src="${initParam['file-upload']}${model.anh}" id="img" width="240" height="160" />
                                    </div>
                                    <div class="loading-image-btn">
                                        <div class="row">
                                            <input type="file" id="anh" name="anh"
                                                   <c:if test="${not empty model.anh}">value="${initParam['file-upload']}${model.anh}"</c:if>
                                                   multiple/>
                                        </div>
                                        <div class="row">
                                            <input type="submit" id="btnUpload" name="btnUpload" value="Đổi ảnh"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </form>
                    <div class="col-sm-12 form-group"></div>
                </div>
            </div>
            <div class="row">
                <c:if test="${not empty model.id}">
                    <form action="<c:url value='/admin-khoahoc'/>" id="formBGSubmit" method="get">
                        <div class="table-btn-controls">
                            <div class="dt-buttons btn-overlap btn-group">
                                <a flag="info"
                                   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                   title='Thêm bài giảng' href='<c:url value="/admin-baigiang?idKH=${model.id}"/>'>
                                        <span>
                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    </span>
                                </a>
                                <button id="btnDeleteBG" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài giảng'>
                                    <span>
                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                    </span>
                                </button>
                            </div>
                            <div class="label-checkbox">
                                <p style="color: red">Vui lòng chọn khóa học cần xóa</p>
                            </div>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>Tên bài giảng</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${modelBaiGiang.listResult}">
                                <tr>
                                    <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                    <td>${item.tenBaiGiang}</td>
                                    <td>
                                        <c:url var="editURL" value="/admin-baigiang">
                                            <c:param name="type" value="edit"/>
                                            <c:param name="idKH" value="${model.id}"/>
                                            <c:param name="idBG" value="${item.id}"/>
                                        </c:url>
                                        <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                           title="Cập nhật bài giảng" href='${editURL}'>
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
<%--                        <ul class="pagination" id="pagination"></ul>--%>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

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
	var editor = '';

    $("#formSubmit").validate({
        rules: {
            tenKhoaHoc: {
                required: true,
            },
            capDo: {
                required: true,
            },
            moTaNgan: {
                required: true,
            },
        },

        messages: {
            tenKhoaHoc: {
                required: "Vui lòng nhập tên khóa học!",

            },
            capDo: {
                required: "Vui lòng chọn cấp độ!",
            },
            moTaNgan: {
                required: "Vui lòng nhập mô tả",
            }
        },
    });

    $('#btnAddOrUpdateKH').click(function (e) {
        var check = $('#formSubmit').valid();
        if (check) {
            e.preventDefault();
            var formData = new FormData();
            var Data = $('#formSubmit').serializeArray();
            $.each(Data, function (i, v) {
                formData.append(""+v.name+"", v.value);
            });
            var fileInput = document.getElementById('anh');
            var file = fileInput.files[0];
            formData.append('anh', file);

            var id = $('#id').val();
            if (id == "") {
                themKhoaHoc(formData);
            } else {
                capNhatKhoaHoc(formData);
            }
        }
    });

    function themKhoaHoc(formData) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result != 0) {
                    $('#img').attr('src',result.anh);
                }
                window.location.href = "${KhoahocURL}?type=edit&id="+result.id+"&message=them_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }

    function capNhatKhoaHoc(formData) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                window.location.href = "${KhoahocURL}?type=edit&id="+result.id+"&message=capnhat_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }

    $("#btnDeleteBG").click(function() {
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
                XoaBaiGiang(data);
            });
        }
    });

    function XoaBaiGiang(data) {
        $.ajax({
            url: '${APIBGurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${KhoahocURL}?type=edit&id=${model.id}"+"&message=xoa_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?type=edit&id=${model.id}"+"&message=loi_hethong";
            }
        });
    }

</script>
</body>
</html>
