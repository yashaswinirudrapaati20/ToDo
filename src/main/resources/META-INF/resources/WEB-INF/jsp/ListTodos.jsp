
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyWebApp - List Todos</title>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container mt-4">
    <h1 class="mb-4">YOUR TODO'S</h1>
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>

                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.status}</td>
                    <td>
                        <a href="update-todo?id=${todo.id}" class="btn btn-success btn-sm">Update</a>
                    </td>
                    <td>
                      <a href="delete-todo?id=${todo.id}"
                         class="btn btn-danger btn-sm"
                         onclick="return confirm('Are you sure you want to delete this Todo?');">
                          Delete
                      </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="add-todo" class="btn btn-success">+ Add Todo</a>
</div>

<%@ include file="common/footer.jspf" %>
</body>
</html>
