<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal - Profile Page</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>User Profile Page</h2>
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
        <div>
            <ul id="navigation">
                <li class="first-link"><a href="${app}/welcome" title="home">Home</a></li>
                <li><a href="${app}/calculatePage" title="calculation">Calculation</a></li>
                <li><a href="${app}/pages/userprofile.jsp" title="user profile">User profile</a></li>
                <li><a href="${app}/pages/contacts.jsp" title="contacts">Contacts</a></li>
            </ul>
        </div>
        <!--END OF NAVIGATION MENU -->

        <DIV class="profile_block">

            <!--USER INFO BLOCK-->
            <div class="user_info">

                <div>
                    <h2>User Info!</h2>

                    <h3>Registered name: ${user.name}</h3>

                    <h3>Your Id: ${user.userId}</h3>

                    <h3>Your Login: ${user.login}</h3>

                    <h3>Your Password: ${user.password}</h3>

                    <h3>Your EMail: ${user.email}</h3>

                    <h3>Your PC:

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

                    <h3>${user.regDate} </h3>
                </div>

                <div>
                    <form name="redaction"
                          method="GET"
                          action="${app}/edit/${user.userId}" <%--edit?{id}=${user.userId}--%>
                          enctype="application/x-www-form-urlencoded">
                        <input type="submit" name="submit" value="Redaction"/>
                    </form>
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
        <p align="center"> Administration</p>

        <div>
            <form name="administration"
                  method="GET"
                  action="${app}/adminPage"
                  enctype="application/x-www-form-urlencoded">
                <input type="submit" name="submit" value="Admin Page"/>
            </form>
        </div>
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