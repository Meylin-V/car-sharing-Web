<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <title>Welcome to car-sharing service!</title>
</head>
<body>
<h1><%= "Welcome to car-sharing service!" %>
</h1>
<br/>
<form action="client-func" method="get">
    <input name= "client-func" id = "client-func" type="submit" value="I'm a client">
</form>

<br/>

<form action="owner-func" method="get">
    <input name= "owner-func" id = "owner-func" type="submit" value="I'm an owner">
</form>

<br/>
<form action="manager-func" method="get">
    <input name= "manager-func" id = "manager-func" type="submit" value="I'm a manager">
</form>

</body>
</html>