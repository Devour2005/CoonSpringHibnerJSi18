<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ attribute name="list" required="true" type="java.util.List"%>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="computers" scope="request" type="java.util.Collection"/>
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
        <div class="user_info">
            <br> To Do:
            <p class="error">
                1. Doesn't Update correctly ANY user<br>
                from list. Only authorized one.<br>
                <br>

                2. After assingning several computers <br>
                User dublicates as many times as many<br>
                computers have been assingned.<br>
            </p>

        </div>
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
                    <br>

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


                    <form:label path="computers">Enter PC:</form:label> <br>
                    <form:select multiple="true" path="computers" size="3">
                        <%--<form:option value="NONE" label="<--- Select --->"/>--%>
                        <form:option value="Delete" label="Delete"/>
                        <form:options items="${computers}" itemValue="pcName" itemLabel="pcName"/>
                    </form:select><br>


                    <%--   <label for="pcName">Enter PC:</label> <br>
                       <select id="pcName" name="pcName" multiple size="3">
                           <option value="NONE">--- Select ---</option>
                           <option value="PC1">PC1</option>
                           <option value="PC2">PC2</option>
                           <option value="PC3">PC3</option>
                           <option value="PC4">PC4</option>
                           <option value="PC5">PC5</option>
                           <option value="PC6">PC6</option>
                           <option value="PC7">PC7</option>
                           <option value="PC8">PC8</option>
                       </select>
                       <br>--%>

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
