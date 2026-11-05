<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
       <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <title>${todo.id == 0 ? 'Add Todo' : 'Update Todo'}</title>
</head>
<body>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<h1>Enter TODO details:</h1>
<form:form method="post"
           action="${todo.id == 0 ? 'add-todo' : 'update-todo'}"
           modelAttribute="todo">
Description:<form:input type="text" path="description" required="required" />
<form:errors path="description" cssClass="text-Warning"/>
<form:input type="date" path="targetDate" required="required"/>
<form:errors path="targetDate" cssClass="text-warning"/>
<form:input type="hidden" path="id"/>
<form:input type="text" path="status"/>

<input type="submit" class="btn btn-success"/>
</form:form>
        </div >
        <%@ include file="common/footer.jspf" %>

</body>
</html>
