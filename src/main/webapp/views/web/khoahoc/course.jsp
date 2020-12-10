<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-dangkykhoahoc"/>
<html>
<head>
    <title>Khóa học</title>
</head>
<body>
<div class="full-background">
    <div class="content-cs-other full-width-other">
        <div class="content-cs-detail-other">
            <div class="content-cs-title-other">
                <h1>${modelKhoaHoc.tenKhoaHoc}</h1>
            </div>
            <div class="content-cs-shortDescription-other">
                ${modelKhoaHoc.moTaNgan}
            </div>
            <div class="content-cs-span-other">
                <div class="content-cs-teacher-other">
                    Được tạo bởi
                    ${tenGiaoVien}
                </div>
                <div class="content-cs-date-other">
                    Cập nhật lần cuối
                    ${modelKhoaHoc.thoiGianTao}
                </div>
            </div>
            <div class="content-cs-level-other">
                Cấp độ:
                <c:if test="${modelKhoaHoc.capDo == 'De'}">Dễ</c:if>
                <c:if test="${modelKhoaHoc.capDo == 'Trung Binh'}">Trung bình</c:if>
                <c:if test="${modelKhoaHoc.capDo == 'Kho'}">Khó</c:if>
            </div>
        </div>
        <div class="content-cs-image-around">
            <div class="content-cs-image-other">
                <img src="${initParam['file-upload']}${modelKhoaHoc.anh}" width="240px" height="160px" alt="">
            </div>
        </div>
    </div>
</div>

<div class="full-width-other">
    <div class="lesson-title">
        <p>Danh sách bài giảng</p>
    </div>
    <div class="les-con">
        <div class="lesson-card">
<%--            <% int index = 0; %>--%>
            <c:set var="index" value="${0}" />
            <c:forEach var="item" items="${model.listResult}">
                <c:url var="showURL" value="/bai-giang">
                    <c:param name="type" value="learn"/>
                    <c:param name="idKH" value="${modelKhoaHoc.id}"/>
                    <c:param name="idBG" value="${item.id}"/>
                    <c:param name="index" value="${index}"/>
                </c:url>
                <c:set var="index" value="${index + 1}" />
                <div class="col-md-2">
                    <div class="toggle toggle-primary" data-plugin-toggle="">
                        <section class="toggle">
                            <label><a href='${showURL}' title="" style="text-decoration: none"><strong>Bài giảng ${index}</strong></a></label>
                        </section>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${model.listResult.size() == 0}">
                <ion-icon class="icon-alert" name="alert-circle-outline"></ion-icon>
                <span class="lesson-card-notice">Bài giảng sẽ ra mắt trong thời gian sớm nhất</span>
                <ion-icon class="icon-lib" name="library-outline"></ion-icon>
                <ion-icon class="icon-lib" name="library-outline"></ion-icon>
                <ion-icon class="icon-lib" name="library-outline"></ion-icon>
            </c:if>
        </div>
    </div>
</div>

<script>
    var data = {};
    var vaiTro = "${USERMODEL.vaiTro}";
    var hocVien = "${hocVien}";

    // Nếu học viên chưa đăng ký khóa học thì mới thực hiện hàm themDangKyKhoaHoc()
    if (vaiTro === 'hocvien' && hocVien === 'khongTonTai') {
        data["hocVienId"] = ${USERMODEL.id};
        data["khoaHocId"] = ${modelKhoaHoc.id};
        themDangKyKhoaHoc(data);
    } else {
        console.log('Không cần đăng ký')
    }

    function themDangKyKhoaHoc(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                console.log('success');
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
</script>
</body>
</html>
