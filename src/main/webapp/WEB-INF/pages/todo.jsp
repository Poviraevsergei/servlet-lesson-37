<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }

        .header-buttons {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .header-buttons form {
            display: inline-block;
            margin-left: 10px;
        }

        .header-buttons button {
            padding: 8px 16px;
            background-color: #4a76a8;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .header-buttons button:hover {
            background-color: #3a6690;
        }

        .input-task {
            margin: 80px 0 40px 0;
            text-align: center;
        }

        .input-task input[type="text"] {
            padding: 12px 15px;
            width: 400px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-right: 10px;
        }

        .input-task button {
            padding: 12px 25px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        .input-task button:hover {
            background-color: #218838;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .welcome-message {
            text-align: center;
            color: #333;
            margin: 40px 0 30px 0;
            font-size: 1.5em;
        }

        .task-list-container {
            max-width: 700px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            border-bottom: 2px solid #4a76a8;
            padding-bottom: 15px;
        }

        .task-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 20px;
            margin-bottom: 12px;
            background-color: #f8f9fa;
            border-left: 6px solid #4a76a8;
            border-radius: 6px;
            font-size: 18px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
            transition: all 0.3s ease;
        }

        .task-item:hover {
            background-color: #e9ecef;
            transform: translateX(5px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .task-content {
            flex-grow: 1;
            padding-right: 15px;
        }

        .remove-task {
            margin: 0;
        }

        .remove-task button {
            padding: 8px 16px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            white-space: nowrap;
        }

        .remove-task button:hover {
            background-color: #c82333;
        }

        .no-tasks {
            text-align: center;
            color: #666;
            font-style: italic;
            padding: 30px;
            font-size: 18px;
        }

        .warning-message {
            text-align: center;
            color: #856404;
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            padding: 15px;
            border-radius: 6px;
            margin: 30px auto;
            max-width: 700px;
            font-size: 18px;
        }

        .task-list {
            padding-left: 0;
            list-style: none;
            counter-reset: task-counter;
        }

        .task-list .task-item:before {
            content: counter(task-counter) ". ";
            counter-increment: task-counter;
            font-weight: bold;
            color: #4a76a8;
            margin-right: 10px;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .task-item {
            animation: fadeIn 0.3s ease-out;
        }

        /* Адаптивность */
        @media (max-width: 768px) {
            body {
                margin: 20px;
            }

            .input-task input[type="text"] {
                width: 70%;
                margin-bottom: 10px;
            }

            .task-list-container {
                padding: 20px;
            }

            .header-buttons {
                position: static;
                text-align: right;
                margin-bottom: 20px;
            }

            .task-item {
                flex-direction: column;
                align-items: flex-start;
            }

            .remove-task {
                align-self: flex-end;
                margin-top: 10px;
            }
        }
    </style>
</head>
<body>

<div class="header-buttons">
    <form action="/about-me" method="get">
        <button type="submit">About me</button>
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
    <div class="welcome-message">
        <h2>Hello ${username}!</h2>
    </div>
</c:if>

<c:if test="${warnMessage != null}">
    <div class="warning-message">
        <h3>${warnMessage}!</h3>
    </div>
</c:if>

<div class="task-list-container">
    <h1>Task list:</h1>

    <c:choose>
        <c:when test="${tasks == null || tasks.isEmpty()}">
            <div class="no-tasks">
                <h4>There are no active tasks!</h4>
            </div>
        </c:when>
        <c:otherwise>
            <div class="task-list">
                <c:forEach items="${tasks}" var="task">
                    <div class="task-item">
                        <div class="task-content">${task}</div>
                        <form action="/tasks" method="post" class="remove-task">
                            <input type="hidden" name="removeTask" value="${task}">
                            <button type="submit">Remove</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script>
    document.querySelectorAll('.remove-task button').forEach(button => {
        button.addEventListener('click', function (e) {
            const taskContent = this.closest('.task-item').querySelector('.task-content').textContent;
            if (!confirm(`Are you sure you want to remove task: "${taskContent}"?`)) {
                e.preventDefault();
            }
        });
    });
</script>

</body>
</html>