<%-- 
    Document   : transaction
    Created on : Jul 10, 2019, 7:36:08 PM
    Author     : tmax3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
    </head>
    <body>
        <form action="logout">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <input type="submit" value="Log out" />
        </form>
        <h1>Transaction Page</h1>
        <c:if test="${not empty sessionScope.USER}">
            Please, choose transaction type below
            <form action="transaction">    

                <input type="radio" name="rdType" value="Print Transaction" checked="checked" />Print Transaction<br/>
                <input type="radio" name="rdType" value="Transfer" />Transfers<br/>
                <input type="submit" value="Execute" />
            </form>
        </c:if>
            
    </body>
</html>
