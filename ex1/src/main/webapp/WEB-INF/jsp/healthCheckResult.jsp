<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%-- <%@ page import ="model.Health" %> --%>
    <%-- <%
    //リクエストスコープに保存されたHealthインスタンスを取得
    Health health = (Health)request.getAttribute("health");
    %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スッキリ健康診断</title>
</head>
<body>
<h1>スッキリ健康診断の結果</h1>
<p>
名前:<%-- <%=  health.getName() %> --%> ${health.name}<br>
身長:<%-- <%=  health.getHeight() %> --%> ${health.height}<br>
体重:<%-- <%=  health.getWeight() %> --%> ${health.weight}<br>
BMI:<%-- <%= String.format("%.2f", health.getBmi()) %> --%>${health.bmi}<br>
BMI:${String.format("%.2f",health.bmi)}<br>
BMI:${Math.floor(health.bmi*100)/100}<br>
BMI:${Math.round(health.bmi*100)/100}<br>
体型:<%-- <%= health.getBodyType() %> --%>${health.bodyType}<br>
</p>
<a href="HealthCheck">戻る</a>
</body>
</html>