<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mytag"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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

        <!--USER INFO BLOCK-->
        <div class="user_info">
            <br>
            <mytag:table list="${user}" />

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
        <jsp:include page="menu.jsp"/>
        <!--END OF NAVIGATION MENU -->

        <!--CALCULATION BLOCK-->
        <div class="calculation_block">
            <div>
                <br>

                <%--<form name="calcul" action="${app}/pages/sorry.jsp"--%>
                <sf:form name="calcul" action="${app}/calculation/POST"
                         method="POST"
                         modelAttribute="dataInputForm"
                         enctype="application/x-www-form-urlencoded">

                    <sf:label path="precision"><strong>Enter precision:</strong></sf:label> <br>
                    <sf:input path="precision" type="text" size="20"/><br>
                    <sf:errors path="precision" cssClass="error"/>
                    <br>
                    <sf:label path="numberOfThreads"><strong>Enter number of threads:</strong></sf:label> <br>
                    <sf:input path="numberOfThreads" type="text" size="20"/><br>
                    <sf:errors path="numberOfThreads" cssClass="error"/>
                    <br>
                    <input type="submit" name="submit" value="Calculate"/>
                </sf:form>
            </div>
            <div>
                <form name="cancel" action="${app}/pages/welcome.jsp"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="SUBMIT" name="SUBMIT" value="Cancel"/>
                </form>
            </div>
        </div>
        <!--END OF CALCULATION BLOCK-->
        <br>


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