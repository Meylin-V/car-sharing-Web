<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="ownerBean" scope="request" type="com.example.carsharing.beans.OwnerBean"/>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 23.06.2022
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" >
    <title>Owners</title>
</head>
<body>
<h1>Owners</h1>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Add Car</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ownerBean.owners}" var="o">
        <tr>
            <td>${o.id}</td>
            <td>${o.firstName}</td>
            <td>${o.lastName}</td>
            <td>${o.phone}</td>
            <td><a href="add-car?id=${o.id}">Add</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="add-owner">I'm a new owner!</a>
</body>
</html>
