<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>

<c:forEach var="context" items="${requestScope.context}">
    <div class="all_context">
        <div class="context ">
            <c:if test="${context.context_type=='text'}">
                <div class="col-md-10" >
                    <p>
                        ${context.value}
                    </p>
                </div>
            </c:if>
            <c:if test="${context.context_type=='h2'}">
                <div class="col-md-10" >
                    <p>
                    <h2>${context.value}</h2>
                    </p>
                </div>
            </c:if>
            <c:if test="${context.context_type=='image'}">
                <div>
                    <img src="111" alt="photo" />
                </div>
            </c:if>
        </div>

        <div class="col-md-2 btn-group btn-group-sm pc_buttons">

            <button class="btn btn-primary" formaction="/admin/form.jsp?edit_context=change_contexts&move=up&index=${context.sort_index}">
                <span class="glyphicon glyphicon-circle-arrow-up"></span>
            </button>
            <button class="btn btn-primary" formaction="/admin/form.jsp?edit_context=change_contexts&move=down&index=${context.sort_index}">
                <span class="glyphicon glyphicon-circle-arrow-down"></span>
            </button>
        </div>
    </div>
</c:forEach>


