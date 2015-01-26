<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
    <link rel = "stylesheet" href="/front-end/style/bootstrap.min.css" />
    <link rel = "stylesheet" href="/front-end/style/page.css" />

    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, max-age=0, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="Fri, 01 Jan 1990 00:00:00 GMT"/>

    <script src = "/front-end/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src = "/front-end/js/bootstrap.min.js" type="text/javascript"></script>

    <c:set var="page" value="${requestScope.page}" />
    <c:set var="selected_category"  value="${requestScope.selected_category}" />

    <title>${page.pageDetail.page_name}</title>

</head>
<body >

<form id="usual"  method="post">

<div class="all col-md-12">
    <div class="header">
        <jsp:include page="/WEB-INF/jsp/admin/base/header.jsp" />
    </div>

    <div class="head">
        <h1>${page.pageDetail.page_name}</h1>
    </div>

    <div class="page_context">
    <jsp:include page="/WEB-INF/jsp/admin/forms/page_context.jsp" />
    </div>


    <div class="short_fields">
        <div class="col-md-6">
            <label class="lbl">название</label>
            <input class="input_name" type="text" placeholder="название" name="name" value="${page.pageDetail.page_name}" />
        </div>
        <div class="col-md-4">
            <label class="lbl">категория</label>
            <select class="select form_elements" name="category">
                <c:forEach var="ctg" items="${requestScope.categories}">
                    <c:if test="${ctg.category_id==selected_category}">
                        <option  readonly="readonly" selected value="${ctg.category_id}">${ctg.category_name}</option>
                    </c:if>
                    <c:if test="${ctg.category_id!=selected_category}">
                        <option  readonly="readonly" value="${ctg.category_id}">${ctg.category_name}</option>
                    </c:if >
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2">
            <input type="text" placeholder="дата" name="date" value="${page.pageDetail.date}" />
        </div>
    </div>

    <div id="parent" class="main row">

        <div class="add_fd col-md-10">
            <div class="textarea">
                <textarea class="fd_textarea" rows="10" name="full_desc">

                </textarea>
            </div>
        </div>
        <div id="child" class="fd_buttons btn-group col-md-2">
                <button class="btn btn-block" type="submit" formaction="/admin/form.jsp?edit_context=add_text">Добавить текст</button>
                <button class="btn btn-block" type="submit" formaction="/admin/form.jsp?edit_context=add_h2" >Добавить подзаголовок</button>
                <button class="btn btn-block" type="submit" formaction="/admin/form.jsp?edit_context=add_photo" >Добавить фотографию</button>
        </div>
    </div>

    <div class="form_buttons col-md-9">
        <div class="col-md-3">
            <a class="btn btn_new" href="/admin/main.jsp?action=cancel" type="submit"  >Отмена</a>
        </div>
        <div class="col-md-3">
            <a class="btn btn_new" href="/admin/form.jsp?action=delete&obj=page&id=${requestScope.page.page_id}">Удалить</a>
        </div>
        <div class="col-md-3">
            <button class="btn" formaction="/admin/form.jsp">Применить</button>
        </div>
    </div>
</div>
<input type="hidden" name = "obj" value="page">
<input type="hidden" name = "id" value=${requestScope.page.page_id}>
<input type="hidden" name = "action" value="${requestScope.action}">

</form>

</body>
</html>



