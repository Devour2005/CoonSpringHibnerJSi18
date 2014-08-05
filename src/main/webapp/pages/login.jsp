<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<%--<security:authorize
        access="isAuthenticated()">
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <jsp:forward page="/adminPage"/>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_USER')">
        <jsp:forward page="/welcome"/>
    </security:authorize>
</security:authorize>--%>

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
        <h2>Login, please!</h2>
    </div>
    <br>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">
    <!--LEFT BLOCK-->
    <DIV class="content_left">
        <div>
            <br>

            <h2>Login, please!</h2>
        </div>
        <%--action="${app}/login.do"--%>
        <%--modelAttribute="loginForm"--%>

        <%--BEGIN OF LOGIN FORM--%>
        <c:url value="/j_spring_security_check" var="secureUrl"/>

        <div class="user_info">
            <div>
                <form name="login"
                      method="POST"
                      action=" <c:url value="/j_spring_security_check" />"
                      enctype="application/x-www-form-urlencoded">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>
                    <br>
                    Enter Login:
                    <%--<label path="login"><strong>Enter Login:</strong></label>--%>
                    <input name="j_username" type="text" size="20"/><br>
                    <%--<errors path="login" cssClass="error"/>--%>
                    <br> <br>
                    Enter Password:
                    <%--<label path="password"><strong>Enter Password:</strong></label>--%>
                    <input name="j_password" type="password" size="20"/><br>
                    <%--<errors path="password" cssClass="error"/>--%>
                    <br>
                    <br> <input type="submit" name="submit" value="Login"/>

                </form>
            </div>
            <div>
                <form name="reg" action="${app}/register"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="submit" name="submit" value="Register"/>
                </form>
            </div>
        </div>
        <%--END OF LOGIN FORM--%>
        <br>

        <a href="${app}/calculatePage" title="calculation">Calculation</a> <br> <br>

    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">


        <div class="redaction_block">
            <form name="listUsers" action="${app}adminPage"
                  method="GET">
                <%--enctype="application/x-www-form-urlencoded">--%>
                <%--<input type=hidden name="action" value="listUser"/>--%>
                <input type="SUBMIT" name="SUBMIT" value="View All"/>
            </form>
        </div>

        <br>


        <%-- <a href="${app}/pages/thankForReg.jsp">THANKS</a>
         <br>
         <a href="${app}/pages/exceptionPages/error.jsp">ERROR</a>
         <br>
         <a href="${app}/pages/exceptionPages/exceptions.jsp">EXCEPTION</a>
         <br>

         <div>
             <form name="reg" action="${app}exc.do"
                   method="GET"
                   enctype="application/x-www-form-urlencoded">
                 <input type="submit" name="submit" value="Exc"/>
             </form>
         </div>
         &lt;%&ndash;<a href="${app}/exc">EXC</a>&ndash;%&gt;--%>
    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
    </DIV>
    <!--END OF RIGHT BLOCK-->

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