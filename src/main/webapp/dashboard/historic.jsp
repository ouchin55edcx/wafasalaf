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
        :root {
            --yellow: #FFD700;
            --white: #FFFFFF;
            --gray: #F0F0F0;
            --blue: #007BFF;
            --black: #333333;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: var(--white);
            color: var(--black);
            line-height: 1.6;
            margin: 0;
            padding: 20px;
        }

        h1, h2 {
            color: var(--blue);
        }

        h1 {
            border-bottom: 2px solid var(--yellow);
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid var(--gray);
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: var(--yellow);
            color: var(--black);
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: var(--gray);
        }

        .request-details {
            background-color: var(--gray);
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .request-details p {
            margin: 5px 0;
        }

        a {
            display: inline-block;
            background-color: var(--blue);
            color: var(--white);
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #0056b3;
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
<div class="request-details">
    <p><strong>ID:</strong> <%= req.getId() %></p>
    <p><strong>Project Name:</strong> <%= req.getProject_name() %></p>
    <p><strong>Amount:</strong> <%= req.getAmount() %></p>
    <p><strong>Email:</strong> <%= req.getEmail() %></p>
    <p><strong>Phone Number:</strong> <%= req.getPhone_number() %></p>
</div>
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