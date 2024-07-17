<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.example.project.model.ChangeValueVO" %>
<%@ page import="com.example.project.model.WeatherInfoVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Weather Search</title>
        <link rel="stylesheet" type="text/css" href="../resources/css/weatherResult.css">
    </head>
    <body>
        <%
            ChangeValueVO changeValue = new ChangeValueVO();
            List<WeatherInfoVO> weathers = (List<WeatherInfoVO>) request.getAttribute("weathers");
        %>

        <img id ="sun" src="../resources/img/sun.gif">
        <img id ="rainbow" src="../resources/img/rainbow.png">
        <img id ="waterfall" src="../resources/img/waterfall.png">
        <h3 id ="searching-result">기상청 오픈 API를 활용한 날씨 검색결과</h3>
        <a href="/weather">다른 주소 검색하기</a>

        <table>
            <thead>
                <tr>
                    <th>시간</th>
                    <th>온도</th>
                    <th>하늘상태</th>
                    <th>습도</th>
                    <th>강수량<span style="font-size: 80%;">(mm/h)</span></th>
                    <th>강수형태</th>
                    <th>풍향</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < 6; i++) {
                    out.println("<tr>");
                    out.println("<td>" + changeValue.formatTime(weathers.get(i).getFcstTime()) + "</td>");
                    out.println("<td>" + weathers.get(i + 24).getFcstValue() + "°C</td>");
                    out.println("<td>" + changeValue.changeSKYValue(weathers.get(i + 18).getFcstValue()) + "</td>");
                    out.println("<td>" + weathers.get(i + 30).getFcstValue() + "%</td>");
                    out.println("<td>" + weathers.get(i + 12).getFcstValue() + "<span style=\"font-size: 80%;\">(mm)</span></td>");
                    out.println("<td>" + changeValue.changePTYValue(weathers.get(i + 6).getFcstValue()) + "</td>");
                    out.println("<td>" + changeValue.changeWSDValue(weathers.get(i + 48).getFcstValue()) + "</td>");
                    out.println("</tr>");
                }
            %>
            </tbody>
        </table>
        <img id = "tree" src="../resources/img/tree.gif">
        <img id = "tree2" src="../resources/img/tree.gif">
    </body>
    <footer>
        &copy; 2024 Weather Search
    </footer>
</html>