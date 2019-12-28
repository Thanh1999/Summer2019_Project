<%-- 
    Document   : error
    Created on : Jul 6, 2019, 2:40:27 PM
    Author     : tmax3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <h2>Error occur due to:</h2>
        <font color="red">
        <c:set var="error" value="${requestScope.ERROR}"/>

        <c:if test="${not empty error.invalidAmount}">
            ${error.invalidAmount}<br/>
        </c:if>

        <c:if test="${not empty error.accountNotExisted}">
            ${error.accountNotExisted}<br/>
        </c:if>

        <c:if test="${not empty error.accountIsExpired}">
            ${error.accountIsExpired}<br/>
        </c:if>

        <c:if test="${not empty error.accountsAreTheSame}">
            ${error.accountsAreTheSame}<br/>
        </c:if>

        <c:if test="${not empty error.sessionTimeout}">
            ${error.sessionTimeout}<br/>
        </c:if>

        </font>

        <form action="error">
            Continue to transaction?<br/>

            <input type="submit" value="Yes" name="btAction" />
            <input type="submit" value="No" name="btAction" />
        </form>
    </body>
</html>
