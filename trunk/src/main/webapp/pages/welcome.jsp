<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <h2>Welcome!!!</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">
    <!--LEFT BLOCK-->
    <DIV class="content_left">
        <br>

        <!--USER INFO BLOCK-->
        <div class="user_info">
            <br>

            <div align="center">
                <h2>User Info!</h2>

                <h3>Welcome, ${user.name}!</h3>

                <h3>Your Login:</h3>

                <h3 class="h3">${user.login}</h3>

                <h3>Your EMail:</h3>

                <h3 class="h3">${user.email}</h3>


                <h3>Your PC:</h3>

                <h3 class="h3">
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

                <h3 class="h3"><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></h3>
            </div>


            <div>
                <form name="logout" action="${app}/logout.do"
                      method="POST"
                      enctype="application/x-www-form-urlencoded">
                    <input type="submit" name="submit" value="Logout"/>
                </form>
            </div>
        </div>
        <!--END OF USER INFO BLOCK-->

    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">
        <!--NAVIGATION MENU -->
        <div>
            <ul id="navigation">
                <li class="first-link"><a href="${app}/pages/welcome.jsp" title="home">Home</a></li>
                <li><a href="${app}/calculatePage" title="calculation">Calculation</a></li>
                <li><a href="${app}/pages/userprofile.jsp" title="user profile">User profile</a></li>
                <li><a href="${app}/pages/contacts.jsp" title="contacts">Contacts</a></li>
            </ul>
        </div>
        <!--END OF NAVIGATION MENU -->
        <br>

        <!--DESCRIPTION OF PORTAL-->
        <div class="description">
            This is first Web-portal with registration, authorization and editing of user profile ability.<br><br>
            Tools and technologies used: <br>
            IntelliJ Idea 12-13, HeidiSQL, SublimeText 2, JavaEE, Maven, Tomcat, Servlets, JSP, JSTL, JDBC,
            Hibernate, Spring MVC, Spring Security, mySQL, XML, HTML, CSS2.<br>
        </div>
        <!--END OF PORTAL DESCRIPTION-->
        <br>


        <div>
            <c:if test="${not empty errorMsg}">
                <div class="error">
                    <c:out value="${errorMsg}"/>
                </div>
            </c:if>
            <br>
        </div>

    </DIV>
    <!--END OF CENTRAL BLOCK-->


    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
        <br>


        <div>
            <form name="administration" action="${app}/adminPage"
                  method="GET"
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