<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="list" required="true" type="com.springapp.entity.User" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${app}/style.css"/>

<div align="center">
    <h2>User Info!</h2>

    <h3>Welcome, <h3 class="h3">${user.name}! </h3 class="h3">
    </h3>

    <h3>Your Login:</h3>

    <h3 class="h3">${user.login}</h3>

    <h3>Your EMail:</h3>

    <h3 class="h3">${user.email}</h3>

    <h3>Your PC:</h3>

    <h3 class="h3">
            <c:choose>
                <c:when test="${user.computers!= null && !user.computers['empty']}">
                    <c:forEach items="${user.computers}" var="comp">
                        <c:out value="${comp.pcName}"/>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <p>No PC Assigned</p>
                </c:otherwise>
            </c:choose>
    </h3>

    <h3>Your were registered: </h3>
    <h3 class="h3"><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></h3>


</div>