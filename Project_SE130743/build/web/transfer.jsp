<%-- 
    Document   : transfer
    Created on : Jul 4, 2019, 7:29:22 PM
    Author     : tmax3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body>
        <form action="logout">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <input type="submit" value="Log out" />
        </form>
        <h1>Transfer Page</h1>

        <c:set var="balance" value="${sessionScope.BALANCE}"/>
        <c:if test="${not empty balance}">
            <h2>Your balance is ${balance}</h2>
            <form action="transfer">
                <table>
                    <tbody>
                        <tr>
                            <td>Transfer Amount (1000 VND)</td>
                            <td><input type="text" name="txtAmount" value="" /></td>
                        </tr>
                        <tr>
                            <td>Account</td>
                            <td><input type="text" name="txtAccount" value="" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="Transfer" /></td>
                        </tr>
                    </tbody>
                </table>

            </form>
        </c:if>
        <a href="transaction.jsp">Return to transaction page</a><br/>
        <a href="print.jsp">Print Transaction</a>

    </body>
</html>
