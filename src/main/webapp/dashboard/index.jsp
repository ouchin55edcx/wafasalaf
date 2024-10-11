<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ouchin.wafasalaf.entity.Request" %>
<%@ page import="com.ouchin.wafasalaf.entity.Status" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Requests Dashboard</title>
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
<h1>Requests Dashboard</h1>

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
    <%
        List<Request> requests = (List<Request>) request.getAttribute("requests");
        List<Status> allStatuses = (List<Status>) request.getAttribute("allStatuses");

        if (requests != null && !requests.isEmpty()) {
            for (Request req : requests) {
    %>
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
                    <% for (Status status : allStatuses) { %>
                    <option value="<%= status.getId() %>" <%= (req.getCurrentStatus() != null && req.getCurrentStatus().getId() == status.getId()) ? "selected" : "" %>>
                        <%= status.getStatus() %>
                    </option>
                    <% } %>
                </select>
                <input type="text" name="description" placeholder="Status description" required>
                <input type="submit" value="Update">
            </form>
        </td>
        <td>
            <a href="viewHistoric?id=<%= req.getId() %>">View Historic</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="8">No requests found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>