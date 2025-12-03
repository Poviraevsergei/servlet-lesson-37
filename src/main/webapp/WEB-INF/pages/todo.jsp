<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo page</title>
</head>
<body>
<div class="header-buttons">
    <form action="/about-me" method="get">
        <button type="submit" >About me</button>
    </form>
    <form action="/logout" method="POST">
        <button type="submit">Logout</button>
    </form>
</div>

<div class="input-task">
    <form action="/tasks" method="post">
        <input type="text" name="task" placeholder="Enter your task" required>
        <button type="submit">Add task</button>
    </form>
</div>

<c:if test="${username != null}">
    <h2>Hello ${username}!</h2>
</c:if>

<c:if test="${warnMessage != null}">
    <h2>${warnMessage}!</h2>
</c:if>

<h1>Task list:</h1>

<c:if test="${tasks == null || tasks.isEmpty()}">
    <h4>There are no active tasks!</h4>
</c:if>

<ol>
    <c:forEach items="${tasks}" var="task">
        <li>${task}</li>
    </c:forEach>
</ol>
</body>
</html>







