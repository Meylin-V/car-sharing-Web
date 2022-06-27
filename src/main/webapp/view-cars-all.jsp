<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="carBean" scope="request" type="com.example.carsharing.beans.CarBean"/>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 23.06.2022
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" >
    <title>All Cars</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Model</th>
        <th>Price</th>
        <th>Owner Name</th>
        <th>Owner Phone</th>
        <th>Available</th>
        <th>Client</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${carBean.cars}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.model}</td>
            <td>${c.price}</td>
            <td>${c.printOwner()}</td>
            <td>${c.printOwnerPhone()}</td>
            <td>${c.available}</td>
            <td>${c.printClient()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="client-func">Show available cars</a>
</body>
</html>
