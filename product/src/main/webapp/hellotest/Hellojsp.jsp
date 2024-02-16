<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		// 서버사이드렌더링(SSR)
		// model1 html사이사이에 스크립틀릿(자바) 네임변수 정의(변수선언.정의) 섞일 수 있음.
		// model2 스크립틀릿(자바코드) 위로 올림. 변수들 위로 아래 표현식
		// mvc model, view, controller)
	//- model => 데이터 ->DTO
	//- view=> html(화면) -> HTML, JSP
	//- controller => model, view를 제어, 요청, 응답 처리->servlets
    String name = "김준일";
	String inputValue = "test";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<ul>
		<%=name %>
	</ul>
	<div>
		<input value="<%=inputValue %>">
	</div>
</body>
</html>