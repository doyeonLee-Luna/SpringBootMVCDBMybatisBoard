<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	el.
	
	error 페이지에서 어떤 정보를 얼만큼 자세히 보여주는 것이 올바른가?
	1. 매우 상세한 예외 정보를 보여 주게 되면 보안적으로 옳지 않음 
	2. 아무것도 안 보여준다면 의미 없는 에러페이지가 되어 버림
	
	따라서, 업무용 시스템 vs 일반 고객 서비스 시스템인지에 따라 달라진다.
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error.jsp</title>
</head>
<body>
	<h1>Error page</h1>
	<p>요청하신 서비스에 문제가 발생했습니다.</p>	
	<p>요청하신 서비스 : ${requestURI}</p>
	<p>발생된 오류 : ${exception}</p>
</body>
</html>