<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Address Search</title>
        <link rel="stylesheet" type="text/css" href="../resources/css/weather.css">
    </head>
    <body>
        <h2 id="searching">날씨 검색</h2>
        <div id="search-wrapper">
            <form action="/search" method="post">
                <input type="text" name="address" placeholder="검색할 주소를 입력하세요">
                <button type="submit">검색</button>
            </form>
            <c:out value="${error}" id="error"/>
        </div>
        <div id = 'css-container'><img src="../resources/img/tree.gif" /></div>
    </body>
    <footer>
        &copy; 2024 Weather Search
    </footer>
</html>