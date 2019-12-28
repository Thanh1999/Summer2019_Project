<%-- 
    Document   : llogin
    Created on : Jul 4, 2019, 5:09:23 PM
    Author     : tmax3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:set var="unlock" value="${sessionScope.UNLOCK}"/>
        <c:if test="${not empty unlock}">
            <font color="red">
            Your account has been locked!<br/>
            Please refresh the page after 5 minutes 
            </font>  
        </c:if>


        <c:if test="${empty unlock}">
            <form action="login" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td>Username</td>
                            <td colspan="2"><input type="text" name="txtUsername" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Pin</td>                      
                            <td colspan="2"><input type="password" name="txtPin" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Login"/></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                    </tbody>
                </table>
            </table>

        </form>
    </c:if>


</body>
</html>
