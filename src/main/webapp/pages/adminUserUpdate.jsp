<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ attribute name="list" required="true" type="java.util.List"%>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal - Admin User Update Page</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>

<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Admin Update Page</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">

    <!--LEFT BLOCK-->
    <DIV class="content_left">
    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">

        <br>

        <!--BEGIN OF USERS TABLE-->
        <div align="center">
            <table border=1>
                <thead align="center">
                <tr>
                    <th>User Id</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Regsiter Date</th>
                    <th>PC Assigned</th>
                </tr>
                </thead>

                <tbody align="center">
                <td><c:out value="${user.userId}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></td>
                <td>
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
                </td>
                </tr>
                </tbody>
            </table>

            <%--action="${app}/adminEdit.do/${user.userId}&${comp.pcName}"--%>
            <%--/${computer.pcName}--%>

            <%--${startTimeList[param.startTime]}--%>

            <%--action="${app}/adminEdit.do/${user.userId}/${comp.compId}"--%>
            <%--action="${app}/adminEdit.do/${user.userId}"--%>
            <!--BEGIN OF REDACTION FORM-->
            <DIV class="admin_redaction_block">
                <sf:form name="adminUserUpdate"
                         method="POST"
                         modelAttribute="userForm"
                         action="${app}/adminEdit.do/${user.userId}"
                         enctype="application/x-www-form-urlencoded">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>

                    <sf:label path="login">Enter new login:</sf:label><br>
                    <sf:input path="login" type="text" size="20"/> <br>
                    <sf:errors path="login" cssClass="error"/>
                    <br>
                    <sf:label path="name"><strong>Enter new name:</strong></sf:label> <br>
                    <sf:input path="name" type="text" size="20"/><br>
                    <sf:errors path="name" cssClass="error"/>
                    <br>
                    <sf:label path="password"><strong>Enter new password:</strong></sf:label> <br>
                    <sf:input path="password" type="text" size="20"/><br>
                    <sf:errors path="password" cssClass="error"/>
                    <br>
                    <sf:label path="email"><strong>Enter new Email:</strong></sf:label> <br>
                    <sf:input path="email" type="text" size="20"/><br>
                    <sf:errors path="email" cssClass="error"/>
                    <%--<sf:label path="computers"><strong>Enter new PC:</strong></sf:label> <br>
                    <sf:input path="computers" type="text" size="50"/><br>
                    <sf:errors path="computers" cssClass="error"/>
--%>
                    <strong>PC Assigned:</strong>

                    <h3 class="h3">
                        <td>
                            <c:choose>
                                <c:when test="${user.computers!= null && !user.computers['empty']}">
                                    <c:forEach items="${user.computers}" var="comp">
                                        <c:out value="${comp.pcName}"/>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p class="h3_error">No PC Assigned</p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </h3>

                    <%--  <sf:label path="role">Role</sf:label>
                      <sf:select path="role">
                          <sf:option value="user" label="user"/>
                          <sf:option value="admin" label="admin"/>
                      </sf:select>--%>

                    <%--<sf:option value="${comp}" itemValue="compId" itemLabel="PC Name">--%>

                    <sf:label path="computers">Enter PC:</sf:label> <br>
                    <sf:select path="computers" size="1">
                        <c:forEach items="${computers}" var="comp" >
                            <sf:option value="${comp}" itemValue="compId" itemLabel="PC Name">
                                <c:out value="${comp}"/>
                            </sf:option>

                            <%--  <sf:option value="${comp.compId}">
                                <c:out value="${comp.compId}"/>
                            </sf:option>--%>
                        </c:forEach>
                    </sf:select>
                    <br> <br>

                <%--    <form:select path="country">
                    <form:option value="0" label="Select" />
                    <form:options items="${countryList}" itemValue="countryId" itemLabel="countryName" />
                    </form:select>--%>


                    <%--    <sf:label path="computers">Enter PC:</sf:label> <br>
                    <sf:select path="computers" size="1">
                        <sf:option value="NONE" label="--- Select ---"/>
                        <c:forEach items="${computers}" var="comp">
                            <sf:option value="${comp.compId}">
                                <c:out value="${comp.pcName}"/>
                            </sf:option>
                        </c:forEach>
                    </sf:select>
                    <br> <br>--%>

                    <input type="SUBMIT" name="SUBMIT" value="Update User"/>
                </sf:form>

                <form name="cancel" action="${app}/adminPage"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="SUBMIT" name="SUBMIT" value="Cancel"/>
                </form>
            </DIV>
            <!--END OF REDACTION FORM-->

        </div>
        <!--END OF USERS TABLE-->

    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
        <br>

    </DIV>
    <!--END OF RIGHT BLOCK-->
</DIV>
<!-- END of CONTENT-->

<!-- BEGIN of FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END of FOOTER -->


</body>
</html>
