<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ouchin.wafasalaf.entity.Historic" %>
<%@ page import="com.ouchin.wafasalaf.entity.Request" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Historic</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Request Historic</h1>

<%
    Request req = (Request) request.getAttribute("request");
    if (req != null) {
%>
<h2>Request Details</h2>
<p>ID: <%= req.getId() %></p>
<p>Project Name: <%= req.getProject_name() %></p>
<p>Amount: <%= req.getAmount() %></p>
<p>Email: <%= req.getEmail() %></p>
<p>Phone Number: <%= req.getPhone_number() %></p>
<% } %>

<h2>Status History</h2>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Status</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Historic> historics = (List<Historic>) request.getAttribute("historics");
        if (historics != null && !historics.isEmpty()) {
            for (Historic historic : historics) {
    %>
    <tr>
        <td><%= historic.getDate() %></td>
        <td><%= historic.getStatus().getStatus() %></td>
        <td><%= historic.getDescription() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">No historic data found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<a href="requestList">Back to Request List</a>
</body>
</html>