<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Admininstration Page</h2>
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
        <!--NAVIGATION MENU -->
        <div>
            <ul id="navigation">
                <li class="first-link"><a href="${app}/pages/welcome.jsp"
                                          title="home">Home</a></li>
                <li><a href="${app}/calculatePage"
                       title="calculation">Calculation</a></li>
                <li><a href="${app}/pages/userprofile.jsp" title="user profile">User
                    profile</a></li>
                <li><a href="${app}/pages/contacts.jsp" title="contacts">Contacts</a></li>
            </ul>
        </div>
        <!--END OF NAVIGATION MENU -->

        <div align="center">
            <br>

            <h2>Members list </h2>

        </div>


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
                    <th colspan=2>Action</th>
                </tr>
                </thead>

                <tbody align="center">
                <tr>
                    <c:forEach items="${members}" var="user">
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
                                <p class="h3_error">No PC Assigned</p>
                            </c:otherwise>
                        </c:choose>

                            <%--        <c:choose>
                                <c:when test="${not empty comp}">
                                    <c:forEach items="${user.computers}" var="comp">
                                        <c:out value="${comp.pcName}"/>
                                    </c:forEach>
                                </c:when>
                                        <c:when test="${empty comp}">
                                            <p>No PC Assigned</p>
                                        </c:when>
                                    </c:choose>--%>

                    </td>
                    <td><a href="${app}/adminEdit/${user.userId}">Update</a></td>
                        <%--<td><a href="${app}adminEdit?userId=${user.userId}">Update</a></td>--%>
                    <td><a href="${app}delete?userId=${user.userId}">Delete</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>

        </div>
        <!--END OF USERS TABLE-->

    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
        <%--        COMPUTERS        --%>
        <table border=1>
            <thead align="center">
            <tr>
                <th>Comp Id</th>
                <th>PCname</th>
            </tr>
            </thead>

            <tbody align="center">
            <tr>

                <c:forEach items="${computers}" var="computer">
                <td><c:out value="${computer.compId}"/></td>
                <td><c:out value="${computer.pcName}"/></td>
            </tr>
            </c:forEach>


            </tbody>
        </table>
        <!--END OF COMPUTERS TABLE-->

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

