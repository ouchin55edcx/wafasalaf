<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ouchin.wafasalaf.entity.Request" %>
<%@ page import="com.ouchin.wafasalaf.entity.Status" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Requests Dashboard</title>
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

        h1 {
            color: var(--blue);
            border-bottom: 2px solid var(--yellow);
            padding-bottom: 10px;
        }

        form {
            background-color: var(--gray);
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        label {
            display: inline-block;
            margin-right: 10px;
        }

        select, input[type="date"], input[type="text"] {
            padding: 5px;
            margin-right: 15px;
            border: 1px solid var(--gray);
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: var(--blue);
            color: var(--white);
            border: none;
            padding: 8px 15px;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
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

        a {
            color: var(--blue);
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Requests Dashboard</h1>

<%
    List<Request> requests = (List<Request>) request.getAttribute("requests");
    List<Status> allStatuses = (List<Status>) request.getAttribute("allStatuses");
    Long statusIdLong = (Long) request.getAttribute("selectedStatusId");
    String startDate = (String) request.getAttribute("startDate");
    String endDate = (String) request.getAttribute("endDate");
%>

<form action="requestList" method="get">
    <label for="statusId">Status:</label>
    <select name="statusId" id="statusId">
        <option value="">All Statuses</option>
        <% if (allStatuses != null) {
            for (Status status : allStatuses) { %>
        <option value="<%= status.getId() %>" <%= (statusIdLong != null && statusIdLong.equals(status.getId())) ? "selected" : "" %>>
            <%= status.getStatus() %>
        </option>
        <% }
        } %>
    </select>

    <label for="startDate">Start Date:</label>
    <input type="date" name="startDate" id="startDate" value="<%= startDate != null ? startDate : "" %>">

    <label for="endDate">End Date:</label>
    <input type="date" name="endDate" id="endDate" value="<%= endDate != null ? endDate : "" %>">

    <input type="submit" value="Filter">
</form>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Project Name</th>
        <th>Amount</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Current Status</th>
        <th>Update Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% if (requests != null && !requests.isEmpty()) {
        for (Request req : requests) { %>
    <tr>
        <td><%= req.getId() %></td>
        <td><%= req.getProject_name() %></td>
        <td><%= req.getAmount() %></td>
        <td><%= req.getEmail() %></td>
        <td><%= req.getPhone_number() %></td>
        <td><%= (req.getCurrentStatus() != null) ? req.getCurrentStatus().getStatus() : "No Status" %></td>
        <td>
            <form action="updateStatus" method="post">
                <input type="hidden" name="requestId" value="<%= req.getId() %>">
                <select name="statusId">
                    <% if (allStatuses != null) {
                        for (Status status : allStatuses) { %>
                    <option value="<%= status.getId() %>" <%= (req.getCurrentStatus() != null && req.getCurrentStatus().getId() == status.getId()) ? "selected" : "" %>>
                        <%= status.getStatus() %>
                    </option>
                    <% }
                    } %>
                </select>
                <input type="text" name="description" placeholder="Status description" required>
                <input type="submit" value="Update">
            </form>
        </td>
        <td>
            <a href="viewHistoric?id=<%= req.getId() %>">View Historic</a>
        </td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="8">No requests found</td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>