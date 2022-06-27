<jsp:useBean id="carBean" scope="request" type="com.example.carsharing.beans.CarBean"/>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 26.06.2022
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <title>Welcome to car-sharing service!</title>
</head>
<body>
<h1><%= "Please, enter your data" %>
</h1>
<form action="finish_add_car" method="post">
    <label for="model">Model: </label>
    <input id="model" type="text" name="model" value="${carBean.current.model}">
    <br/>
    <label for="price">Price: </label>
    <input id="price" type="text" name="price" value="${carBean.current.model}">
    <br/>

    <input type="hidden" name="id" value="${carBean.current.id}">
    <input type="submit" value="Add">
</form>
</body>
</html>
