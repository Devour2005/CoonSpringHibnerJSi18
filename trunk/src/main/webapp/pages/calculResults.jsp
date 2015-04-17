<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Coon Portal</title>

    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
    <%--<link rel="stylesheet" type="text/css" href="../style.css"/>--%>
    <link rel="shortcut icon" href="${app}pictures/favicon.ico">
</head>
<body>

<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Calculation of Pi</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->
<DIV id="content">

    <!-- BEGIN OF CONTENT-->
    <!--LEFT BLOCK-->
    <DIV class="content_left">
        <br>

    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">
        <!--NAVIGATION MENU -->
        <jsp:include page="menu.jsp"/>
        <!--END OF NAVIGATION MENU -->


        <!--BEGIN OF CALCULATION RESULTS-->
        <div class="calculation_block">
            <p>
                Real Pi = 3.14159265358979323846264338327950288419716939937510 <br>
                58209749445923078164062862089986280348253421170679821480865132 <br>
                82306647093844609550582231725359408128481117450284102701938521 <br>
                10555964462294895493038196 <br>
            </p>

            <p>
                Elapsed Time = ${elapsedTime}
            </p>

            <p>Calculated Pi = ${result} </p>

        </div>
        <!--END OF CALCULATION RESULTS-->


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