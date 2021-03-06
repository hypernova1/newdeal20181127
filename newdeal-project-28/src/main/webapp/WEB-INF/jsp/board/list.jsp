<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물</title>
</head>
<style>
  table{
    all: unset;
  }
  a[href]{
    text-decoration: none;
  }
  
  a:link { color: black; text-decoration: none;}
  a:visited { color: black; text-decoration: none;}
  a:hover {
    color: black; text-decoration: none;
    background: purple;
    transition: 1s;
  }
</style>

<body>
  <jsp:include page="../header.jsp"></jsp:include>
	<h1>게시물</h1>
	<p><a href="./form">새글</a></p>
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>내용</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="board">
		<tr>
			<td>${board.no}</td>
			<td><a href="./detail?no=${board.no}">${board.contents}</a></td>
			<td>${board.createdDate}</td>
			<td>${board.viewCount}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
