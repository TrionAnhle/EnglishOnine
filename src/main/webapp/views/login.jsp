<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-hocvien"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
<div class="exit-icon">
    <a href="<c:url value="/trang-chu"/>" data-toggle="tooltip" title="Quay lại trang chủ">
        <ion-icon size="large" name="exit-outline"></ion-icon>
    </a>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Đăng nhập</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Đăng ký</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <c:if test="${not empty messageResponse}">
                                <div class="alert alert-${alert}">
                                        ${messageResponse}
                                </div>
                            </c:if>
                            <form id="login-form" action="<c:url value='/dang-nhap?action=dangnhap&idKH=${idKH}&type=${type}'/>" method="post" role="form" style="display: block;">
                                <input type="hidden" id="type" name="type" value="${type}">
                                <div class="form-group">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Email" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Mật khẩu">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Đăng nhập">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-7 col-sm-offset-4">
                                            <a href="#" id="chua-co-tai-khoan">Chưa có tài khoản</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="" method="post" role="form" style="display: none;" autocomplete="off">
                                <div class="form-group">
                                    <input type="text" name="hoTen" id="hoTen" tabindex="1" class="form-control" placeholder="Họ và tên" value="">
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Địa chỉ email" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Mật khẩu">
                                </div>
                                <div class="form-group">
                                    <select class="form-control" id="gioiTinh" name="gioiTinh">
                                        <option value="">Chọn giới tính</option>
                                        <option value=1>Nam</option>
                                        <option value=0>Nữ</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="diaChi" id="diaChi" tabindex="2" class="form-control" placeholder="Địa chỉ">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="soDienThoai" id="soDienThoai" tabindex="3" class="form-control" placeholder="Số điện thoại" value="">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Đăng ký ngay">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="blurred-background"></div>
<%--thong bao dang nhap thanh cong--%>
<div class="dialog-box modal-success">
    <div class="icon-box i-success">
        <i class="material-icons"></i>
    </div>
    <h3>Đăng ký thành công!</h3>
    <div class="my-content"><button class="btn-close close-success">Đóng</button></div>
</div>

<%--thong bao dang nhap that bai--%>
<div class="dialog-box modal-fail">
    <div class="icon-box i-fail">
        <ion-icon name="close-outline"></ion-icon>
    </div>
    <h3>Tài khoản đã tồn tại!</h3>
    <div class="my-content"><button class="btn-close close-fail">Đóng</button></div>
</div>

<script>
    $(document).ready(function(){
        var type = $('#type').val();
        if(type=='dangky'){
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $('#register-form-link').addClass('active');
        }
    });

    $(function() {

        $('#login-form-link').click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#chua-co-tai-khoan').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $('#register-form-link').addClass('active');
            e.preventDefault();
        });

        $.validator.addMethod("hoTenPattern", function (value, element) {
            return this.optional(element) || /^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ\s]+$/.test(value);
        }, "Hãy nhập họ tên ít nhất 8 ký tự chỉ gồm chữ cái!");

        $.validator.addMethod("soDienThoaiPattern", function (value, element) {
            return this.optional(element) || /(09|03|07|01)+([0-9]{8})\b/.test(value);
        }, "Số điện thoại không hợp lệ!");

        $("#login-form").validate({
            rules: {
                username: {
                    required: true,
                },
                password: {
                    required: true,
                },
            },

            messages: {
                username: {
                    required: "Vui lòng nhập email!",
                },
                password: {
                    required: "Vui lòng nhập mật khẩu!"
                },
            },
        });

        $('#login-submit').click(function (e) {
            if ($('#login-form').valid())
                $('#login-form').submit();
        })

        $("#register-form").validate({
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
                email: {
                    required: true,
                    email: true
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
                email: {
                    required: "Vui lòng nhập địa chỉ email!",
                    email: "Email không hợp lệ"
                },
                soDienThoai: {
                    required: "Vui lòng nhập số điện thoại!"
                }
            },
        });
        $('#register-submit').click(function (e) {
            var check = $('#register-form').valid();
            if (check) {
                e.preventDefault();
                var data = {};
                var formData = $('#register-form').serializeArray();
                $.each(formData, function (i, v) {
                    if (i == 3) {
                        if (v.value == "1") {
                            v.value = 1;
                        } else {
                            v.value = 0;
                        }
                    }
                    data[""+v.name+""] = v.value;
                });
                // data["content"] = editor.getData();
                addTaiKhoan(data);
            }

        });
        function addTaiKhoan(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    console.log(result);

                    $('.modal-success').addClass('show-up');
                    $('.blurred-background').addClass('show-up');
                    $('.btn-close').click(function(event) {
                        $('.dialog-box').removeClass('show-up');
                        $('.blurred-background').removeClass('show-up');
                        window.location.href = "dang-nhap?action=dangnhap";
                    });
                    $('.blurred-background').click(function(event) {
                        $('.dialog-box').removeClass('show-up');
                        $('.blurred-background').removeClass('show-up');
                        window.location.href = "dang-nhap?action=dangnhap";
                    });
                },
                error: function (error) {
                    console.log(error);

                    $('.modal-fail').addClass('show-up');
                    $('.blurred-background').addClass('show-up');
                    $('.btn-close').click(function(event) {
                        $('.dialog-box').removeClass('show-up');
                        $('.blurred-background').removeClass('show-up');
                    });
                    $('.blurred-background').click(function(event) {
                        $('.dialog-box').removeClass('show-up');
                        $('.blurred-background').removeClass('show-up');
                    });
                    <%--window.location.href = "<c:url value="/dang-nhap?action=dangnhap"/>";--%>
                }
            });
        }
    });
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
