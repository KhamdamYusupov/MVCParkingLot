<%--
  Created by IntelliJ IDEA.
  User: khamdam
  Date: 01-Dec-23
  Time: 05:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title> Car Registration </title>
</head>
<body>
<form:form action="saveCar" modelAttribute="car">
    Car name: <form:input path="name" />
    <br><br>
    Car price: <form:input path="price" />
    <br><br>
    Car horsePower: <form:input path="horsePower" />
    <br><br>
    <input type="submit" value="Submit" />
</form:form>

<include src="./index.jsp"></include>

<h4>"If you want to register a car, go to :"</h4>
<a href="/cars/registration">Register car form</a>
<br>
<br>
<h4>"If you want to see the list of all registered cars, go to :"</h4>
<a href="/cars/list">List of all the registered cars</a>
<br>
<br>
<h4>"If you want to search for a particular car, go to :"</h4>
<a href="/cars/search/{name}">Search for a car</a>
</body>
</html>
