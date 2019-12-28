<%-- 
    Document   : print
    Created on : Jul 21, 2019, 8:24:07 PM
    Author     : tmax3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Print</title>
    </head>
    <body>
        <h1>Print Transaction</h1>
        <form action="logout">
            <font color="red">
            Welcome, ${sessionScope.NAME}
            </font>
            <input type="submit" value="Log out" />
        </form>
        <c:if test="${not empty sessionScope.BALANCE}">
            Your Balance: ${sessionScope.BALANCE}
            <form action="print">
                From Date<input type="text" name="txtFromDate" value="${param.txtFromDate}" width="10"/>
                To Date<input type="text" name="txtToDate" value="${param.txtToDate}" width="10"/>
                <input type="submit" value="List" />
            </form>
            <c:if test="${not empty param.txtFromDate or not empty param.txtToDate}">
                <c:if test="${not empty requestScope.SEARCHRESULT}">
                    <table border="1" border-collapse: collapse>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Date</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Reason</th>
                                <th>Hide<br/>
                                    <c:url var="showLink" value="show">
                                        <c:param name="txtFromDate" value="${param.txtFromDate}"/>
                                        <c:param name="txtToDate" value="${param.txtToDate}"/>
                                    </c:url>
                                    <a href="${showLink}">Show All</a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${requestScope.SEARCHRESULT}" varStatus="counter">
                            <form action="hide">

                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.transDate}
                                    </td>
                                    <td>
                                        ${dto.type}
                                    </td>
                                    <td>
                                        ${dto.amount}
                                    </td>
                                    <td>
                                        ${dto.reason}
                                    </td>
                                    <td>
                                        <input type="hidden" name="txtID" value="${dto.transID}" />
                                        <input type="hidden" name="txtFromDate" value="${param.txtFromDate}" />
                                        <input type="hidden" name="txtToDate" value="${param.txtToDate}" />
                                        <input type="submit" value="Hide" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty requestScope.SEARCHRESULT}">
                <h2>No record is matched!</h2>
            </c:if>
        </c:if>
        <a href="transaction.jsp">Return to transaction page</a><br/>
        <a href="transfer.jsp">Transfer</a>
    </c:if>
</body>
</html>
