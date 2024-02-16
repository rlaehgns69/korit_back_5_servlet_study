<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	// 내장 객체
    	
    	// 저장소
    	
    	// application 객체 저장소
    	// 생명주기 - 서버가 켜지고 꺼질 때 까지
    	
    	// requset 객체 저장소
    	// 생명주기 - 요청이 들어오고 응답이 될 때까지 저장 공간이 살아있다.
    	
    	// session 객체 저장소
    	// 생명주기 : 서버가 켜지고 꺼질때까지 또는 세션 만료 시간 까지 저장 공간이 살아있다.
    	//String value4 = (String) request.getAttribute("key4");
    	//String value44 = (String) application.getAttribute("key4");
    	//String value444 = (String) application.getAttribute("key4");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>데이터 확인</h1>
	<h2><% %><!-- <=value4%> 1번 key 값 같을 때 request 2번 session 3번 application -->데이터</h2>
	<h3>${key5}<!-- el 표기법 다운캐스팅 생략--></h3>
	<input type = "text" placeholder ="username" />
	<input type = "password" placeholder ="password" />
	<input type = "text" placeholder ="name" />
	<input type = "email" placeholder ="email" />
	
	<button onclick = "handleSignupSubmit();">회원가입</button>
	
	<img src="/product/hellotest/java.png">
	
	<script src ="/product/static/js/signup.js"></script>
	
</body>
</html>