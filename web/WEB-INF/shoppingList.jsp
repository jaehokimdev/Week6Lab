<%-- 
    Document   : shoppingList
    Created on : Oct 14, 2022, 10:14:30 PM
    Author     : joekim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${loginUser}</p>
        <p><a href="ShoppingList?action=logout">Logout</a></p>
        
        <form action="" method="POST">
            <h2>Add Item</h2>
            <input type="text" name="itemname"><input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form action="" method="POST">
            <br>
            <c:forEach items="${items}" var="items">
                &emsp;<input type="radio" name="item" values="${items}">${items}<br>
            </c:forEach>
            <br>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
