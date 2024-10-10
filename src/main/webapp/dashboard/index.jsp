<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ouchin.wafasalaf.entity.Request" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Requests Dashboard</title>
</head>
<body>
<h1>Requests Dashboard</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Project Name</th>
        <th>Amount</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // On récupère la liste des requêtes passée depuis le Servlet
        List<Request> requests = (List<Request>) request.getAttribute("requests");

        if (requests != null && !requests.isEmpty()) {
            for (Request req : requests) {
    %>
    <tr>
        <td><%= req.getId() %></td>
        <td><%= req.getProject_name() %></td>
        <td><%= req.getAmount() %></td>
        <td><%= req.getEmail() %></td>
        <td><%= req.getPhone_number() %></td>
<%--        <td><%= (req.getStatus() != null) ? req.getStatus().getStatus() : "No Status" %></td>--%>
        <td>
            <a href="updateStatus?id=<%= req.getId() %>">Update Status</a> |
            <a href="viewHistoric?id=<%= req.getId() %>">View Historic</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">No requests found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
