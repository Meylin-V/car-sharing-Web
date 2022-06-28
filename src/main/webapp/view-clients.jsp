<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="clientBean" scope="request" type="com.example.carsharing.beans.ClientBean"/>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 26.06.2022
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" >
    <title>Clients</title>
</head>
<body>
<h1>Clients</h1>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clientBean.clients}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.phone}</td>
            <td><a href="delete-client?id=${c.id}">Delete</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
