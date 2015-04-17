<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal - New User Profile</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>New User Profile</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">

    <DIV class="content_left">  <!-- BEGIN OF CONTENT-->
    </DIV>

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">
        <!--NAVIGATION MENU -->
        <jsp:include page="menu.jsp"/>
        <!--END OF NAVIGATION MENU -->

        <DIV class="profile_block">
            <!--USER INFO BLOCK-->
            <div class="user_info">

                <div>
                    <h2>Your New Data</h2>
                    <h3>Your Id: ${user.userId}</h3>

                    <h3>Your Login: ${user.login}</h3>

                    <h3>Registered name: ${user.name}</h3>

                    <h3>Your Password: ${user.password}</h3>

                    <h3>Your EMail: ${user.email}</h3>

                    <h3>Your PC: </h3>

                    <h3>
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
                    </h3>

                    <h3>Your were registered: </h3>

                    <h3><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></h3>
                </div>

                <div>
                    <form name="logout" action="${app}/logout.do"
                          method="POST"
                          enctype="application/x-www-form-urlencoded">
                        <input type="submit" name="submit" value="Logout"/>
                    </form>
                </div>
            </div>
        </DIV>
        <!--END OF USER INFO BLOCK-->

    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
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