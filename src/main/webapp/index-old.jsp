<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:forward page="/emps"/>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
<script src="${APP_PATH}/static/js/jquery-3.2.1.js"></script>
<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
Hello Eclipse <button class="btn btn-primary"> 按钮</button>
${APP_PATH}
</body>
</html>