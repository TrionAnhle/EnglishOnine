<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Khóa học</title>
</head>
<body>
    <div class="full-background">
        <div class="header-container full-width-other">
            <div class="header-bar">
                <div>
                    <h1>Khóa học của tôi</h1>
                </div>
            </div>
            <div class="nav-container">
                <ul class="nav-slide">
                    <li>
                        <a href="">Tất cả khóa</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <section class="list-course-container">
        <form action="<c:url value='/khoa-hoc'/>" id="formSubmit" method="get">
            <div class="full-width-other">
                <div class="list-course-wrapper">
                    <c:forEach var="item" items="${model.listResult}">
                        <c:url var="showURL" value="/bai-giang">
                            <c:param name="type" value="show"/>
                            <c:param name="idKH" value="${item.id}"/>
                            <c:param name="idBG" value=""/>
                            <c:param name="idUSER" value="${USERMODEL.id}"/>
                        </c:url>
                        <div class="card-course">
                            <a href="${showURL}">
                                <div class="card-image">
                                    <img src="${initParam['file-upload']}${item.anh}" alt="">
                                </div>
                            </a>
                            <a href="${showURL}">
                                <div class="card-details">
                                    <strong class="details-name">${item.tenKhoaHoc}</strong>
                                    <div class="details-short">${item.moTaNgan}</div>
                                    <div class="details-bottom">
                                        <span class="details__progress"><span class="progress__bar" style="width: 0%;"></span></span>
                                        <span class="details__start-course">THAM GIA</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${tongKhoaHoc > 8}">
                    <ul class="pagination" id="pagination"></ul>
                </c:if>
                <input type="hidden" value="" id="type" name="type"/>
                <input type="hidden" value="" id="page" name="page"/>
                <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                <input type="hidden" value="" id="sortName" name="sortName"/>
                <input type="hidden" value="" id="sortBy" name="sortBy"/>
            </div>
        </form>
    </section>

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
                        $('#sortBy').val('asc');
                        $('#type').val('own');

                        // để chuyển được trang thì ta phải submit form đến '/admin-khoahoc'
                        $('#formSubmit').submit();
                    }
                }
            });
        });
    </script>
</body>
</html>
