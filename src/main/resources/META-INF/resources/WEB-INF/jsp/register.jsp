
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<%@ include file="common/header.jspf" %>
<h2>Create Account</h2>

<form action="${pageContext.request.contextPath}/register"
      method="post">

    <label>Username:</label>
    <input type="text" name="username" required>
    <br><br>

    <label>Email:</label>
    <input type="email" name="email" required>
    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>
    <br><br>

    <button type="submit">Register</button>
</form>

<p>Already have an account?
    <a href="${pageContext.request.contextPath}/login">
        Login here
    </a>
</p>
<% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">
        <%= request.getAttribute("error") %>
    </div>
<% } %>
<%@ include file="common/footer.jspf" %>
</body>
</html>
