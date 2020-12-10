<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Khóa học</title>
</head>
<body>
    <div class="father-container">
        <div class="side-content">
            <div class="side-content-1"></div>
            <div class="side-content-2"></div>
        </div>
        <div class="row-container">
            <p class="title-content">CÁC KHÓA HỌC HIỆN CÓ</p>
            <c:if test="${empty model.listResult}">
                <h2>Không có khóa học nào</h2>
            </c:if>
            <form action="<c:url value='/khoa-hoc'/>" id="formSubmit" method="get">
                <div class="row-container--sapa">
                    <c:forEach var="item" items="${model.listResult}">
                        <div class="course">
                            <div class="course-container">
                                <div class="course-content">
                                    <div class="course-content--title">
                                            ${item.tenKhoaHoc}
                                    </div>
                                    <div class="course-content--shortDescription">
                                            ${item.moTaNgan}
                                    </div>
                                    <c:url var="showURL" value="/bai-giang">
                                        <c:param name="type" value="show"/>
                                        <c:param name="idKH" value="${item.id}"/>
                                        <c:param name="idBG" value=""/>
                                        <c:param name="idUSER" value="${USERMODEL.id}"/>
                                    </c:url>
                                    <div class="course-content--btn">
                                        <c:if test="${not empty USERMODEL}">
                                            <c:if test="${USERMODEL.vaiTro == 'hocvien'}">
                                                <a class="course-content--button" href='${showURL}'>Vào học</a>
                                            </c:if>
                                            <c:if test="${USERMODEL.vaiTro == 'giaovien'}">
                                                <a class="course-content--button" href='${showURL}'>Xem</a>
                                            </c:if>
                                            <c:if test="${USERMODEL.vaiTro == 'admin'}">
                                                <a class="course-content--button" href='${showURL}'>Xem</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty USERMODEL}">
                                            <c:url var="dangnhapURL" value="/dang-nhap">
                                                <c:param name="action" value="dangnhap"/>
                                                <c:param name="type" value="show"/>
                                                <c:param name="idKH" value="${item.id}"/>
                                            </c:url>
                                            <a class="course-content--button" href="${dangnhapURL}">Vào học</a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="course-image">
                                    <img src="${initParam['file-upload']}${item.anh}" alt="" width="240" height="145">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <ul class="pagination" id="pagination"></ul>
                <input type="hidden" value="" id="page" name="page"/>
                <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                <input type="hidden" value="" id="sortName" name="sortName"/>
                <input type="hidden" value="" id="sortBy" name="sortBy"/>
                <input type="hidden" value="${model.type}" id="type" name="type"/>
                <c:if test="${model.type=='search'}">
                    <input type="hidden" value="${model.key}" id="key" name="key"/>
                </c:if>

            </form>
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
    </script>
</body>
</html>
