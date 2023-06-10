<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource
        var="ds"
        driver="com.mysql.jdbc.Driver"
        url=""
        user=""
        password=""
/>
<sql:query var="rs" sql="select * from middle_forecasts" dataSource="${ds}" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>API 데이터 조회 화면</title>
</head>
<body>
<h2>단기예보 조회</h2>
<button onclick="getForecast()">클릭</button>
<table border="1" width="100%"  bgcolor="#f0ffff" id="shortForecast">
    <tr>
        <th>id</th>
        <th>category</th>
        <th>fcst_value</th>
    </tr>
</table>
<script>
    function getForecast(){
        const config = {
            method: "get"
        };
        fetch("http://localhost:8080/app/task",config)
        .then((response) => response.json())
        .then((jsonData) => {
            jsonData.forEach((data)=>{
                const tr =document.createElement("tr");
                tr.id=data.id;
                const table = document.getElementById("shortForecast");
                table.appendChild(tr);
                const tableRow = document.getElementById(data.id);
                const id = document.createElement("td");
                const category = document.createElement("td");
                const fcstValue = document.createElement("td");
                id.textContent = data.id;
                category.textContent =  data.category;
                fcstValue.textContent =  data.fcstValue;
                tableRow.appendChild(id);
                tableRow.appendChild(category);
                tableRow.appendChild(fcstValue);
            })
        })
        .catch(error => console.log(error));
    }
</script>
<br>

<h2>중기예보 조회</h2>
<table border="1" width="100%" bgcolor="#f0ffff">
    <tr>
        <th>id</th>
        <th>wfSv</th>
    </tr>
    <c:forEach var="row" items="${rs.rows}">
        <tr>
            <td> <c:out value="${row.id}"/></td>
            <td> <c:out value="${row.wf_sv}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
