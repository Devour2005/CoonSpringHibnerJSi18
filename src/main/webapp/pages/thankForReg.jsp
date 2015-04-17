<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
    <link rel="shortcut icon" href="${app}pictures/favicon.ico">
</head>
<body>


<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div class="thank">
        <h2>Thank You For Registration!</h2>
    </div>
    <br>
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

        <div align="center">
            Registration completed! <br>
            Now You Can Login.
        </div>

        <!--BUTTON BLOCK-->
        <br>

        <div align="center">
            <form action="${app}/login"
                  method="GET"
                  enctype="application/x-www-form-urlencoded">

                <div align="center"><input type="SUBMIT" name="SUBMIT" value="Login"/></div>
            </form>
        </div>
        <br><br>
        <!--END OF BUTTON BLOCK-->


        <div class="center_block">

            <img width="340" align="absmiddle" src="../pictures/coonwelcome.jpg">
        </div>
        <br>

    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <DIV class="content_right">
        <p align="center"> Advertising</p>
    </DIV>
</DIV>
<!-- END OF CONTENT-->

<!-- BEGIN OF FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END OF FOOTER -->


</body>
</html>