<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<link rel = "stylesheet" href="/front-end/style/bootstrap.min.css" />




<div class="col-md-12">

    <div class="col-md-3"><strong>статистика</strong></div>
    <div class="col-md-3"><strong>все пользователи</strong></div>
    <div class="col-md-3"><strong>пользователь: ${sessionScope.user_name}</strong></div>
    <div class="col-md-3"><a href="/admin/action.jsp?action=logout"> <strong>выход</strong></a></div>

</div>
