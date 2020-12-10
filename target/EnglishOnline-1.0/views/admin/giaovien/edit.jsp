<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-giaovien"/>
<c:url var ="GiaovienURL" value="/admin-giaovien"/>
<html>
<head>
    <title>Chỉnh sửa giáo viên</title>
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
                <li class="active">Chỉnh sửa giáo viên</li>
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
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật giáo viên" id="btnAddOrUpdateGV"/>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm giáo viên" id="btnAddOrUpdateGV"/>
                        </c:if>
                    </div>
                    <form id="formSubmit">
                        <div class="col-sm-8 form-group">
                            <label for="hoTen" class="control-label no-padding-right">Tên Giáo Viên</label>
                            <input type="text" class="form-control" id="hoTen" name="hoTen" value="${model.hoTen}"/>
                        </div>
                        <div class="col-sm-3 form-group">
                            <label for="gioiTinh" class="control-label no-padding-right">Giới Tính</label>
                            <select class="form-control" id="gioiTinh" name="gioiTinh">
                                <c:if test="${empty model.gioiTinh}">
                                    <option value="">Chon giới Tính</option>
                                    <option value=1>Nam</option>
                                    <option value=0>Nữ</option>

                                </c:if>
                                <c:if test="${not empty model.gioiTinh}">
                                    <option value="">Chọn giới tính</option>
                                    <option value=1 <c:if test="${model.gioiTinh == true}">selected="selected"</c:if>>
                                        Nam
                                    </option>
                                    <option value=0 <c:if test="${model.gioiTinh == false}">selected="selected"</c:if>>
                                        Nữ
                                    </option>
                                </c:if>
                            </select>
                        </div>
                        <%--                        <input type="hidden" class="form-control" id="maGiaoVien" name="maGiaoVien" value="${model.maGiaoVien}"/>--%>
                        <br/>
                        <br/>
                        <div class="col-sm-8 form-group">
                            <c:if test="${not empty model.id}">
                                <label for="emailDis" class=" form-control-label">Email</label>
                                <input type="text" disabled id="emailDis" name="emailDis" value="${model.email}"  class="form-control">
                            </c:if>
                            <c:if test="${empty model.id}">
                                <label for="email" class="control-label no-padding-right">Email</label>
                                <input type="text" class="form-control" id="email" name="email" value="${model.email}"/>
                            </c:if>

                        </div>
                        <div class="col-sm-3 form-group">
                            <label for="password" class="control-label no-padding-right">Mật khẩu</label>
                            <input type="text" class="form-control" id="password" name="password" value="${model.password}"/>
                        </div>
                        <br/>
                        <br/>
                        <div class="col-sm-8 form-group">
                            <label for="diaChi" class="control-label no-padding-right">Địa chỉ</label>
                            <input type="text" class="form-control" id="diaChi" name="diaChi" value="${model.diaChi}"/>
                        </div>
                        <div class="col-sm-3 form-group">
                            <label for="soDienThoai" class="control-label no-padding-right">Số Điện Thoại</label>
                            <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" value="${model.soDienThoai}"/>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="vaiTro" class="control-label no-padding-right">Vai Trò</label>
                            <select class="form-control" id="vaiTro" name="vaiTro" <c:if test="${not empty model.vaiTro}">disabled</c:if>>
                                <c:if test="${empty model.vaiTro}">
                                    <option value="">Chọn vai trò</option>
                                    <option value="giaovien">Giáo Viên</option>
                                    <option value="admin">Admin</option>
                                </c:if>
                                <c:if test="${not empty model.vaiTro}">
                                    <option value="">Chọn vai trò</option>
                                    <option value="giaovien" <c:if test="${model.vaiTro == 'giaovien'}">selected="selected"</c:if>>
                                        Giáo Viên
                                    </option>
                                    <option value="admin" <c:if test="${model.vaiTro == 'admin'}">selected="selected"</c:if>>
                                        Admin
                                    </option>
                                </c:if>
                            </select>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </form>
                      <!--98 148-->
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var editor = '';
    // $(document).ready(function(){
    // 	editor = CKEDITOR.replace( 'content');
    // });

    //chuan hoa dl vao
    $.validator.addMethod("hoTenPattern", function (value, element) {
        return this.optional(element) || /^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳýỵỷỹ\s]+$/.test(value);
    }, "Hãy nhập họ tên ít nhất 3 ký tự chỉ gồm chữ cái!");

    $.validator.addMethod("soDienThoaiPattern", function (value, element) {
        return this.optional(element) || /(09|03|07|01)+([0-9]{8})\b/.test(value);
    }, "Số điện thoại không hợp lệ!");

    $.validator.addMethod("emailPattern", function (value, element) {
        return this.optional(element) || /([a-zA-Z0-9])+(@gmail.com)\b/.test(value);
    }, "Email không hợp lệ!");

    $("#formSubmit").validate({
        rules: {
            hoTen: {
                required: true,
                hoTenPattern: true,
                minlength: 3
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
            vaiTro: "required",
            email: {
                required: true,
                emailPattern: true
            },
        },
        messages: {
            hoTen: {
                required: "Vui lòng nhập họ tên!",
                minlength: "Hãy nhập họ tên ít nhất 3 ký tự chỉ gồm chữ cái!"
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
            },
            vaiTro: "Vui lòng chọn vai trò!",
            email: {
                required: "Vui lòng nhập email!"
            },
        },
    });
    //


    $('#btnAddOrUpdateGV').click(function (e) {
        var check = $('#formSubmit').valid();
        if(check){
            e.preventDefault();
            var data = {};
            var formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                if (i == 1) {
                    if (v.value == "1") {
                        v.value = 1;
                    } else {
                        v.value = 0;
                    }
                }
                data[""+v.name+""] = v.value;
            });

            var id = $('#id').val();
            if (id == "") {
                themGiaoVien(data);
            } else {
                capNhatGiaoVien(data);
            }
        }
    });

    function themGiaoVien(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${GiaovienURL}?type=list&maxPageItem=3&page=1&message=them_thanhcong";
            },
            error: function (error) {
                window.location.href = "${GiaovienURL}?type=edit&message=loi_dangky";
            }
        });
    }

    function capNhatGiaoVien(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${GiaovienURL}?type=list&maxPageItem=3&page=1&message=capnhat_thanhcong";
            },
            error: function (error) {
                window.location.href = "${GiaovienURL}?type=list&maxPageItem=3&page=1&message=loi_hethong";
            }
        });
    }

</script>
</body>
</html>
