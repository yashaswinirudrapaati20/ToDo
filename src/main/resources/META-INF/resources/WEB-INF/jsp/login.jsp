
<!DOCTYPE html>
<html>
<head>

    <title>Login</title>
</head>
<body>

<%@ include file="common/header.jspf" %>

<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login"
      method="post">

    <label>Username:</label>
    <input type="text" name="username" required>
    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>
    <br><br>

    <button type="submit">Login</button>
</form>

<p>New user?
    <a href="${pageContext.request.contextPath}/register">
        Register here
    </a>
</p>

<% if (request.getParameter("error") != null) { %>
    <p style="color:red;">
        Invalid username or password. Please try again.
    </p>
<% } %>
<div class="text-end mt-2">
    <a href="${pageContext.request.contextPath}/forgot-password">
        Forgot Password?
    </a>
<%@ include file="common/footer.jspf" %>

</body>
</html>
