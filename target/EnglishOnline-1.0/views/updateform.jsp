<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-hocvien"/>
<c:url var ="CapNhatURL" value="/cap-nhat"/>
<html>
<head>
    <title>Cập nhật</title>
</head>
<body>
<div class="exit-icon">
    <a href="<c:url value="/trang-chu"/>" data-toggle="tooltip" title="Quay lại trang chủ">
        <ion-icon size="large" name="exit-outline"></ion-icon>
    </a>
</div>
<section class="my-container">
    <div class="wrap-contact">
        <form class=" update-form validate-form" id="form-submit" autocomplete="off">
                <span class="update-form-title">
                    Cập nhật thông tin
                </span>

            <div class="wrap-input rs1-wrap-input validate-input">
                <span class="label-input">Họ tên</span>
                <input class="input100" type="text" name="hoTen" id="hoTen" value="${USERMODEL.hoTen}">
            </div>

            <div class="wrap-input rs1-wrap-input validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                <span class="label-input">Email</span>
                <input class="input100" type="emailDis" name="emailDis" disabled value="${USERMODEL.email}">
            </div>

            <div class="wrap-input">
                <span class="label-input">Mật khẩu</span>
                <input class="input100" type="password" name="password" id="password" value="${USERMODEL.password}">
            </div>

            <div class="wrap-input">
                <span class="label-input">Giới tính</span>
                <select class="input100 form-control" id="gioiTinh" name="gioiTinh">
                    <option value="">Chọn giới tính</option>
                    <option value=1 <c:if test="${USERMODEL.gioiTinh == true}">selected="selected"</c:if>>Nam</option>
                    <option value=0 <c:if test="${USERMODEL.gioiTinh == false}">selected="selected"</c:if>>Nữ</option>
                </select>
            </div>

            <div class="wrap-input validate-input">
                <span class="label-input">Địa chỉ</span>
                <input class="input100" type="text" name="diaChi" id="diaChi" value="${USERMODEL.diaChi}">
            </div>

            <div class="wrap-input validate-input">
                <span class="label-input">Số điện thoại</span>
                <input class="input100" type="text" name="soDienThoai" id="soDienThoai" value="${USERMODEL.soDienThoai}">
            </div>

            <input type="hidden" value="${USERMODEL.email}" id="email" name="email"/>
            <input type="hidden" value="${USERMODEL.id}" id="id" name="id"/>

            <div class="container-update-form-btn">
                <div class="wrap-update-form-btn">
                    <div class="update-form-bgbtn"></div>
                    <button class="update-form-btn" id="btnUpdate">
                        Cập nhật
                    </button>
                </div>
            </div>
        </form>
    </div>
</section>
<script>
    $(function() {
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
                    if (i == 2) {
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
                    window.location.href = "${CapNhatURL}?id="+${USERMODEL.id};
                },
                error: function (error) {
                    alert("Cập nhật thất bại")
                    window.location.href = "${CapNhatURL}?id="+${USERMODEL.id};
                }
            })
        }
    });
</script>

<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
