<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2 align="center">ERROR PAGE!</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">

    <!--CENTRAL BLOCK-->
    <DIV align="center">
        <h2 align="center">SORRY, NO SUCH USER!</h2>
        <br>
        <img align="absmiddle" src="${app}/pictures/crying-racoon.gif">
        <br>
        <!--BUTTON BLOCK-->
        <div class="no_such_user_block">
            <form action="${app}/login"
                  method="GET"
                  enctype="application/x-www-form-urlencoded">
                <br>

                <div align="center"><input type="SUBMIT" name="SUBMIT" value="Back"/></div>
            </form>
        </div>
        <!--END OF BUTTON BLOCK-->
    </DIV>
    <!--END OF CENTRAL BLOCK-->

</DIV>
<!--END OF CONTENT-->

<!-- BEGIN OF FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END OF FOOTER -->


</body>
</html>