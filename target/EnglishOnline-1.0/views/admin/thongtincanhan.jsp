<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-giaovien"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Thông tin cá nhân</a>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <form id = "form-submit">
                <div class="col-sm-6">
                    <div class="row" >
                        <div class="form-group col-sm-7">
                            <label for="hoTen" class=" form-control-label">Họ và Tên</label>
                            <input type="text" id="hoTen" name="hoTen" value="${USERMODEL.hoTen}" class="form-control">
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="gioiTinh" class=" form-control-label">Giới Tính</label>
                            <select class="form-control" id="gioiTinh" name="gioiTinh">
                                <option value="">Chọn giới tính</option>
                                <option value=1 <c:if test="${USERMODEL.gioiTinh == true}">selected="selected"</c:if>>Nam</option>
                                <option value=0 <c:if test="${USERMODEL.gioiTinh == false}">selected="selected"</c:if>>Nữ</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-7">
                            <label for="emailDis" class=" form-control-label">Email</label>
                            <input type="text" disabled id="emailDis" name="emailDis" value="${USERMODEL.email}"  class="form-control">
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="soDienThoai" class=" form-control-label">Mật khẩu</label>
                            <input type="text" id="password" name="password" value="${USERMODEL.password}"  class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-3">
                            <label for="soDienThoai" class=" form-control-label">Số Điện Thoại</label>
                            <input type="text" id="soDienThoai" name="soDienThoai" value="${USERMODEL.soDienThoai}"  class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-4">
                            <label for="soDienThoai" class=" form-control-label">Địa Chỉ</label>
                            <textarea id="diaChi" name="diaChi" style="width: 500px;height: 100px" value="">${USERMODEL.diaChi}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-4">
                            <input type="button" class="btn btn-sm btn-primary" value="Cập nhật thông tin" id="btnUpdate"/>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <img src="<c:url value="/template/image/errorr.png"/>">
                </div>
                <input type="hidden" value="${USERMODEL.email}" id="email" name="email"/>
            </form>
        </div>
    </div>
</div><!-- /.main-content -->
<script>
    $.validator.addMethod("hoTenPattern", function (value, element) {
        return this.optional(element) || /^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳýỵỷỹ\s]+$/.test(value);
    }, "Hãy nhập họ tên ít nhất 8 ký tự chỉ gồm chữ cái!");

    $.validator.addMethod("soDienThoaiPattern", function (value, element) {
        return this.optional(element) || /(09|03|07|01)+([0-9]{8})\b/.test(value);
    }, "Số điện thoại không hợp lệ!");

    $("#form-submit").validate({
        rules: {
            hoTen: {
                required: true,
                hoTenPattern: true
            },
            gioiTinh: "required",
            diaChi: {
                required: true,
                minlength: 5
            },
            soDienThoai: {
                required: true,
                soDienThoaiPattern: true
            },
            password: {
                required: true,
                minlength: 6
            },
        },
        messages: {
            hoTen: {
                required: "Vui lòng nhập họ tên!"
            },
            gioiTinh: "Vui lòng chọn giới tính!",
            password: {
                required: "Vui lòng nhập mật khẩu",
                minlength: "Mật khẩu phải bao gồm ít nhất 6 ký tự!"
            },
            diaChi: {
                required: "Vui lòng nhập địa chỉ!",
                minlength: "Địa chỉ phải bao gồm ít nhất 5 kí tự!"
            },
            soDienThoai: {
                required: "Vui lòng nhập số điện thoại!"
            }
        },
    });
    $('#btnUpdate').click(function (e) {
        var check = $('#form-submit').valid();
        if(check){
            e.preventDefault();
            var data = {};
            var formData = $('#form-submit').serializeArray();
            $.each(formData, function (i,v) {
                if (i == 1) {
                    if (v.value == "1") {
                        v.value = 1;
                    } else {
                        v.value = 0;
                    }
                }
                data[""+v.name+""] = v.value;
            })
            capNhatThongTin(data);
        }

    })

    function capNhatThongTin(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (result) {
                alert("Cập nhật thành công")
                window.location.href = "<c:url value="/admin-trang-chu?type=thongtin"/>";
            },
            error: function (error) {
                alert("Cập nhật thất bại")
                window.location.href = "<c:url value="/admin-trang-chu?type=thongtin"/>";
            }
        })
    }
</script>
</body>
</html>