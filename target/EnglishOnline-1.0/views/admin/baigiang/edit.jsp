<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-khoahoc"/>
<c:url var="APIBGurl" value="/api-admin-baigiang"/>
<c:url var ="ChiTietKhoaHocURL" value="/api-admin-chitietkhoahoc"/>
<c:url var ="KhoahocURL" value="/admin-khoahoc"/>
<html>
<head>
    <title>Chỉnh sửa bài giảng</title>
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
                    <a href="<c:url value="/admin-trang-chu"/>">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài giảng</li>
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
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài giảng" id="btnAddOrUpdate"/>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài giảng" id="btnAddOrUpdate"/>
                        </c:if>
                    </div>
                    <form id="formSubmit">
                        <div class="col-sm-12"></div>
                        <div class="col-sm-12 form-group">
                            <label class="col-sm-2 control-label no-padding-right">Tên bài giảng</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="tenBaiGiang" name="tenBaiGiang" style="width: 800px" value="${model.tenBaiGiang}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="col-sm-12 form-group">
                            <label class="col-sm-2 control-label no-padding-right">Nội dung</label>
                            <div class="col-sm-9">
                                <textarea rows="" cols="" id="noiDung" name="noiDung" style="width: 850px;height: 175px">${model.noiDung}</textarea>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var idBaiGiang;
    var editor = '';
    $(document).ready(function(){
        editor = CKEDITOR.replace( 'noiDung');
    });

    $("#formSubmit").validate({
        rules: {
            tenBaiGiang: {
                required: true,
                minlength: 5
            }
        },

        messages: {
            tenBaiGiang: {
                required: "Vui lòng nhập tên bài giảng!",
                minlength: "Tên bài giảng phải bao gồm ít nhất 5 kí tự!"
            }
        },
    });

    $('#btnAddOrUpdate').click(function (e) {
        var check = $('#formSubmit').valid();
        if (check) {
            e.preventDefault();
            var data = {};
            var dataCTKH = {};
            var formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                data[""+v.name+""] = v.value;
            });
            data["noiDung"] = editor.getData();
            var id = $('#id').val();
            if (id == "") {
                themBaiGiang(data);
            } else {
                chinhSuaBaiGiang(data);
            }
        }
    });

    function themBaiGiang(data) {
        $.ajax({
            url: '${APIBGurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                idBaiGiang = result["id"];
                var dataCTKH = {};
                dataCTKH["khoaHocId"] = ${modelKhoaHoc.id};
                dataCTKH["baiGiangId"] = idBaiGiang;

                themChiTietKhoaHoc(dataCTKH);
                <%--window.location.href = "${KhoahocURL}?type=edit&id="+result.id+"&message=insert_success";--%>
            },
            error: function (error) {
                alert("Thêm thất bại");
                <%--window.location.href = "${KhoahocURL}?type=list&maxPageItem=2&page=1&message=error_system";--%>
            }
        });
    }

    function themChiTietKhoaHoc(data) {
        $.ajax({
            url: '${ChiTietKhoaHocURL}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${KhoahocURL}?type=edit&id=${modelKhoaHoc.id}"+"&message=them_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }

    function chinhSuaBaiGiang(data) {
        $.ajax({
            url: '${APIBGurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${KhoahocURL}?type=edit&id=${modelKhoaHoc.id}"+"&message=capnhat_thanhcong";
            },
            error: function (error) {
                window.location.href = "${KhoahocURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }
</script>
</body>
</html>
