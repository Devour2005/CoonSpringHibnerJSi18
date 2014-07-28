<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.springapp.Calculation.PiCalculator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<c:set var="css" value="${pageContext.request.contextPath}/style.css"/>
<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="../style.css"/>
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
        <div>
            <ul id="navigation">
                <li class="first-link"><a href="${app}/pages/welcome.jsp" title="home">Home</a></li>
                <li><a href="${app}/pages/calculation.jsp" title="calculation">Calculation</a></li>
                <li><a href="${app}/pages/userprofile.jsp" title="user profile">User profile</a></li>
                <li><a href="${app}/pages/contacts.jsp" title="contacts">Contacts</a></li>
            </ul>
        </div>
        <!--END OF NAVIGATION MENU -->



        <!--BEGIN OF CALCULATION RESULTS-->
        <div class="calculation_block">
            <p>
                Real Pi = 3.14159265358979323846264338327950288419716939937510 <br>
                58209749445923078164062862089986280348253421170679821480865132 <br>
                82306647093844609550582231725359408128481117450284102701938521 <br>
                10555964462294895493038196 <br>
            </p>
            <c:set var="resultStr">${result}</c:set>
            Calculated Pi= <c:out value="${resultStr}"/>
            <br>
            <%-- split
            <c:if test= "${f:length(resultStr)>65}" >
            this is done
            <c:out value="${fn:split(resultStr, '<br>')}"/>
            gogogo
            &lt;%&ndash;<c:out value="${resultStr}"/><br>&ndash;%&gt;
            </c:if>

            <br>
            Length = ${f:length(resultStr)}
            --%>
            <%--

            ${f:length(result)}

            <c:set var="myValue">${someenum}</c:set>
            ${somemap[myValue]}

            <c:set var="idAsString">${model.data.id}</c:set>
            <c:out value="${model.myHashtable[idAsString]}"/>--%>
            <p>
                Elapsed Time = ${elapsedTime}
            </p>

            <p>
                <%--Calculated Pi EL1 = <c:out value="=${fn:length(result)}"/>--%>
            </p>

            <p>
                <%--Calculated Pi EL2 = ${f:length($(result))}--%>
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