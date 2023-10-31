<%@ page import="java.util.*" %>
<%@ page import="java.lang.System" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Лабораторная работа №2</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script src="js/validator.js"></script>
    <script src="js/updater.js"></script>
    <script src="js/clearTable.js"></script>
    <script src="js/graphHandler.js"></script>

</head>
<body>
<div class="back-container">
    <div class="prev-result-container">
        <div id="back-button">
            <button class='pointer' id='prevResult' onClick="window.location.replace('index.jsp');" type="reset" onclick="">Назад</button>
        </div>
        <div id="prev-result-table">
            <table id="results">
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Текущее время</th>
                    <th>Время работы программы (мкс)</th>
                    <th>Результат</th>
                </tr>
                <%--@elvariable id="points" type="ru.iwishyoujoy.web_lab_2.model.PointCollectionManager"--%>
                <tr>
                    <c:if test="${not empty points.collection}">
                        <td>${points.last.x.toString().format("%.2f", points.last.x)}</td>
                        <td>${points.last.y.toString().format("%.2f", points.last.x)}</td>
                        <td>${points.last.r.toString().format("%.2f", points.last.r)}</td>
                        <td>${points.last.time}</td>
                        <td>${points.last.scriptTime}</td>
                        <td>${points.last.status}</td>
                    </c:if>
                </tr>
            </table>
        </div>
    </div>
    <%--<footer>
        <img src="appearance/itmo.png">
    </footer>--%>
</div>
</body>
</html>