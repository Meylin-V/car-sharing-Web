<jsp:useBean id="ownerBean" scope="request" type="com.example.carsharing.beans.OwnerBean"/>

<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 23.06.2022
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
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
<form action="finish_add" method="post">
    <label for="first_name">First Name: </label>
    <input id="first_name" type="text" name="first_name" value="${ownerBean.current.firstName}">
    <br/>
    <label for="last_name">Last Name: </label>
    <input id="last_name" type="text" name="last_name" value="${ownerBean.current.lastName}">
    <br/>
    <label for="phone">Phone: </label>
    <input id="phone" type="text" name="phone" value="${ownerBean.current.phone}">
    <br/>
    <input type="hidden" name="id" value="${ownerBean.current.id}">
    <input type="submit" value="Add">
</form>
</body>
</html>