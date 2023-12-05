<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>List of the cars</title>
</head>
<body>
<h1><bold>"Here is the list of all the registered cars at Parking Lot: "</bold></h1>
<>
${cars}

<br>
<br>
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
<a href="/cars/search/id">Search for a car</a>

</body>
</html>