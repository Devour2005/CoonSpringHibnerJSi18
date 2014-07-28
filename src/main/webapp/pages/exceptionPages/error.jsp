<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<%@ page isErrorPage="true" %>

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
        <h2 align="center">SORRY, PAGE NOT FOUND!</h2>
        <br>
        <img align="absmiddle" src="${app}/pictures/crying-racoon.gif">
        <br>

    </DIV>
    <!--END OF CENTRAL BLOCK-->

</DIV>
<!--END OF CONTENT-->

<%--
<!-- BEGIN OF FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END OF FOOTER -->--%>


</body>
</html>