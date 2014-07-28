<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Sorry!</h2>
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

        <h2> Sorry, this block doesn't work yet!</h2>
        <!--USER INFO BLOCK-->
        <br>

        <div class="no_such_user_block">
            <form action="${app}/pages/welcome.jsp"
                  method="GET"
                  enctype="application/x-www-form-urlencoded">
                <br>

                <div align="center"><input type="SUBMIT" name="SUBMIT" value="Back"/></div>
            </form>
        </div>
        <!--END OF USER INFO BLOCK-->

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