<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>'
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored ="false" %>

<link rel = "stylesheet" href="/front-end/style/bootstrap.min.css" />
<%--<link rel = "stylesheet" href="/front-end/style/main.css" />--%>
<meta charset="UTF-8">
<div class="allPage">

    <form id="select">

    <div class="col-md-12">
        <%--<form>--%>
        <table class="table table-bordered searchTable">
            <tr>
                <td class="search_col">
                    <strong>Поиск</strong>
                </td>
                <td>категория</td>
                <td>
                    <c:set var="selected_category"  value="${requestScope.sel_ctg}" />

                    <select class="select" name="category_id">
                        <option  readonly="readonly" value="">Все категории</option>
                        <c:forEach var="category" items="${requestScope.categories}">
                            <c:if test="${category.category_id==selected_category}">
                                <option selected readonly="readonly" value="${category.category_id}">${category.category_name}</option>
                            </c:if>
                            <c:if test="${category.category_id!=selected_category}">
                                <option  readonly="readonly" value="${category.category_id}">${category.category_name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td>тег </td>
                <td>
                    <input class="select" type="text" placeholder="введите тег" />
                </td>
                <td>
                    <button form="select" formaction="/admin/action.jsp?action=select" type="submit" class="btn btn_new">Применить</button>
                </td>

                <td>
                    <strong>Добавить статью</strong>
                </td>
                <td>
                    <a class="btn btn_new" href="/admin/action.jsp?action=add&obj=page" type="submit">Добавить</a>
                </td>
            </tr>
        </table>
        <input type="hidden" form="select" name="action" value="select">
        <%--</form>--%>
    </div>
    </form>

    <div class="col-md-12 editTable">
        <form id="category" method="get"  action="/admin/add_category">

            <table class="table table-bordered ">
                <tr>
                    <td>
                        <p>
                            <strong>Выберите действие</strong>
                        </p>
                    </td>
                    <td>
                        <select class="select"  name="action">
                            <option readonly="readonly" value="add">Добавить</option>
                            <option readonly="readonly" value="edit">Редактировать</option>
                            <option readonly="readonly" value="delete">Удалить</option>
                        </select>
                    </td>

                    <td>
                        Категория
                    </td>
                    <td>
                        <c:set var="selected_category"  value="${requestScope.sel_ctg}" />

                        <select class="select" name="category_edit">
                            <c:forEach var="category" items="${requestScope.categories}">
                                <c:if test="${category.category_id==selected_category}">
                                    <option selected readonly="readonly" value="${category.category_id}">${category.category_name}</option>
                                </c:if>
                                <c:if test="${category.category_id!=selected_category}">
                                    <option  readonly="readonly" value="${category.category_id}">${category.category_name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input name="category_name" type="text" placeholder="поле для названия" />
                    </td>
                    <td>
                        <button class="btn btn_new" form="category" formaction="/admin/add_category" type="submit">Применить</button>

                    </td>
                </tr>
            </table>
            <input form="category" type="hidden" name="obj" value="category">

        </form>
    </div>



<div class="category1">

    <table class="table table-bordered resultTable" >
        <tr>
            <th class="date">Дата</th>
            <th>Статья</th>
            <th>Категория</th>
            <th>Краткое описание</th>
            <th class="status">Статус</th>
            <th>Теги</th>
        </tr>

    <c:forEach var="page" items="${requestScope.allPages}" >


        <tr>
                <%--Page date--%>
            <td><c:out value="${page.pageDetail.date}" /></td>
                <%--Page name--%>
            <td>
                <a href="/admin/action.jsp?action=edit&obj=page&id=${page.getPage_id()}">
                    <c:out value="${page.pageDetail.getPage_name()}" />
                </a>
            </td>
                <%--Category--%>
            <td>
                <a href="/admin/action.jsp?action=select&category_id=${page.category.category_id}">
                    <c:out value="${page.category.category_name}" />
                </a>
            </td>
                <%--Short description--%>

            <td>
                <p >
                    <%--<c:out value="${page.pageDetail.getContexts.get}" />--%>
                </p>
            </td>
                    <%--Status--%>
            <td  >
                <p>
                    <c:if test="${page.pageDetail.isVisible()==true}">
                        <input type="checkbox" readonly="readonly" checked>
                    </c:if>
                    <c:if test="${page.pageDetail.isVisible()==false}">
                        <input type="checkbox" readonly="readonly">
                    </c:if>
                </p>
            </td>
                    <%--Tags--%>
            <td>
                <p>
                    <%--<c:out value="${page.tags.tag_name}" />--%>
                </p>
            </td>


        </tr>
     </c:forEach>
    </table>

</div>
<div class="pages">


    <a class="main_a"  href="/admin/action.jsp?action=select&page=previous&category_id=${selected_category}&search_page=${requestScope.search_page}" onClick="${requestScope.prev_st}" class="page btn btn_new"><<<</a>
    <button class="page btn btn_new" type="submit">${requestScope.search_page}</button>
    <a class="main_a"  href="/admin/action.jsp?action=select&page=next&category_id=${selected_category}&search_page=${requestScope.search_page}" onClick="${requestScope.next_st}" class="page btn btn_new">>>></a>
    <%--<button class="page btn btn-group" type="submit" formaction="/action.jsp?">>>></button>--%>


    </div>
    </form>
</div>


