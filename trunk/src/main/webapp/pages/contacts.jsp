<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mytag"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<c:set var="css" value="${pageContext.request.contextPath}/style.css"/>
<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="${css}"/>
    <%--<link rel="stylesheet" type="text/css" href="${app}/style.css"/>--%>
    <link rel="shortcut icon" href="${app}/pictures/favicon.ico">
</head>
<body>


<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Contacts</h2>
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
            <mytag:table list="${user}" />
           <%-- <div align="center">
                <h2>User Info!</h2>

                <h3>Welcome, ${user.name}!</h3>

                <h3>Your Login:</h3>

                <h3>${user.login}</h3>

                <h3>Your EMail:</h3>

                <h3>${user.email}</h3>

                <h3>Your were registered: </h3>

                <h3><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></h3>
            </div>
--%>
            <div>
                <form name="logout" action="/logout.do"
                      method="POST"
                      enctype="application/x-www-form-urlencoded">
                    <input type="submit" name="submit" value="Logout"/>
                </form>
            </div>
        </div>
        <!--END OF USER INFO BLOCK-->

    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK -->
    <DIV class="content_center">

        <!--NAVIGATION MENU -->
        <jsp:include page="menu.jsp"/>
        <!--END OF NAVIGATION MENU -->

        <DIV>
            <DIV ID="CONTENT_C">
                <br> <br>
                Contacts<BR>
                <img width="45" align="absmiddle" src="../pictures/e-mail.jpg">E-mail:
                <a class=ab href="mailto:Devour2005@ukr.net">Devour2005@ukr.net<BR></A>

                <img width="40" align="absmiddle" src="../pictures/slype.png"> Skype: Devour_2005<BR>
                <img width="40" align="absmiddle" src="../pictures/icq.jpg">ICQ: 12 12 54 037<BR>
                <img width="45" align="absmiddle" src="../pictures/telefon.jpg">Tel: +38 050 615 82 69<BR>
            </DIV>
        </DIV>


    </DIV>
    <!--END OF CENTRAL BLOCK -->


    <DIV class="content_right">   <!--Right block -->
        <p align="center"> Advertising</p>
    </DIV>
</DIV>
<!--Right block -->
<!-- END of CONTENT-->

<!-- BEGIN of FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END of FOOTER -->


</body>
</html>